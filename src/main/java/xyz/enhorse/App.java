package xyz.enhorse;

import xyz.enhorse.commons.PathEx;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         08/10/16
 */
public class App {
    public static void main(String[] args) {
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

            System.out.format("<!--Table of Contents for \'%s\'-->\n", input.filename());

            if (input.isExistingFile()) {
                try (Parser parser = new FileParser(commandLine.marker(), input.toFile());
                     Recorder recorder = new StreamRecorder(System.out)) {

                    Producer producer = new Producer(parser, recorder);
                    producer.produce("+ [%s](#%s)\n");

                    System.out.println("<!--End Of TOC-->\n");
                } catch (IOException ex) {
                    System.out.println("Error processing the file \'" + file + "\': " + ex.getMessage());
                }
            } else {
                System.out.println("Skip the missing file \'" + input + '\'');
            }
        }
    }


    private static void exit() {
        System.out.println("Table Of Contents Generator");
        System.out.println("===========================");
        CommandLine.showHelp();
        System.exit(42);
    }
}
