# Command Line Itinerary Prettifier

[![JavaDocs](https://img.shields.io/badge/JavaDocs-Reference-blue)](https://github.com/yourusername/itinerary-prettifier)

## Table of contents

🚀 [What does the tool do?](#what-does-the-tool-do)<br/>
💡 [How to start using the tool](#how-to-start-using-the-tool)<br/>
💾 [How the data is processed](#how-the-data-is-processed)<br/>
⚙️ [Tool functionality](#tool-functionality)<br/>
❔ [Error handling](#error-handling)<br/>
🛠️ [Testing Requirements](#testing-requirements)<br/>
☕ [Version](#version)<br/>
🤝 [Creator](#creator)

## What does the tool do?

The Itinerary Prettifier is a tool that formats flight itineraries to make them easier to read and saves the result in a new file, helping staff quickly prepare neat versions for customers.

## How to start using the tool

To use the tool, run the program with these three inputs:

1. Path to the input itinerary file.
2. Path to the output file for the formatted itinerary.
3. Path to the airport lookup CSV file.

### Example usage:

- Clone Github repository:

```bash
$ git clone https://gitea.kood.tech/codewithtim/itinerary
```

- Move to project folder:

```bash
$ cd itinerary
```

- Run code:

```bash
$ java Prettifier.java ./input.txt ./output.txt ./airports_lookup.csv
```

- Run code and show output.txt in console:

```bash
$ java Prettifier.java -o ./input.txt ./output.txt ./airports_lookup.csv
```

- Display usage information, run:

```bash
$ java Prettifier.java -h
```

## How the data is processed

- Airport Code Conversion: Converts IATA and ICAO codes into airport names using a CSV file. If a code isn't found, it's left unchanged.

- Date and Time Formatting:

  - ISO 8601 dates become "DD-Mmm-YYYY" (e.g., "05 Apr 2007").
  - Times are formatted in 12-hour or 24-hour with AM/PM and timezones.

- Whitespace Trimming: Removes extra blank spaces, ensuring no more than two consecutive empty lines.

## Tool functionality

- Reads itineraries from text files and saves improved versions to new files.
- Looks up airport codes using a flexible CSV file, allowing different column arrangements.
- Supports both IATA and ICAO codes, and recognizes city names marked with an asterisk(\*).
- Formats dates and times for easier reading.

## Error handling

- If the input file does not exist, the output will show: _`Input not found`_.
- If the airport lookup file does not exist, the output will show: _`Airport lookup not found`_.
- If the airport data is malformed (e.g., missing columns or blank cells), the output will show: _`Airport lookup malformed`_.
- In any case of an error, the output file will not be created or overwritten.

## Testing Requirements

### 1. **ISO Dates and Times**:

- Create an input file with the following lines:

```bash
1. D(2022-05-09T08:07Z)
2. T12(2069-04-24T19:18-02:00)
3. T12(2080-05-04T14:54Z)
4. T12(1980-02-17T03:30+11:00)
5. T12(2029-09-04T03:09Z)
6. T24(2032-07-17T04:08+13:00)
7. T24(2084-04-13T17:54Z)
8. T24(2024-07-23T15:29-11:00)
9. T24(2042-09-01T21:43Z)
```

- Run the program, and the output file should be:

```bash
1. 09 May 2022
2. 07:18PM (-02:00)
3. 02:54PM (+00:00)
4. 03:30AM (+11:00)
5. 03:09AM (+00:00)
6. 04:08 (+13:00)
7. 17:54 (+00:00)
8. 15:29 (-11:00)
9. 21:43 (+00:00)
```

### 2. **Converting IATA and ICAO codes to airport names**:

- Create a file containing the text:

```bash
Your flight departs from #HAJ, and your destination is ##EDDW.
```

- Run the program, check the content of the output file. It should contain:

```bash
Your flight departs from Hannover Airport, and your destination is Bremen Airport.
```

### File Validation Tests

- Take a copy of an airport lookup, remove the name column, and run the code with an input containing a valid airport name conversion. Ensure the output file is not created.
- Create a file containing a short phrase and use that file as the output path. Repeat the above steps to ensure the output file was not overwritten.

### Additional Tests

- Whitespace Handling: Verify that \v, \f, and \r are converted to \n, and excessive blank lines are trimmed.
- Code Conversion: Ensure random IATA and ICAO codes are correctly mapped to airport names.
- Non-Standard Column Order: Test the tool's ability to handle arbitrary airport lookup column orders.
- Bonus Features: Showcase any extra features, like color formatting, highlighting timezone offsets and -o flag.

## Version

- Java 21.0.4 2024-07-16 LTS

## Creator

- Timofey Babisashvili <br/>
