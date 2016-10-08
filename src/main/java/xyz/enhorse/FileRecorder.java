package xyz.enhorse;

import xyz.enhorse.commons.Validate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class FileRecorder extends StreamRecorder {

    private final File file;


    public FileRecorder(final File output) {
        super(initialize(output));
        file = output;
    }


    @Override
    public String toString() {
        return String.valueOf(file);
    }


    private static OutputStream initialize(final File output) {
        prepareFile(output);

        try {
            return new FileOutputStream(output);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Initialization error with the file \'" + output + '\'');
        }
    }


    private static void prepareFile(final File file) {
        Validate.required("Output markdown file for processing", file);

        if (file.exists()) {
            throw new IllegalArgumentException('\'' + file.getName() + "\' already exists.");
        }

        try {
            Files.createFile(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create the file \'" + file.getName() + '\'');
        }
    }
}
