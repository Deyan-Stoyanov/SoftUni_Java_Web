package web.servlets;

import domain.models.service.ProductServiceModel;
import service.ProductService;
import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String CREATE_PRODUCT_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\5.2 Java EE Servlet API 4.0 - Exercises\\JavaServletExercises\\src\\main\\resources\\views\\create.html";
    private final ProductService productService;
    private final HtmlReader reader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader reader) {
        this.productService = productService;
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.reader.readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH);
        resp.getWriter().println(html);
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel(
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("type")
        );
        this.productService.saveProduct(productServiceModel);
        resp.sendRedirect("/products/all");
    }
}
