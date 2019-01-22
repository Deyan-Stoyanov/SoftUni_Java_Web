package Create_Classes;

public class HttpCookie {
    private String key;
    private String value;

    public HttpCookie(String key, String value) {
        this.setKey(key);
        this.setValue(value);
    }

    public String getKey() {
        return this.key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
