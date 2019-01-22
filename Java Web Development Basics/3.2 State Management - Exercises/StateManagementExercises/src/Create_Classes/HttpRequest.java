package Create_Classes;

import java.util.Map;
import java.util.Set;

public interface HttpRequest {
    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    String getHttpVersion();

    void setHttpVersion(String httpVersion);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    boolean isResource();

    void addCookie(HttpCookie cookie);

    Set<HttpCookie> getCookies();
}
