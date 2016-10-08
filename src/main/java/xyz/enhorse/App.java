package xyz.enhorse;

import xyz.enhorse.commons.PathEx;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class App {

    private static final String CONTENTS_FILE_PREFIX = "contents_for_";


    public static void main(String[] args) {
        System.out.println("Table Of Contents Generator");
        System.out.println("===========================");

        if (args.length == 0) {
            exit();
        }

        CommandLine commandLine = new CommandLine(args);

        if (commandLine.marker() == null) {
            System.out.println("Marker for content sections is not specified.");
            exit();
        }

        if (commandLine.files().length == 0) {
            System.out.println("Files for processing aren't specified. Nothing to do.");
            exit();
        }

        for (File file : commandLine.files()) {
            PathEx input = new PathEx(file);

            if (input.isExistingFile()) {
                PathEx output = input.changeName(CONTENTS_FILE_PREFIX + input.name());

                try (Parser parser = new FileParser(commandLine.marker(), input.toFile());
                     Recorder recorder = new FileRecorder(output.toFile())) {

                    Producer producer = new Producer(parser, recorder);
                    producer.produce("+ [%s](#%s)\n");

                    System.out.println("\'" + output.filename() + "\' has been successfully created.");

                } catch (IOException ex) {
                    System.out.println("Error processing the file \'" + file + "\': " + ex.getMessage());
                }
            } else {
                System.out.println("Skip the missing file \'" + input + '\'');
            }
        }
    }


    private static void exit() {
        CommandLine.showHelp();
        System.exit(42);
    }
}
