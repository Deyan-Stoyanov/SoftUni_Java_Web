package Create_Classes;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {
    private String method;
    private String requestUrl;
    private String httpVersion;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private Set<HttpCookie> cookies;

    public HttpRequestImpl(String request) {
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new LinkedHashSet<>();
        this.parseData(request);
    }

    private void parseData(String request) {
        this.parseRequestLineData(request);
        this.parseHeaders(request);
        this.parseBodyParameters(request);
        this.parseCookies();
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String getHttpVersion() {
        return this.httpVersion;
    }

    @Override
    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public Set<HttpCookie> getCookies() {
        return Collections.unmodifiableSet(this.cookies);
    }

    @Override
    public boolean isResource() {
        return !this.requestUrl.contains(".");
    }

    private void parseRequestLineData(String request) {
        String[] tokens = request.split("\r\n");
        String[] requestLine = tokens[0].split("\\s+");
        String method = requestLine[0];
        String url = requestLine[1];
        String httpVersion = requestLine[2];
        this.setMethod(method);
        this.setRequestUrl(url);
        this.setHttpVersion(httpVersion);
    }

    private void parseBodyParameters(String request) {
        String[] tokens = request.split("\r\n");
        if (!"<CRLF>".equals(tokens[tokens.length - 1])) {
            String[] bodyParams = tokens[tokens.length - 1].split("[=&]");
            for (int i = 0; i < bodyParams.length - 1; i += 2) {
                this.addBodyParameter(bodyParams[i], bodyParams[i + 1]);
            }
        }
    }

    private void parseHeaders(String request) {
        String[] tokens = request.split("\r\n");
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals("<CRLF>")) {
                break;
            }
            String[] pair = tokens[i].split(":\\s");
            this.addHeader(pair[0], pair[1]);
        }
    }

    private void parseCookies() {
        if (this.getHeaders().entrySet().stream().anyMatch(x -> x.getKey().equals("Cookie"))) {
            Arrays.stream(this.getHeaders().get("Cookie").split("; "))
                    .filter(x -> !x.equals(""))
                    .map(x -> x.split("="))
                    .forEach(x -> this.addCookie(new HttpCookie(x[0], x[1])));
        }
    }
}
