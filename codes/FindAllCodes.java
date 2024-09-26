package codes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAllCodes {

    public List<String> findAllCodes(String input, String type) {
        List<String> codes = new ArrayList<String>();

        Pattern p = Pattern.compile(type);
        Matcher m = p.matcher(input);

        while (m.find()) {
            codes.add(m.group());
        }

        return codes;
    }

}
