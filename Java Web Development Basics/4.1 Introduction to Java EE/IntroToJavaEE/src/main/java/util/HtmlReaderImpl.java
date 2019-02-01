package util;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
