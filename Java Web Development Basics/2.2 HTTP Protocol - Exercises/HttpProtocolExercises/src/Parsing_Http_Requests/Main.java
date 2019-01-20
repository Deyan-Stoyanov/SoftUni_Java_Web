package Parsing_Http_Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> paths = Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());
        String[] requestLine = reader.readLine().split("\\s+");
        String method = requestLine[0];
        String url = requestLine[1];
        String httpVersion = requestLine[2];
        List<String> requestHeaders = getInputAsList(reader);
        String body = setBody(requestHeaders);
        String[] responseCodeAndMessage = setResponseCodeAndMessage(paths, url, requestHeaders, body, method);
        String responseCode = responseCodeAndMessage[0];
        String responseMessage = responseCodeAndMessage[1];
        String output = configureOutput(requestHeaders, responseMessage, httpVersion, responseCode);
        System.out.println(output);
    }

    private static String setBody(List<String> requestHeaders) {
        String body = "";
        if (requestHeaders.get(requestHeaders.size() - 1).equals("\r\n")) {
            requestHeaders.remove("\r\n");
        } else {
            body = requestHeaders.get(requestHeaders.size() - 1);
            requestHeaders.remove(body);
            requestHeaders.remove("\r\n");
        }
        return body;
    }

    static List<String> getInputAsList(BufferedReader reader) throws IOException {
        List<String> requestHeaders = new ArrayList<>();
        String newLine;
        while ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            requestHeaders.add(newLine);
        }
        requestHeaders.add(System.lineSeparator());
        if ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            requestHeaders.add(newLine);
        }
        return requestHeaders;
    }

    static String configureOutput(List<String> requestHeaders, String responseMessage, String httpVersion, String responseCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(httpVersion)
                .append(" ")
                .append(responseCode)
                .append(System.lineSeparator())
                .append(requestHeaders.stream()
                        .filter(x -> x.startsWith("Date") || x.startsWith("Host") || x.startsWith("Content-Type"))
                        .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(responseMessage);
        return sb.toString();
    }

    static String[] setResponseCodeAndMessage(List<String> paths, String url, List<String> requestHeaders, String body, String method) {
        String responseCode;
        String responseMessage;
        if (!paths.contains(url)) {
            responseCode = "404 Not Found";
            responseMessage = "The requested functionality was not found.";
        } else if (requestHeaders.stream().noneMatch(x -> x.startsWith("Authorization"))) {
            responseCode = "401 Unauthorized";
            responseMessage = "You are not authorized to access the requested functionality.";
        } else if (method.toUpperCase().equals("POST") && body.equals("")) {
            responseCode = "400 Bad Request";
            responseMessage = "There was an error with the requested functionality due to malformed request.";
        } else {
            responseCode = "200 OK";
            String[] bodyParameters = body.split("[=&]");
            String encodedUsername = requestHeaders.stream().filter(x -> x.startsWith("Authorization")).findFirst().get().split("\\s+")[2];
            String username = new String(Base64.getDecoder().decode(encodedUsername));
            responseMessage = String.format("Greetings %s! You have successfully created %s with %s - %s, %s - %s.", username, bodyParameters[1], bodyParameters[2], bodyParameters[3], bodyParameters[4], bodyParameters[5]);
        }
        return new String[]{responseCode, responseMessage};
    }
}