package xyz.enhorse;

import xyz.enhorse.commons.Validate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class FileParser extends StreamParser {

    private final File file;


    public FileParser(final String marker, final File input) {
        super(marker, initialize(input));
        file = input;
    }


    @Override
    public String toString() {
        return String.valueOf(file);
    }


    private static InputStream initialize(final File input) {
        checkFile(input);

        try {
            return new FileInputStream(input);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Initialization error with the file \'" + input + '\'');
        }
    }


    private static void checkFile(final File file) {
        Validate.required("Input markdown file for processing", file);

        if (!file.exists()) {
            throw new IllegalArgumentException('\'' + file.getName() + "\' doesn't exist.");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException('\'' + file.getName() + "\' must be a file.");
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException('\'' + file.getName() + "\' isn't readable.");
        }
    }
}
