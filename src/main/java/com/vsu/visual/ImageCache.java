package com.vsu.visual;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;


public class ImageCache {
    private final Map<String, Image> imageMap;
 //Ghjd
    public ImageCache() {
        this.imageMap = new HashMap<>();
    }

    public Image getImageByPath(String path) {
        if (!imageMap.containsKey(path)) {
            imageMap.put(path, new Image(String.valueOf(getClass().getResource(path))));
        }
        return imageMap.get(path);
    }
}
