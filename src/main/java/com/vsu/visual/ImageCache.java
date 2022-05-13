package com.vsu.visual;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

import static com.vsu.App.logger;


public class ImageCache {
    private final Map<String, Image> imageMap;
    public ImageCache() {
        this.imageMap = new HashMap<>();
    }

    public Image getImageByPath(String path) {
        if (!imageMap.containsKey(path)) {
            try {
                imageMap.put(path, new Image(String.valueOf(getClass().getResource(path))));
            } catch (RuntimeException e) {
                logger.info("Internal graphics not initialized yet");
            }
        }
        return imageMap.get(path);
    }
}
