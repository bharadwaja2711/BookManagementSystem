package com.manoj.bookmanagement.service;

import com.manoj.bookmanagement.model.User;
import com.manoj.bookmanagement.repository.UserRepository;
import com.manoj.bookmanagement.utils.AESUtil;
import com.manoj.bookmanagement.utils.EmailUtil;
import com.manoj.bookmanagement.utils.OtpUtil;
import com.manoj.bookmanagement.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public User registerUser(User user) throws MessagingException {
        if (isEmailTaken(user.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }
        user.setPassword(encryptPassword(user.getPassword()));
        String otp = generateOtp();
        user.setOtp(otp);
        sendOtpEmail(user.getEmail(), otp);
        return userRepository.save(user); // Ensure OTP is saved in the database
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public String generateOtp() {
        return OtpUtil.generateOtp();
    }

    @Override
    public void sendOtpEmail(String email, String otp) throws MessagingException {
        emailUtil.sendEmail(email, "Book Management System", "Your OTP code is " + otp);
    }

    @Override
    public boolean verifyOtp(String actualOtp, String providedOtp) {
        return OtpUtil.verifyOtp(actualOtp, providedOtp);
    }

    @Override
    public CaptchaData generateCaptcha() {
        return CaptchaUtil.generateCaptcha();
    }

    @Override
    public boolean verifyCaptcha(String actualCaptcha, String providedCaptcha) {
        return actualCaptcha.equals(providedCaptcha);
    }

    @Override
    public void sendThankYouEmail(String email) throws MessagingException {
        emailUtil.sendEmail(email, "Book Management System", "Thank you for registering with our service. We're glad to have you!");
    }

    private String encryptPassword(String password) {
        return AESUtil.encrypt(password);
    }
}
