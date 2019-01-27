package fdmc.servlets;

import fdmc.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    private static final String CAT_PROFILE_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.2 Introduction to Java EE - Exercises\\IntroToJavaEE_Exercises\\src\\main\\resources\\cat-profile.html";
    private static final String NOT_FOUND_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.2 Introduction to Java EE - Exercises\\IntroToJavaEE_Exercises\\src\\main\\resources\\non-existent-cat.html";
    private final HtmlReader reader;

    @Inject
    public CatProfileServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats")).get(req.getQueryString().split("=")[1]);
        String html;
        if (cat == null) {
            html = this.reader.readHtmlFile(NOT_FOUND_HTML_FILE_PATH)
                    .replace("{{name}}", req.getQueryString().split("=")[1]);
        } else {
            html = this.reader.readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{name}}", cat.getName())
                    .replace("{{breed}}", cat.getBreed())
                    .replace("{{color}}", cat.getColor())
                    .replace("{{numberOfLegs}}", cat.getNumberOfLegs().toString());
        }
        resp.getWriter().println(html);
        resp.getWriter().close();
    }
}
