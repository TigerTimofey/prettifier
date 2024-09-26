package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class WriteOutput {

    public void writeOutput(String inputContent, String outputPath) {
        try {
            BufferedReader input = new BufferedReader(new StringReader(inputContent));
            BufferedWriter output = new BufferedWriter(new FileWriter(outputPath));
            int newLineCount = 0;
            boolean lastCharWasNewline = false;

            int ch;
            while ((ch = input.read()) != -1) {
                char currentChar = (char) ch;

                if (currentChar == '\u000B' || currentChar == '\f' || currentChar == '\r') {
                    currentChar = '\n';
                }

                if (currentChar == '\n') {
                    if (lastCharWasNewline && newLineCount > 1) {
                        continue;
                    }
                    newLineCount++;
                    lastCharWasNewline = true;
                }

                if (!(currentChar == '\n')) {
                    newLineCount = 0;
                    lastCharWasNewline = false;
                }

                output.write(currentChar);
            }

            input.close();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
