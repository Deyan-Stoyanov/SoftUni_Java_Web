package servlets;

import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("*.css")
public class StyleServlet extends HttpServlet {
    private static final String CSS_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.1 Introduction to Java EE\\IntroToJavaEE\\src\\main\\resources\\style";
    private final HtmlReader reader;

    @Inject
    public StyleServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        File folder = new File(CSS_FILE_PATH);
        resp.setContentType("text/css");
        for (File file : folder.listFiles()) {
            writer.println(this.reader.readFile(file.getPath()));
        }
        writer.close();
    }
}
