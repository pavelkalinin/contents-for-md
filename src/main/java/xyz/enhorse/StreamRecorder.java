package xyz.enhorse;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class StreamRecorder extends BaseRecorder {

    public StreamRecorder(final OutputStream stream) {
        super(new OutputStreamWriter(stream, StandardCharsets.UTF_8));
    }
}
