package xyz.enhorse;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class Producer {

    private static final String DEFAULT_TEMPLATE = "[%s](#%s)";
    private final Parser in;
    private final Recorder out;


    public Producer(final Parser parser, final Recorder recorder) {
        in = Validate.required("Parser", parser);
        out = Validate.required("Recorder", recorder);
    }


    public void produce(final String template) {
        String format = Validate.defaultIfNullOrEmpty(template, DEFAULT_TEMPLATE);
        try {
            while (in.hasNext()) {
                ContentEntry entry = new ContentEntry(in.next());
                out.record(String.format(format, entry.title(), entry.link()));
            }
        } catch (Exception ex) {
            throw new IllegalStateException(String.format("Compilation error (parser:%s, recorder:%s)", in, out));
        }
    }
}
