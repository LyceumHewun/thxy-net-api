package cc.lyceum.api.thxy.network;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Discern {

    private Map<String, String> dictionary = new Dictionary().getDictionary();

    public String getCode(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));

            image = this.removeInterference(image);
            image = this.filling(image);

            List<Integer> source = this.convert(image);

            List<int[]> reslut = this.spilt(source);

            // fail
            if (reslut.size() != 4) {
                return null;
            }

            List<BufferedImage> digitImageList = this.spilt(reslut, image);

            List<String> list = this.binarization(digitImageList);

            // return code
            return this.comparing(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private BufferedImage removeInterference(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean c = true;
                int roundWhiteCount = 0;
                if (isBlackColor(image, x + 1, y + 1)) {
                    roundWhiteCount++;
                }
                if (isBlackColor(image, x + 1, y - 1)) {
                    roundWhiteCount++;
                }
                if (isBlackColor(image, x - 1, y + 1)) {
                    roundWhiteCount++;
                }
                if (isBlackColor(image, x - 1, y - 1)) {
                    roundWhiteCount++;
                }
                if (roundWhiteCount >= 4) {
                    c = false;
                }

                if (!isBlackColor(image, x, y)
                        && c
                        ) {
                    //argb:AARRGGBB
                    image.setRGB(x, y, 0xFFFFFFFF);
                }
            }
        }
        return image;
    }

    private BufferedImage filling(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((image.getRGB(x, y) & 0xFFFFFF) != (new Color(255, 255, 255).getRGB() & 0xFFFFFF)) {
                    image.setRGB(x, y, 0xFF000000);
                }
            }
        }
        return image;
    }

    private boolean isBlackColor(BufferedImage image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
            return false;
        }

        int pixel = image.getRGB(x, y);

        return
                // R
                (pixel & 0xFF0000) >> 16 < 30
                        // G
                        && (pixel & 0xFF00) >> 8 < 30
                        // B
                        && (pixel & 0xFF) < 30;
    }

    private List<Integer> convert(BufferedImage image) {
        List<Integer> source = new LinkedList<>();
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            int count = 0;
            // 计算黑块总数
            for (int y = 0; y < height; y++) {
                if (isBlackColor2(image, x, y)) {
                    count++;
                }
//                image.setRGB(x, y, 0xFFFFFFFF);
            }
            for (int y = height - 1; y > height - count - 1; y--) {
//                image.setRGB(x, y, 0xFF000000);
            }
            source.add(count);
        }
        return source;
    }

    private boolean isBlackColor2(BufferedImage image, int x, int y) {
        return (image.getRGB(x, y) & 0xFFFFFF) == (new Color(0, 0, 0).getRGB() & 0xFFFFFF);
    }

    private List<int[]> spilt(List<Integer> source) {
        List<int[]> reslut = new LinkedList<>();
        for (int n = 0, count = 0; n < source.size(); n++) {
            if (source.get(n) != 0) {
                count++;
            } else if (count > 2 || (n > 2 && source.get(n - 1) + source.get(n - 2) > 3)) {
                if (source.subList(n - count, n).size() > 1) {
                    reslut.add(new int[]{n - count + 1, n});
                }
                count = 0;
            }
        }
        return reslut;
    }

    private List<BufferedImage> spilt(List<int[]> list, BufferedImage image) {
        final int DIGIT_Y = 0;
        final int DIGIT_HEIGHT = image.getHeight();

        List<BufferedImage> digitImageList = new LinkedList<>();
        digitImageList.add(image.getSubimage(list.get(0)[0] - 1, DIGIT_Y, list.get(0)[1] - list.get(0)[0] + 1, DIGIT_HEIGHT));
        digitImageList.add(image.getSubimage(list.get(1)[0] - 1, DIGIT_Y, list.get(1)[1] - list.get(1)[0] + 1, DIGIT_HEIGHT));
        digitImageList.add(image.getSubimage(list.get(2)[0] - 1, DIGIT_Y, list.get(2)[1] - list.get(2)[0] + 1, DIGIT_HEIGHT));
        digitImageList.add(image.getSubimage(list.get(3)[0] - 1, DIGIT_Y, list.get(3)[1] - list.get(3)[0] + 1, DIGIT_HEIGHT));

        return digitImageList;
    }

    private List<String> binarization(List<BufferedImage> images) {
        List<String> list = new LinkedList<>();
        for (BufferedImage image : images) {
            StringBuilder reslut = new StringBuilder();
            for (int w = 0; w < image.getWidth(); w++) {
                for (int h = 0; h < image.getHeight(); h++) {
                    if ((image.getRGB(w, h) & 0xFFFFFF) == (new Color(0, 0, 0).getRGB() & 0xFFFFFF)) {
                        reslut.append("1");
                    } else {
                        reslut.append("0");
                    }
                }
            }
            list.add(reslut.toString());
        }
        return list;
    }

    private String comparing(List<String> lists) {
        StringBuilder reslut = new StringBuilder();
        for (String s : lists) {
            int minCount = s.length();
            String re = "";
            for (Map.Entry<String, String> dic : dictionary.entrySet()) {
                String d = dic.getKey();
                int count = 0;
                one:
                if (d.length() == s.length()) {
                    for (int n = 0; n < s.length() - 1; n++) {
                        if (!s.substring(n, n + 1).equals(d.substring(n, n + 1))) {
                            count++;
                        }
                        if (count >= minCount) {
                            break one;
                        }
                    }
                } else {
                    continue;
                }
                if (count < minCount) {
                    minCount = count;
                    re = dic.getValue();
                }
            }
            reslut.append(re);
        }
        return reslut.toString();
    }
}
