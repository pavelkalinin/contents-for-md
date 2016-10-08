package xyz.enhorse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
class CommandLine {

    private static final String OPTION_MARKER = "--marker=";

    private String marker;
    private File[] files;


    CommandLine(final String[] args) {
        parse(args);
    }


    String marker() {
        return marker;
    }


    File[] files() {
        return files;
    }


    private void parse(final String[] args) {
        List<File> fileList = new ArrayList<>();

        for (String arg : args) {
            if (arg.startsWith(OPTION_MARKER)) {
                marker = extractMarker(arg);
            } else {
                fileList.add(new File(arg));
            }
        }

        files = fileList.toArray(new File[0]);
    }


    private String extractMarker(final String arg) {
        int start = OPTION_MARKER.length();
        int end = arg.length();

        char markerHead = arg.charAt(start);
        if ((markerHead == '\'') || (markerHead == '\"')) {
            start++;
            end--;
        }

        return arg.substring(start, end);
    }


    static void showHelp() {
        System.out.printf("Table Of Contents Generator");
        System.out.println("Usage:");
        System.out.println("\tfile1.md file2.md ...");
        System.out.format("\t%s\'marker-for-a-contents-entry\' (ex. %s\'##\')", OPTION_MARKER, OPTION_MARKER);
    }


}
