package com.manoj.bookmanagement.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import javax.imageio.ImageIO;
import com.manoj.bookmanagement.service.UserService.CaptchaData;

public class CaptchaUtil {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int CAPTCHA_LENGTH = 5;

    public static CaptchaData generateCaptcha() {
        int width = 150;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        // Background color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Add noise
        Random random = new Random();
        graphics.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        // Generate CAPTCHA text
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(CHAR_LIST.length());
            char c = CHAR_LIST.charAt(index);
            captchaText.append(c);
        }

        // Draw CAPTCHA text with a consistent color
        graphics.setFont(new Font("Arial", Font.BOLD, 40));
        graphics.setColor(Color.BLACK); // Use a consistent color for all letters
        int x = 10;
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            graphics.drawString(String.valueOf(captchaText.charAt(i)), x, 40);
            x += 25;
        }

        graphics.dispose();

        // Convert image to Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        return new CaptchaData(captchaText.toString(), base64Image);
    }

    public static boolean verifyCaptcha(String actualCaptcha, String providedCaptcha) {
        return actualCaptcha.equals(providedCaptcha);
    }
}
