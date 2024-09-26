package file;

import java.util.List;
import java.util.Map;

import airport.GetAirportName;
import airport.GetCityName;
import airport.GetIndexesFromCSV;
import codes.FindAllCodes;
import date.FormatDataAndTime;

public class FormatInput {

    public String formatInput(String inputPath, String outputPath, String airportLookup) {

        FormatDataAndTime formatDataAndTime = new FormatDataAndTime();
        ReadFile readFile = new ReadFile();
        FindAllCodes findAllCodes = new FindAllCodes();
        GetAirportName getAirportName = new GetAirportName();
        GetCityName getCityName = new GetCityName();
        WriteOutput writeOutput = new WriteOutput();
        GetIndexesFromCSV getIndexesFromCSV = new GetIndexesFromCSV();

        String inputContent = readFile.readFile(inputPath);
        Map<String, Integer> indexes = getIndexesFromCSV.getIndexesFromCSV(airportLookup);
        inputContent = formatDataAndTime.formatDataAndTime(inputContent, "D\\((.*?)\\)", "dd MMMM yyyy");
        inputContent = formatDataAndTime.formatDataAndTime(inputContent, "T12\\((.*?)\\)", "hh:mma (xxx)");
        inputContent = formatDataAndTime.formatDataAndTime(inputContent, "T24\\((.*?)\\)", "kk:mm (xxx)");

        List<String> citiesICAO = findAllCodes.findAllCodes(inputContent, "\\*##\\w+");
        if (!citiesICAO.isEmpty()) {
            inputContent = getCityName.getCityName(airportLookup, inputContent, citiesICAO, indexes, "icao_code");
        }
        List<String> ICAO = findAllCodes.findAllCodes(inputContent, "##\\w+");
        if (!ICAO.isEmpty()) {
            inputContent = getAirportName.getAirportName(airportLookup, inputContent, ICAO, indexes, "icao_code");
        }
        List<String> citiesIATA = findAllCodes.findAllCodes(inputContent, "\\*#\\w+");
        if (!citiesIATA.isEmpty()) {
            inputContent = getCityName.getCityName(airportLookup, inputContent, citiesIATA, indexes, "iata_code");
        }
        List<String> IATA = findAllCodes.findAllCodes(inputContent, "#\\w+");
        if (!IATA.isEmpty()) {
            inputContent = getAirportName.getAirportName(airportLookup, inputContent, IATA, indexes, "iata_code");
        }

        writeOutput.writeOutput(inputContent, outputPath);
        return inputContent;
    }

}
