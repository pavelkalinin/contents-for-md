package xyz.enhorse;

import xyz.enhorse.commons.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class BaseParser implements Parser {

    private final String marker;
    private final BufferedReader reader;


    public BaseParser(final String marker, final Reader reader) {
        this.marker = Validate.required("Marker for a contents entry", marker);
        this.reader = initialize(reader);
    }


    @Override
    public boolean hasNext() {
        try {
            while (true) {
                reader.mark(Short.MAX_VALUE);

                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                if (line.trim().startsWith(marker)) {
                    reader.reset();
                    return true;
                }
            }

            return false;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public String next() {
        try {
            return trimMarker(reader.readLine());
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    private String trimMarker(final String string) {
        return string.trim().substring(marker.length());
    }


    @Override
    public void close() throws IOException {
        reader.close();
    }


    private static BufferedReader initialize(final Reader reader) {
        if (reader == null) {
            throw new IllegalStateException("Initialization error: Reader is null");
        }

        return (reader instanceof BufferedReader)
                ? (BufferedReader) reader
                : new BufferedReader(reader);
    }
}
