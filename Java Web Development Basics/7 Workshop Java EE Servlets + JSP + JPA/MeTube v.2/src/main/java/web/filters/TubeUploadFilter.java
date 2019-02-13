package web.filters;

import domain.enums.TubeStatus;
import domain.model.binding.TubeUploadBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/upload-tube")
public class TubeUploadFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().toUpperCase().equals("POST")) {
            TubeUploadBindingModel model = new TubeUploadBindingModel(
                    req.getParameter("title"),
                    req.getParameter("author"),
                    req.getParameter("youtube-link"),
                    req.getParameter("description"),
                    (String) req.getSession().getAttribute("username"),
                    TubeStatus.PENDING
            );

            req.setAttribute("model", model);
        }
        chain.doFilter(req, resp);
    }
}
