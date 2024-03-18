import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        if (args.length == 0) {
            logger.severe("No file path provided");
            return;
        }

        File file = new File(args[0]);
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
        } catch(IOException e) {
            logger.severe("Error loading properties: " + e.getMessage());
            return;
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());

        String mode = properties.getProperty("mode");
        if (!"copy".equalsIgnoreCase(mode) && !"move".equalsIgnoreCase(mode)) {
            logger.severe("Mode is not recognized: " + mode);
            return;
        }

        String suffix = properties.getProperty("suffix");
        if (suffix == null) {
            logger.severe("No suffix is configured");
            return;
        }

        String file_paths = properties.getProperty("files");
        if (file_paths == null) {
            logger.warning("No files are configured to be copied/moved");
            return;
        }

        String[] file_names = file_paths.split(":");
        if (file_names[0].length() == 0) {
            logger.warning("No files are configured to be copied/moved");
            return;
        }

        for (String file_name : file_names) {
            Path origin_path = Paths.get(file_name);
            int last_index = file_name.lastIndexOf(".");
            StringBuilder suffix_file = new StringBuilder(file_name.substring(0, last_index));
            suffix_file.append(suffix);
            suffix_file.append(file_name.substring(last_index));
            Path destiny_path = Paths.get(suffix_file.toString());
            try {
                if ("copy".equalsIgnoreCase(mode)) {
                    Files.copy(origin_path, destiny_path, StandardCopyOption.REPLACE_EXISTING);
                    logger.info(file_name + " -> " + destiny_path);
                } else {
                    Files.move(origin_path, destiny_path, StandardCopyOption.REPLACE_EXISTING);
                    logger.info(file_name + " => " + destiny_path);
                }
            } catch (IOException e) {
                logger.severe("No such file: " + file_name);
            }
        }
    }
}