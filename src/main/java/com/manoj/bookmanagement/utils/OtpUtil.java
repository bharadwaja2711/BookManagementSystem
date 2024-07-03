package com.manoj.bookmanagement.utils;

import java.util.Random;

public class OtpUtil {

    private static final Random random = new Random();

    public static String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean verifyOtp(String actualOtp, String providedOtp) {
        return actualOtp.equals(providedOtp);
    }
}
