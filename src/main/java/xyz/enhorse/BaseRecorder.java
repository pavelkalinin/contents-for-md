package xyz.enhorse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class BaseRecorder implements Recorder {

    private final BufferedWriter writer;


    public BaseRecorder(final Writer writer) {
        this.writer = initialize(writer);
    }


    @Override
    public void record(final String string) {
        try {
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Error record the string: \'" + string + '\'');
        }
    }


    @Override
    public void close() throws IOException {
        writer.close();
    }


    private static BufferedWriter initialize(final Writer writer) {
        if (writer == null) {
            throw new IllegalStateException("Initialization error: Writer is null");
        }

        return (writer instanceof BufferedWriter)
                ? (BufferedWriter) writer
                : new BufferedWriter(writer);
    }
}
