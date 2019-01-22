package Extracting_Cookies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] requestLine = reader.readLine().split("\\s+");
        Map<String, String> requestHeaders = getRequestHeadersAsMap(reader);
        Map<String, String> cookies = extractCookies(requestHeaders);
        printResult(cookies);
    }

    private static void printResult(Map<String, String> cookies) {
        cookies.forEach((key, value) -> System.out.println(String.format("%s <-> %s", key, value)));
    }

    private static Map<String, String> getRequestHeadersAsMap(BufferedReader reader) throws IOException {
        Map<String, String> requestHeaders = new LinkedHashMap<>();
        String newLine;
        while ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            requestHeaders.put(newLine.split(":\\s")[0], newLine.split(":\\s")[1]);
        }
        return requestHeaders;
    }

    private static Map<String, String> extractCookies(Map<String, String> requestHeaders) {
        return requestHeaders.entrySet().stream().noneMatch(x -> x.getKey().equals("Cookie"))
                ? new LinkedHashMap<>() :
                Arrays.stream(requestHeaders.get("Cookie")
                        .split(";\\s"))
                        .filter(x -> !x.equals(""))
                        .map(x -> x.split("="))
                        .collect(Collectors.toMap(x -> x[0], x -> x[1], (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
