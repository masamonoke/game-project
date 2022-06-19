package com.vsu.utils;

import java.io.*;

public class ResourceReaderUtil {
    //передавать значения вида "src/main/resources/..."
    public static BufferedReader readFile(String localPath) throws IOException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(localPath);
        } catch (FileNotFoundException e) {
            var file = new File(localPath);
            if (file.createNewFile()) {
                fileReader = new FileReader(localPath);
            } else {
                return null;
            }
        }
        return new BufferedReader(fileReader);
    }
}
