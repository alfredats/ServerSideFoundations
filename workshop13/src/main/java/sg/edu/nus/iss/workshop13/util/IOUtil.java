package sg.edu.nus.iss.workshop13.util;

import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.logging.Level;

public class IOUtil {
    private static final Logger logger 
        = Logger.getLogger(IOUtil.class.getName());
    
    public static void createDir(String path) {
        File dir = new File(path);
        dir.mkdirs();

        String osName = System.getProperty("os.name");
        if (!osName.contains("Windows")) {
            logger.log(Level.INFO, "Others -> ", osName);
            try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions 
                        = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating directory: " + e.getMessage());
            }
        }
    }
}
