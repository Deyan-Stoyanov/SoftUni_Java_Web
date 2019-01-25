package fdmc.web.servlets;

import fdmc.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {

    private static final String ALL_CATS_HTML_FILE = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.2 Introduction to Java EE - Exercises\\IntroToJavaEE_Exercises\\src\\main\\resources\\views\\all-cats.html";
    private final HtmlReader reader;

    @Inject
    public CatAllServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.reader.readHtmlFle(ALL_CATS_HTML_FILE);
        if (req.getSession().getAttribute("cats") == null) {
            html = html
                    .replace("{{allCats}}", "<h2>There are no cats. <a href=\"/cats/create\">Create some!</a></h2>");
        } else {
            List<Cat> cats = new ArrayList<>(((Map<String, Cat>) req.getSession().getAttribute("cats")).values());
            html = html
                    .replace("{{allCats}}", cats.stream()
                            .map(x -> String.format("<h4><a href=\"cats/profile?name=%s\">%s</a></h4>",
                                    x.getName(), x.getName())).collect(Collectors.joining("<br/>")));
        }
        resp.getWriter().println(html);
        resp.getWriter().close();
    }
}