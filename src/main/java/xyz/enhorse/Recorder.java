package xyz.enhorse;

import java.io.Closeable;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public interface Recorder extends Closeable {

    void record(final String string);
}
