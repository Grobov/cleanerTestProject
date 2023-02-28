package utils;

import io.qameta.allure.Step;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class FileUtils {

    public static void waitForFile(String fileFolderPath, String fileName) {
        var filePath = fileFolderPath + "\\" + fileName;
        var path = Paths.get(filePath);

        while (!Files.exists(path)) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Waiting for file " + filePath + " to exist...");
        }
        System.out.println("File " + filePath + " found!");
    }

    @Step("Execute app: {path}")
    public static void execute(String path) {
        var desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
