package com.bsuir.shoken.iam;

interface PasswordHashEncoder {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
