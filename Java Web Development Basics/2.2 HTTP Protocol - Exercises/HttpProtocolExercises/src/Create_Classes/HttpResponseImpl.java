package Create_Classes;

import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
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

    public HttpResponseImpl(HttpRequest request) {
        this.checkStatus(request);
        this.headers = new LinkedHashMap<>(request.getHeaders());
    }

    private void checkStatus(HttpRequest request) {
        if (!request.isResource()) {
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
            this.setContent(String.format("Greetings %s!", request.getBodyParameters().get("username")).getBytes());
        }
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
                .append(System.lineSeparator())
                .append(this.getHeaders().entrySet().stream()
                        .filter(x -> x.getKey().equals("Date") || x.getKey().equals("Host") || x.getKey().equals("Content-Type"))
                        .map(x -> x.getKey() + ": " + x.getValue())
                        .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(new String(this.getContent()));
        return sb.toString();
    }
}
