package HTTP_Requester;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class HttpRequester {
    private static OkHttpClient httpClient = new OkHttpClient();
    public static String doGet(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(new URL(url))
                .build();
        Response response = httpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }
}
