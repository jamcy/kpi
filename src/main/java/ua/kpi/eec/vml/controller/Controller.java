package ua.kpi.eec.vml.controller;

import ua.kpi.eec.vml.common.RequestData;

public interface Controller {

    public ControllerResponse processRequest(RequestData rd);

    //public String getMapping();

}
