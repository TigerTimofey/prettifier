package date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatDataAndTime {

    public String formatDataAndTime(String text, String regex, String format) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        while (m.find()) {
            String date = m.group().substring(m.group().indexOf("(") + 1, m.group().length() - 1);
            ZonedDateTime dateTime = ZonedDateTime.parse(date);
            String formattedDate = dateTime.format(dateFormatter);

            text = text.replace(m.group(), formattedDate);
        }

        return text;
    }
}
