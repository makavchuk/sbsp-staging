package com.example.sbsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@SpringBootApplication
public class SbspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbspApplication.class, args);
    }

    @RestController
    public static class ImageController {

        @GetMapping(value = "/generateImage", produces = MediaType.IMAGE_PNG_VALUE)
        public byte[] generateImage() throws IOException {
            int squareSize = 100;
            int imageSize = 5 * squareSize;

            BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    graphics.setColor(randomColor);
                    graphics.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
                }
            }

            graphics.dispose();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);

            return outputStream.toByteArray();
        }
    }
}

