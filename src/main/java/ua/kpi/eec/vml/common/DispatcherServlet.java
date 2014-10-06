package ua.kpi.eec.vml.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.eec.vml.controller.Controller;
import ua.kpi.eec.vml.controller.ControllerResponse;


@MultipartConfig
public class DispatcherServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response, String httpMethod) {
        //ConfigurationProperties.init("confFilePath", getServletContext().getRealPath("/"));
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestData rd = new HttpRequestData(request);
        ControllerResponse resp = null;

        if (resp.isNoresp()) {
            return;
        }

        if (resp.isRedirect()) {
            try {
                response.sendRedirect(resp.getRedirectAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (resp.isRaw()) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType(resp.getRawContentType());
                if (resp.getContent() != null) {
                    InputStream is = resp.getContent();
                    OutputStream o = response.getOutputStream();
                    byte[] buf = new byte[32 * 1024];
                    int nRead = 0;
                    while ((nRead = is.read(buf)) != -1) {
                        o.write(buf, 0, nRead);
                    }
                    o.flush();
                    o.close();
                    return;
                }
                PrintWriter out = response.getWriter();
                out.write((String) resp.getPageData().get("content"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Calling page uri
        Enumeration<String> pnames = request.getParameterNames();
        String params = "";
        if (pnames.hasMoreElements()) {
            String element = pnames.nextElement();
            params = "?" + element + "=" + request.getParameter(element);
            while (pnames.hasMoreElements()) {
                element = pnames.nextElement();
                params += "&" + element + "=" + request.getParameter(element);
            }
        }
        if (!request.getServletPath().startsWith("/login"))
            request.getSession().setAttribute("calling_page_address",
                    request.getRequestURI() + params);

        // Setting messages
        String lang = (String) request.getSession().getAttribute("lang");
        if (lang == null) {
            request.getSession().setAttribute("lang", "en");
        }

        // Setting attributes
        HashMap<String, Object> attributes = resp.getPageData();
        if (attributes != null) {
            Set<String> keys = attributes.keySet();
            for (String key : keys) {
                request.setAttribute(key, attributes.get(key));
            }
        }
        try {
            request.getRequestDispatcher("/WEB-INF/views/" + resp.getNextView() + ".jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "POST");
    }

}
