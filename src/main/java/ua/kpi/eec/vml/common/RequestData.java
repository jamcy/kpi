package ua.kpi.eec.vml.common;

import java.util.List;

import javax.servlet.http.Part;


public interface RequestData {
    public String getControllerName();

    public Object getClientStateAttribute(String attribute);

    public void setClientStateAttribute(String attribute, Object value);

    public void unsetClientStateAttribute(String attribute);

    public boolean issetClientStateAttribute(String attribute);

    public String getParameter(String name);

    public boolean isClientDataProvided();

    public String getFunction();

    public List<String> getParameterNames();

    public Part getFile(String name);
}
