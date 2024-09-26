package airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetAirportName {
    public String getAirportName(String airportLookup, String input, List<String> codes,
            Map<String, Integer> indexesCSV, String typeCSVIndex) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(airportLookup));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (String code : codes) {
                    if (values[indexesCSV.get(typeCSVIndex)].equals(code.substring(code.lastIndexOf("#") + 1))) {
                        input = input.replace(code, values[indexesCSV.get("name")]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return input;
    }
}
