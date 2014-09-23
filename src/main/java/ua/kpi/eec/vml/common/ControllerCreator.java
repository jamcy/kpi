package ua.kpi.eec.vml.common;

import ua.kpi.eec.vml.controller.AccessProxy;
import ua.kpi.eec.vml.controller.AdminController;
import ua.kpi.eec.vml.controller.Controller;
import ua.kpi.eec.vml.controller.CourseController;
import ua.kpi.eec.vml.controller.FileController;
import ua.kpi.eec.vml.controller.LanguageController;
import ua.kpi.eec.vml.controller.LoginController;
import ua.kpi.eec.vml.controller.ModuleController;
import ua.kpi.eec.vml.controller.PageController;
import ua.kpi.eec.vml.controller.RoomController;

public class ControllerCreator {

    public Controller FactoryMethod(RequestData rd) {

        String controllerName = rd.getControllerName();

        if (controllerName.equals("room")) {
            //return new AccessProxy(new RoomController());
        }
        if (controllerName.equals("page")) {
            return new AccessProxy(new PageController());
        }
        if (rd.getControllerName().equals("module")) {
            return new AccessProxy(new ModuleController());
        }
        if (rd.getControllerName().equals("login")) {
            return new AccessProxy(new LoginController());
        }
        if (rd.getControllerName().equals("language")) {
            return new AccessProxy(new LanguageController());
        }
        if (rd.getControllerName().equals("admin")) {
            return new AccessProxy(new AdminController());
        }
        if (controllerName.equals("course")) {
            return new AccessProxy(new CourseController());
        }
        if (controllerName.equals("file")) {
            return new AccessProxy(new FileController());
        }
        return null;
    }
}
