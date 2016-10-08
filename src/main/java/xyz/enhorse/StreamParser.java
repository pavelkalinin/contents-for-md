package xyz.enhorse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class StreamParser extends BaseParser {

    public StreamParser(final String marker, final InputStream input) {
        super(marker, new InputStreamReader(input, StandardCharsets.UTF_8));
    }
}
