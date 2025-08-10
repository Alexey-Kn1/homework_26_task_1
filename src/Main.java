import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String INSTALLATION_PATH = "./installation";
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) throws Exception {
        StringBuilder logs = new StringBuilder();
        boolean installationFinished = false;

        try {
            logs.append(String.format("installation to '%s' began\n", INSTALLATION_PATH));

            // Пункт 1:

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "src", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "res", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "savegames", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "temp", logs)) {
                return;
            }

            // Пункт 2:

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "src" + PATH_SEPARATOR + "main", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "src" + PATH_SEPARATOR + "test", logs)) {
                return;
            }

            // Пункт 3:

            if (!createEmptyFile(INSTALLATION_PATH + PATH_SEPARATOR + "src" + PATH_SEPARATOR + "main", "Main.java",
                    logs)) {
                return;
            }

            if (!createEmptyFile(INSTALLATION_PATH + PATH_SEPARATOR + "src" + PATH_SEPARATOR + "main", "Utils.java",
                    logs)) {
                return;
            }

            // Пункт 4:

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "res" + PATH_SEPARATOR + "drawables", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "res" + PATH_SEPARATOR + "vectors", logs)) {
                return;
            }

            if (!createDir(INSTALLATION_PATH + PATH_SEPARATOR + "res" + PATH_SEPARATOR + "icons", logs)) {
                return;
            }

            logs.append(String.format("installation to '%s' finished\n", INSTALLATION_PATH));

            installationFinished = true;
        } finally {
            if (!installationFinished) {
                logs.append(String.format("installation to '%s' failed\n", INSTALLATION_PATH));
            }

            // Пункт 5:

            File f = new File(INSTALLATION_PATH + PATH_SEPARATOR + "temp", "temp.txt");

            f.createNewFile();

            try (FileWriter w = new FileWriter(f)) {
                w.write(logs.toString());
            }
        }
    }

    private static boolean createDir(String dirPath, StringBuilder logs) {
        File f = new File(dirPath);

        if (f.exists()) {
            logs.append(String.format("directory '%s' already exists\n", dirPath));

            return false;
        }

        boolean success = f.mkdir();

        logs.append(
                success ? String.format("directory '%s' created successfully\n", dirPath)
                        : String.format("cannot create directory '%s'\n", dirPath));

        return success;
    }

    private static boolean createEmptyFile(String dirPath, String fileName, StringBuilder logs) {
        String fileFullPath = dirPath + (dirPath.endsWith(PATH_SEPARATOR) ? "" : PATH_SEPARATOR) + fileName;

        File f = new File(dirPath, fileName);

        if (f.exists()) {
            logs.append(String.format("file '%s' already exists\n", fileFullPath));

            return false;
        }

        try {
            boolean success = f.createNewFile();

            logs.append(
                    success ? String.format("file '%s' created successfully\n", fileFullPath)
                            : String.format("cannot create file '%s'\n", fileFullPath));

            return success;
        } catch (IOException ex) {
            logs.append(String.format("cannot create file '%s': %s\n", fileFullPath, ex.toString()));

            return false;
        }
    }
}
