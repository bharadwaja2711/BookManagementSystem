package com.manoj.bookmanagement.controller;

import com.manoj.bookmanagement.model.User;
import com.manoj.bookmanagement.repository.UserRepository;
import com.manoj.bookmanagement.service.UserService;
import com.manoj.bookmanagement.service.UserService.CaptchaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        model.addAttribute("user", new User());

        // Generate CAPTCHA
        CaptchaData captchaData = userService.generateCaptcha();
        session.setAttribute("actualCaptcha", captchaData.getText());
        model.addAttribute("captchaImage", captchaData.getImage());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam String captcha, Model model, HttpSession session) {
        String actualCaptcha = (String) session.getAttribute("actualCaptcha");
        if (!userService.verifyCaptcha(actualCaptcha, captcha)) {
            model.addAttribute("error", "Invalid CAPTCHA");

            // Generate new CAPTCHA
            CaptchaData captchaData = userService.generateCaptcha();
            session.setAttribute("actualCaptcha", captchaData.getText());
            model.addAttribute("captchaImage", captchaData.getImage());

            return "register";
        }

        try {
            userService.registerUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());

            // Generate new CAPTCHA
            CaptchaData captchaData = userService.generateCaptcha();
            session.setAttribute("actualCaptcha", captchaData.getText());
            model.addAttribute("captchaImage", captchaData.getImage());

            return "register";
        } catch (MessagingException e) {
            model.addAttribute("error", "Failed to send OTP email");

            // Generate new CAPTCHA
            CaptchaData captchaData = userService.generateCaptcha();
            session.setAttribute("actualCaptcha", captchaData.getText());
            model.addAttribute("captchaImage", captchaData.getImage());

            return "register";
        }

        model.addAttribute("user", user);
        return "verifyOtp";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam Long id, @RequestParam String otp, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "verifyOtp";
        }

        String actualOtp = user.getOtp();
        System.out.println("Actual OTP from user: " + actualOtp); // Debugging statement
        System.out.println("Provided OTP: " + otp); // Debugging statement

        if (actualOtp == null) {
            model.addAttribute("error", "OTP not found for the user");
            return "verifyOtp";
        }

        if (userService.verifyOtp(actualOtp, otp)) {
            user.setVerified(true);
            userRepository.save(user);
            try {
                userService.sendThankYouEmail(user.getEmail());
            } catch (MessagingException e) {
                model.addAttribute("error", "Failed to send thank you email");
                return "verifyOtp";
            }
            return "registrationSuccess";
        } else {
            model.addAttribute("error", "Invalid OTP");
            return "verifyOtp";
        }
    }
}
