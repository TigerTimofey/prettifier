
public class Prettifier {
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        boolean displayOutput;
        String inputPath;
        String outputPath;
        String airportLookup;

        if (!parseArguments(args)) {
            return;
        }

        displayOutput = args[0].equals("-o");
        inputPath = args[displayOutput ? 1 : 0];
        outputPath = args[displayOutput ? 2 : 1];
        airportLookup = args[displayOutput ? 3 : 2];

        FileService fileService = new FileService();

        if (!validateFilePaths(fileService, inputPath, airportLookup)) {
            return;
        }

        try {
            fileService.getIndexesFromCSV(airportLookup);
        } catch (Exception e) {
            printError("Airport lookup malformed");
            return;
        }

        processOutput(fileService, inputPath, outputPath, airportLookup, displayOutput);
    }

    private static boolean parseArguments(String[] args) {
        if (args.length == 4 && args[0].equals("-o")) {
            return true;
        } else if (args.length != 3 || args[0].equals("-h")) {
            showUsage();
            return false;
        }
        return true;
    }

    private static boolean validateFilePaths(FileService fileService, String inputPath, String airportLookup) {
        if (fileService.checkIfFileNotExist(inputPath)) {
            printError("Input not found!");
            return false;
        }
        if (fileService.checkIfFileNotExist(airportLookup)) {
            printError("Airport not found!");
            return false;
        }
        return true;
    }

    private static void processOutput(FileService fileService, String inputPath, String outputPath,
            String airportLookup, boolean displayOutput) {
        if (displayOutput) {
            System.out.println("");
            System.out.println(GREEN + "output.txt" + RESET);
            System.out.println("");
            System.out.println(fileService.formatInput(inputPath, outputPath, airportLookup));
            System.out.println("");
        } else {
            fileService.formatInput(inputPath, outputPath, airportLookup);
        }
    }

    private static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    private static void showUsage() {
        System.out.println("");
        System.out.println(YELLOW + "show help message:" + RESET);
        System.out.println(GREEN + "$ java Prettifier.java" + RESET + PURPLE + " -h" + RESET);
        System.out.println(YELLOW + "show output file:" + RESET);
        System.out.println(GREEN + "$ java Prettifier.java" + RESET + PURPLE + " -o " + RESET + GREEN
                + "./input.txt ./output.txt ./airport-lookup.csv" + RESET);
        System.out.println(YELLOW + "sample itinerary usage:" + RESET);
        System.out.println(GREEN + "$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv" + RESET);
        System.out.println("");
    }
}
