package servlets;

import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.1 Introduction to Java EE\\IntroToJavaEE\\src\\main\\resources\\login.html";
    private final HtmlReader reader;

    @Inject
    public LoginServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.reader.readFile(LOGIN_HTML_FILE_PATH));
        resp.getWriter().close();
    }
}
