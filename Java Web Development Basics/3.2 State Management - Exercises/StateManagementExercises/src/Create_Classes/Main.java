package Create_Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String parsedInput = parseInput(reader);
        HttpRequest request = new HttpRequestImpl(parsedInput);
        request.getCookies().forEach(x -> System.out.println(String.format("%s <-> %s", x.getKey(), x.getValue())));
    }

    private static String parseInput(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String newLine;
        while ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            sb.append(newLine).append("\r\n");
        }
        sb.append("<CRLF>\r\n");
        if ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            sb.append(newLine);
        }
        return sb.toString();
    }
}
