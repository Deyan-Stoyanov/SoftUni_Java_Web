package web.servlets;

import domain.models.view.ProductViewModel;
import service.ProductService;
import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class DetailsServlet extends HttpServlet {
    private static final String PRODUCT_DETAILS_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\5.2 Java EE Servlet API 4.0 - Exercises\\JavaServletExercises\\src\\main\\resources\\views\\details.html";
    private final ProductService productService;
    private final HtmlReader reader;

    @Inject
    public DetailsServlet(ProductService productService, HtmlReader reader) {
        this.productService = productService;
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductViewModel productViewModel = this.productService.findProductByName(req.getQueryString().split("=")[1]);
        String html = this.reader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH)
                .replace("{{name}}", productViewModel.getName())
                .replace("{{description}}", productViewModel.getDescription())
                .replace("{{type}}", productViewModel.getType());
        resp.getWriter().println(html);
        resp.getWriter().close();
    }
}
