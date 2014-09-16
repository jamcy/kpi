package common;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class HttpRequestData implements RequestData {

    private HttpServletRequest request = null;
    private HttpSession session = null;

    public HttpRequestData(HttpServletRequest req) {
        this.request = req;
        this.session = req.getSession();
    }

    @Override
    public String getControllerName() {
        return request.getServletPath().replace("/", "");
    }

    @Override
    public Object getClientStateAttribute(String attribute) {
        return this.session.getAttribute(attribute);
    }

    @Override
    public void setClientStateAttribute(String attribute, Object value) {
        this.session.setAttribute(attribute, value);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public void unsetClientStateAttribute(String attribute) {
        this.session.setAttribute(attribute, null);
    }

    @Override
    public boolean issetClientStateAttribute(String attribute) {
        return session.getAttribute(attribute) != null;
    }

    @Override
    public boolean isClientDataProvided() {
        return request.getMethod().equalsIgnoreCase("post");
    }

    @Override
    public String getFunction() {
        return this.request.getRequestURI()
                .replaceFirst(request.getContextPath(), "")
                .replaceFirst(request.getServletPath(), "")
                .replaceFirst("/", "").trim();
    }

    @Override
    public List<String> getParameterNames() {
        LinkedList<String> names = new LinkedList<String>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            names.add(params.nextElement());
        }
        return names;
    }

    @Override
    public Part getFile(String name) {
        try {
            return request.getPart(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
