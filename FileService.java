import java.io.*;
import java.util.*;

import codes.FindAllCodes;
import airport.GetAirportName;
import airport.GetCityName;
import airport.GetIndexesFromCSV;
import file.FormatInput;
import file.ReadFile;
import file.WriteOutput;
import date.FormatDataAndTime;

public class FileService {
    public boolean checkIfFileNotExist(String path) {
        File f = new File(path);
        return !f.exists() || f.isDirectory();
    }

    public String readFile(String path) {
        ReadFile readFileObj = new ReadFile();
        return readFileObj.readFile(path);
    }

    public String formatDataAndTime(String text, String regex, String format) {
        FormatDataAndTime formatDateAndTime = new FormatDataAndTime();
        return formatDateAndTime.formatDataAndTime(text, regex, format);
    }

    public List<String> findAllCodes(String input, String type) {
        FindAllCodes findAllCodes = new FindAllCodes();
        return findAllCodes.findAllCodes(input, type);
    }

    public Map<String, Integer> getIndexesFromCSV(String airportLookup) {
        GetIndexesFromCSV getIndexesFromCSV = new GetIndexesFromCSV(); // Создаем объект класса GetIndexesFromCSV
        return getIndexesFromCSV.getIndexesFromCSV(airportLookup); // Вызываем метод getIndexesFromCSV
    }

    public String getAirportName(String airportLookup, String input, List<String> codes,
            Map<String, Integer> indexesCSV, String typeCSVIndex) {
        GetAirportName getAirportName = new GetAirportName();

        return getAirportName.getAirportName(airportLookup, input, codes, indexesCSV, typeCSVIndex);
    }

    public String getCityName(String airportLookup, String input, List<String> codes, Map<String, Integer> indexesCSV,
            String typeCSVIndex) {

        GetCityName getCityName = new GetCityName();
        return getCityName.getCityName(airportLookup, input, codes, indexesCSV, typeCSVIndex);
    }

    public String formatInput(String inputPath, String outputPath, String airportLookup) {
        FormatInput formatInput = new FormatInput();
        return formatInput.formatInput(inputPath, outputPath, airportLookup);
    }

}
