package fdmc.web.servlets;

import fdmc.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateServlet extends HttpServlet {
    private static final String CREATE_CATS_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.2 Introduction to Java EE - Exercises\\IntroToJavaEE_Exercises\\src\\main\\resources\\views\\create.html";
    private final HtmlReader reader;

    @Inject
    public CreateServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readHtmlFle(CREATE_CATS_HTML_FILE_PATH));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat(
                req.getParameter("name"),
                req.getParameter("breed"),
                req.getParameter("color"),
                Integer.parseInt(req.getParameter("numberOfLegs"))
        );
        if(req.getSession().getAttribute("cats") == null){
            req.getSession().setAttribute("cats", new LinkedHashMap<String, Cat>());
        }
        ((Map<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
        resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
    }
}
