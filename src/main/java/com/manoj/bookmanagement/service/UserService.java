package com.manoj.bookmanagement.service;

import com.manoj.bookmanagement.model.User;
import jakarta.mail.MessagingException;

public interface UserService {

    User registerUser(User user) throws MessagingException;

    boolean isEmailTaken(String email);

    String generateOtp();

    void sendOtpEmail(String email, String otp) throws MessagingException;

    boolean verifyOtp(String actualOtp, String providedOtp);

    CaptchaData generateCaptcha();

    boolean verifyCaptcha(String actualCaptcha, String providedCaptcha);

    void sendThankYouEmail(String email) throws MessagingException;

    public static class CaptchaData {
        private final String text;
        private final String image;

        public CaptchaData(String text, String image) {
            this.text = text;
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public String getImage() {
            return image;
        }
    }
}
