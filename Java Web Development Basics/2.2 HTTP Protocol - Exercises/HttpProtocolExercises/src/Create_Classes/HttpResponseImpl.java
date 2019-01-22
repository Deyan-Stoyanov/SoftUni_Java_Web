package Create_Classes;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HttpResponseImpl implements HttpResponse {

    private static final Map<Integer, String> STATUS_CODES_AND_STATUS_PHRASES = new LinkedHashMap<>() {{
        put(200, "OK");
        put(400, "Bad request");
        put(401, "Unauthorized");
        put(404, "Not Found");
    }};

    private Map<String, String> headers;
    private int statusCode;
    private byte[] content;

    public HttpResponseImpl(HttpRequest request, List<String> validUrls) {
        this.checkStatus(request, validUrls);
        this.headers = new LinkedHashMap<>(request.getHeaders());
    }

    private void checkStatus(HttpRequest request, List<String> validUrls) {
        if (!validUrls.contains(request.getRequestUrl())) {
            this.setStatusCode(404);
            this.setContent("The requested functionality was not found.".getBytes());
        } else if (request.getHeaders().keySet().stream().noneMatch(x -> x.equals("Authorization"))) {
            this.setStatusCode(401);
            this.setContent("You are not authorized to access the requested functionality.".getBytes());
        } else if (request.getMethod().toUpperCase().equals("POST") && request.getBodyParameters().size() == 0) {
            this.setStatusCode(400);
            this.setContent("There was an error with the requested functionality due to malformed request.".getBytes());
        } else {
            this.setStatusCode(200);
            String username = this.decodeUsername(request.getHeaders());
            List<String> parametersAsString = new ArrayList<>();
            for (String key : request.getBodyParameters().keySet().stream().skip(1).collect(Collectors.toList())) {
                parametersAsString.add(key + " - " + request.getBodyParameters().get(key));
            }
            this.setContent(String.format("Greetings %s! You have successfully created %s with %s.", username, request.getBodyParameters().get("name"), String.join(", ", parametersAsString)).getBytes());
        }
    }

    private String decodeUsername(Map<String, String> headers) {
        String encodedUsername = headers.get("Authorization").split("\\s+")[1];
        return new String(Base64.getDecoder().decode(encodedUsername));
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return this.toString().getBytes();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1")
                .append(" ")
                .append(this.getStatusCode())
                .append(" ")
                .append(STATUS_CODES_AND_STATUS_PHRASES.get(this.getStatusCode()))
                .append(System.lineSeparator());
        if (this.getHeaders().keySet().stream().noneMatch(x -> x.equals("Date"))) {
            sb.append("Date: ")
                    .append(new SimpleDateFormat("dd/MM/yyyy").format(new Date()))
                    .append(System.lineSeparator());
        }
        sb.append(this.getHeaders().entrySet().stream()
                .filter(x -> x.getKey().equals("Date") || x.getKey().equals("Host") || x.getKey().equals("Content-Type"))
                .map(x -> x.getKey() + ": " + x.getValue())
                .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(new String(this.getContent()));
        return sb.toString();
    }
}
