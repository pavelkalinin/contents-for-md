package xyz.enhorse;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class ContentEntry {

    private static final char SPACES_FILLER = '-';

    private final String title;
    private final String link;


    public ContentEntry(final String string) {
        title = Validate.notNullOrEmpty("String for a contents entry", string);
        link = convertToLink(string);
    }


    public String title() {
        return title;
    }


    public String link() {
        return link;
    }


    private String convertToLink(final String string) {
        StringBuilder result = new StringBuilder();

        for (char c : string.toCharArray()) {
            if (!isValidSymbol(c)) {
                continue;
            }

            if (isUpperCaseLatinLetter(c)) {
                c = Character.toLowerCase(c);
            } else if (Character.isSpaceChar(c)) {
                c = SPACES_FILLER;
            }

            result.append(c);
        }

        return result.toString();
    }


    @Override
    public int hashCode() {
        return 31 * title.hashCode() + link.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ContentEntry entry = (ContentEntry) o;

        return title.equals(entry.title)
                && link.equals(entry.link);

    }


    @Override
    public String toString() {
        return '[' + title + "](" + link + ')';
    }


    private static boolean isValidSymbol(char c) {
        return (Character.isLetterOrDigit(c)) || (Character.isSpaceChar(c));
    }


    private static boolean isUpperCaseLatinLetter(char c) {
        return (c >= 'A' && c <= 'Z');
    }
}
