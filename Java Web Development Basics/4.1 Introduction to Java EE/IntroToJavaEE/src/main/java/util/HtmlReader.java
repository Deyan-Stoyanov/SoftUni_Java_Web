package util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface HtmlReader {
    String readFile(String path) throws IOException;
}
