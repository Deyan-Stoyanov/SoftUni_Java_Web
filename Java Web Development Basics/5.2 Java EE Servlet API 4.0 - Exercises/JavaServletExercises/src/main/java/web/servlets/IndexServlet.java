package web.servlets;

import domain.models.view.AllProductsViewModel;
import service.ProductService;
import util.HtmlReader;
import util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns={"/", "/products/all"})
public class IndexServlet extends HttpServlet {
    private static final String INDEX_HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\5.2 Java EE Servlet API 4.0 - Exercises\\JavaServletExercises\\src\\main\\resources\\views\\index.html";
    private final ProductService productService;
    private final HtmlReader reader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader reader, ModelMapper modelMapper) {
        this.productService = productService;
        this.reader = reader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.reader.readHtmlFile(INDEX_HTML_FILE_PATH);
        List<AllProductsViewModel> viewModels = this.productService.allProducts();
        StringBuilder sb = new StringBuilder();
        viewModels.stream()
                .map(product -> String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>", product.getName(), product.getName()))
                .forEach(p -> sb.append(p).append(System.lineSeparator()));
        resp.getWriter().println(html.replace("{{products}}", sb.toString()));
        resp.getWriter().close();
    }
}