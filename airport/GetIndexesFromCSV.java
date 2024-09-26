package airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class GetIndexesFromCSV {

    public Map<String, Integer> getIndexesFromCSV(String airportLookup) {
        Map<String, Integer> indexes = new HashMap<String, Integer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(airportLookup));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++) {
                    if (values[i].isEmpty()) {
                        throw new Exception("Airport lookup malformed");
                    }

                    switch (values[i]) {
                        case "name" -> indexes.put("name", i);
                        case "iso_country" -> indexes.put("iso_country", i);
                        case "municipality" -> indexes.put("municipality", i);
                        case "icao_code" -> indexes.put("icao_code", i);
                        case "iata_code" -> indexes.put("iata_code", i);
                        case "coordinates" -> indexes.put("coordinates", i);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return indexes;
    }

}
