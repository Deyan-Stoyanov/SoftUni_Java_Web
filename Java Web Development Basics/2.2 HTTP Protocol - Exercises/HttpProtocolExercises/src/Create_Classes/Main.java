package Create_Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls(reader);
        String parsedInput = parseInput(reader);
        HttpRequest request = new HttpRequestImpl(parsedInput);
        HttpResponse response = new HttpResponseImpl(request, validUrls);
        System.out.println(response);
    }

    private static List<String> getValidUrls(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());
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
