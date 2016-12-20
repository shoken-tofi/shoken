package com.bsuir.shoken.bid;

import com.bsuir.shoken.ShokenConfigurationProperties;
import com.bsuir.shoken.ValidationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/images")
abstract class ImageController {

    final static String IMAGE_PATH = "/";

    private final ServletContext servletContext;

    private final ShokenConfigurationProperties configurationProperties;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/upload")
    public ImageDto upload(@RequestParam("file") MultipartFile image) throws ValidationException {

        final String originalFilename = image.getOriginalFilename();
        final String imageExtension = getExtension(originalFilename);
        final String generatedImageName = UUID.randomUUID() + "." + imageExtension.toLowerCase();

        final File file = new File(servletContext.getRealPath("") + IMAGE_PATH + generatedImageName);
        try {
            StreamUtils.copy(image.getInputStream(), new FileOutputStream(file));
        } catch (IOException e) {
            throw new ValidationException(e);
        }

        return new ImageDto(configurationProperties.getImageServer().getName() + IMAGE_PATH + generatedImageName,
                generatedImageName);
    }
}
