package com.bsuir.shoken.iam;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringBCryptPasswordHashEncoder extends BCryptPasswordEncoder implements PasswordHashEncoder {
}
