package servlets;

import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private static final String INDEX_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.1 Introduction to Java EE\\IntroToJavaEE\\src\\main\\resources\\index.html";
    private final HtmlReader reader;

    @Inject
    public HomeServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readFile(INDEX_HTML_FILE_PATH));
        writer.close();
    }
}
