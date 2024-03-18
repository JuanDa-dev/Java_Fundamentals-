import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        String filePath = args[0];
        Properties properties = loadProperties(filePath);
        if (properties == null) return;

        setupLogger();

        String mode = properties.getProperty("mode", "").toLowerCase();
        if (!isValidMode(mode)) return;

        String suffix = properties.getProperty("suffix");
        if (suffix == null) {
            logger.severe("No suffix is configured");
            return;
        }

        String[] fileNames = getFileNames(properties);
        if (fileNames == null) return;

        processFiles(mode, suffix, fileNames);
    }

    private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch(IOException e) {
            logger.severe("Failed to load properties file");
            return null;
        }
        return properties;
    }

    private static void setupLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
    }

    private static boolean isValidMode(String mode) {
        if (!mode.equals("copy") && !mode.equals("move")) {
            logger.severe("Mode is not recognized: " + mode);
            return false;
        }
        return true;
    }

    private static String[] getFileNames(Properties properties) {
        String filePaths = properties.getProperty("files");
        if (filePaths == null || filePaths.isEmpty()) {
            logger.warning("No files are configured to be copied/moved");
            return null;
        }
        return filePaths.split(":");
    }

    private static void processFiles(String mode, String suffix, String[] fileNames) {
        for (String fileName : fileNames) {
            Path originPath = Paths.get(fileName);
            String suffixFile = getSuffixFile(fileName, suffix);
            Path destinyPath = Paths.get(suffixFile);
            try {
                if (mode.equals("copy")) {
                    Files.copy(originPath, destinyPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.move(originPath, destinyPath, StandardCopyOption.REPLACE_EXISTING);
                }
                logger.info(fileName + " -> " + destinyPath);
            } catch (IOException e) {
                logger.severe("No such file: " + fileName);
            }
        }
    }

    private static String getSuffixFile(String fileName, String suffix) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, lastIndex) + suffix + fileName.substring(lastIndex);
    }
}