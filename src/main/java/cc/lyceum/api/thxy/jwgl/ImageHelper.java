package cc.lyceum.api.thxy.jwgl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

// 写只香蕉注释, 用就是
public class ImageHelper {

    private BufferedImage image;

    public ImageHelper(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage build() {
        return this.image;
    }

    public ImageHelper removeInterference() {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) == new Color(0, 0, 0).getRGB()) {
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
        final int x = 30;
        final int y = 13;
        final int width = 22;
        final int height = 35;

        List<BufferedImage> digitImageList = new LinkedList<>();
        digitImageList.add(image.getSubimage(x, y, width, height));
        digitImageList.add(image.getSubimage(x + width, y, width, height));
        digitImageList.add(image.getSubimage(x + width * 2, y, width, height));
        digitImageList.add(image.getSubimage(x + width * 3, y, width, height));

        return digitImageList;
    }

    public static String binarization(BufferedImage image) {
        StringBuilder s = new StringBuilder();
        for (int w = 0; w < image.getWidth(); w++) {
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
