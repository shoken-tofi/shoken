package com.bsuir.shoken.bid;

import com.bsuir.shoken.ShokenConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@RestController
class ImageRestController extends ImageController {

    @Autowired
    ImageRestController(ServletContext servletContext, ShokenConfigurationProperties configurationProperties) {
        super(servletContext, configurationProperties);
    }
}
