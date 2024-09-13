package com.example.StudentCompetency.utils;
        import org.bytedeco.javacv.FFmpegFrameGrabber;
        import org.bytedeco.javacv.Frame;
        import org.bytedeco.javacv.Java2DFrameConverter;
        import org.springframework.web.multipart.MultipartFile;

        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.ByteArrayOutputStream;

        import javax.imageio.ImageIO;

public class VideoUtils {

    public static byte[] fetchFrame(MultipartFile videoFile) {
        FFmpegFrameGrabber ff = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ff = new FFmpegFrameGrabber(videoFile.getInputStream());
            ff.start();
            Frame f = ff.grabImage();
            BufferedImage bi = new Java2DFrameConverter().getBufferedImage(f);
            ImageIO.write(bi, "jpg", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ff != null) {
                    ff.stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return os.toByteArray();
    }

    public static BufferedImage rotate(BufferedImage src, int angle) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angle);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angle), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }

    public static Rectangle calcRotatedSize(Rectangle src, int angle) {
        if (angle >= 90) {
            if (angle / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angle = angle % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angle) / 2) * r;
        double angle_alpha = (Math.PI - Math.toRadians(angle)) / 2;
        double angle_delta_width = Math.atan((double) src.height / src.width);
        double angle_delta_height = Math.atan((double) src.width / src.height);
        int len_delta_width = (int) (len * Math.cos(Math.PI - angle_alpha - angle_delta_width));
        int len_delta_height = (int) (len * Math.cos(Math.PI - angle_alpha - angle_delta_height));
        int des_width = src.width + len_delta_width * 2;
        int des_height = src.height + len_delta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}
