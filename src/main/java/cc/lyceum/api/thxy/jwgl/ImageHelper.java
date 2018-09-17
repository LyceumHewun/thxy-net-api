package cc.lyceum.api.thxy.jwgl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 隔壁工作室干不过就改验证码, 哎哎哎, 记住今天是2018-9-17
 *
 * @author LyceumHewun
 * @date 2018-9-17 MODIFY
 */
public class ImageHelper {

    private BufferedImage image;

    public ImageHelper(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage build() {
        return this.image;
    }

    /**
     * 处理干扰线</br>
     * 纵切遍历, 连续黑块高度小于一定范围则替换为白块</br>
     * 注意要先用{@link #filling()}处理成黑白高对比图片</br>
     *
     * @return ImageHelper
     */
    public ImageHelper removeInterference() {
        int width = image.getWidth();
        int height = image.getHeight();
        // 将最左边的全部清理掉
//        for (int x = 0; x < 23; x++) {
//            replaceBlackByY(x, 0, height);
//        }
        // 清理纵向连续黑块
        for (int x = 0; x < width; x++) {
            // 记录连续黑块数
            int blackNum = 0;
            // 连续黑块的开始Y坐标
            int startY = 0;
            for (int y = 0; y < height; y++) {
                // 是否黑块
                if (image.getRGB(x, y) == new Color(0, 0, 0).getRGB()) {
                    if (blackNum == 0) {
                        startY = y;
                    }
                    blackNum++;
                } else {
                    if (blackNum <= 3 && blackNum != 0) {
                        // 连续黑块替换成白块
                        replaceBlackByY(x, startY, y);
                    }
                    // 初始化
                    blackNum = 0;
                }
            }
        }
        // 清理横向连续黑块
        for (int y = 0; y < height; y++) {
            // 记录连续黑块数
            int blackNum = 0;
            // 连续黑块的开始X坐标
            int startX = 0;
            for (int x = 0; x < width; x++) {
                // 是否黑块
                if (image.getRGB(x, y) == new Color(0, 0, 0).getRGB()) {
                    if (blackNum == 0) {
                        startX = x;
                    }
                    blackNum++;
                } else {
                    if (blackNum <= 3 && blackNum != 0) {
                        // 连续黑块替换成白块
                        replaceBlackByX(y, startX, x);
                    }
                    // 初始化
                    blackNum = 0;
                }
            }
        }
        // 蚀骨算法
        erosion();
        return this;
    }

    private void replaceBlackByX(int y, int startX, int endX) {
        for (int i = startX; i < endX; i++) {
            image.setRGB(i, y, 0xFFFFFFFF);
        }
    }

    private void replaceBlackByY(int x, int startY, int endY) {
        for (int i = startY; i < endY; i++) {
            image.setRGB(x, i, 0xFFFFFFFF);
        }
    }

    private ImageHelper erosion() {
        int width = image.getWidth();
        int height = image.getHeight();
        // 纵向
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                // 左右两个像素是白色
                if (image.getRGB(x, y) == new Color(0, 0, 0).getRGB()
                        && ((image.getRGB(x - 1, y) == new Color(255, 255, 255).getRGB() && image.getRGB(x + 1, y) == new Color(255, 255, 255).getRGB())
                        || (image.getRGB(x, y - 1) == new Color(255, 255, 255).getRGB() && image.getRGB(x, y + 1) == new Color(255, 255, 255).getRGB()))) {
                    image.setRGB(x, y, 0xFFFFFFFF);
                }
            }
        }
        return this;
    }

    public ImageHelper filling() {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (isBlackColor(x, y)) {
                    image.setRGB(x, y, 0xFF000000);
                } else {
                    image.setRGB(x, y, 0xFFFFFFFF);
                }
            }
        }
        return this;
    }

    private boolean isBlackColor(int x, int y) {
        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
            return false;
        }

        int pixel = image.getRGB(x, y);

        return
                // R
                (pixel & 0xFF0000) >> 16 < 130
                        // G
                        && (pixel & 0xFF00) >> 8 < 130
                        // B
                        && (pixel & 0xFF) < 130;
    }

    public List<BufferedImage> spilt() {
        int width = image.getWidth();
        int height = image.getHeight();
        // 瀑布图数据
        List<Integer> waterfallList = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            int blackNum = 0;
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) == new Color(0, 0, 0).getRGB()) {
                    blackNum++;
                }
                // 纵向最后一个
                if (y == height - 1) {
                    waterfallList.add(blackNum);
                }
            }
        }
        // 根据瀑布图分割
        // 验证码的右边开始切
        // 获取分割的x坐标
        List<Integer> xList = new ArrayList<>();
        for (int i = waterfallList.size() - 2; i > 1; i--) {
            if (waterfallList.get(i) >= 3
                    && waterfallList.get(i + 1) < 3
                    && waterfallList.get(i - 1) >= 3) {
                xList.add(i + 1);
            }
        }
        if (xList.size() != 4) {
            return null;
        }
        // 切割
        List<BufferedImage> digitImageList = new LinkedList<>();
        final int y = 10;
        final int heights = 45;
        digitImageList.add(image.getSubimage(0, y, xList.get(3) + 1, heights));
        digitImageList.add(image.getSubimage(xList.get(3), y, xList.get(2) - xList.get(3) + 1, heights));
        digitImageList.add(image.getSubimage(xList.get(2), y, xList.get(1) - xList.get(2) + 1, heights));
        digitImageList.add(image.getSubimage(xList.get(1), y, xList.get(0) - xList.get(1) + 1, heights));
        return digitImageList;
    }

    /**
     * 从右到左, 这是特殊情况
     *
     * @param image image
     * @return String
     */
    public static String binarization(BufferedImage image) {
        StringBuilder s = new StringBuilder();
        for (int w = image.getWidth() - 1; w > 0; w--) {
            for (int h = 0; h < image.getHeight(); h++) {
                if ((image.getRGB(w, h) & 0xFFFFFF) == (new Color(0, 0, 0).getRGB() & 0xFFFFFF)) {
                    s.append("1");
                } else {
                    s.append("0");
                }
            }
        }
        return s.toString();
    }
}
