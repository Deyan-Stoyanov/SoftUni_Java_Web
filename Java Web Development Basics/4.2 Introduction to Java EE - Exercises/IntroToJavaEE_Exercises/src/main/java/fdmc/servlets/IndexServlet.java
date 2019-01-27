package fdmc.servlets;

import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private static final String INDEX_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.2 Introduction to Java EE - Exercises\\IntroToJavaEE_Exercises\\src\\main\\resources\\index.html";
    private final HtmlReader reader;

    @Inject
    public IndexServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readHtmlFile(INDEX_HTML_FILE_PATH));
        writer.close();
    }
}
