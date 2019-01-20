package Create_Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String newLine;
        while ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            sb.append(newLine).append("\r\n");
        }
        sb.append("<CRLF>\r\n");
        if ((newLine = reader.readLine()) != null && newLine.length() > 0) {
            sb.append(newLine);
        }
        HttpRequest request = new HttpRequestImpl(sb.toString());
        HttpResponse response = new HttpResponseImpl(request);
        System.out.println(response.toString());
    }
}
