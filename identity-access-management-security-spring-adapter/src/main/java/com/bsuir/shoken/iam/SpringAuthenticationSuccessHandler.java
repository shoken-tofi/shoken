package com.bsuir.shoken.iam;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class SpringAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpStatus httpStatusToReturn;

    public SpringAuthenticationSuccessHandler() {
        this.httpStatusToReturn = HttpStatus.OK;
    }

    public SpringAuthenticationSuccessHandler(HttpStatus httpStatusToReturn) {
        Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
        this.httpStatusToReturn = httpStatusToReturn;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(this.httpStatusToReturn.value());
        response.getWriter().flush();
    }
}
