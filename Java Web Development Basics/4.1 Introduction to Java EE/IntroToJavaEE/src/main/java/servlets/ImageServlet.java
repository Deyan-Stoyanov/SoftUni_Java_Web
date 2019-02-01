package servlets;

import util.HtmlReader;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("*.png")
public class ImageServlet extends HttpServlet {
    private static final String IMAGE_FOLDER_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\4.1 Introduction to Java EE\\IntroToJavaEE\\src\\main\\resources\\images";
    private final HtmlReader reader;

    @Inject
    public ImageServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream stream = resp.getOutputStream();
        File folder = new File(IMAGE_FOLDER_PATH);
        resp.setContentType("image/png");
        for (File file : folder.listFiles()) {
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "PNG", stream);
        }
        stream.close();
    }
}
