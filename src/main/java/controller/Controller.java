package controller;

import common.RequestData;

public interface Controller {

    public ControllerResponse processRequest(RequestData rd);

    //public String getMapping();

}
