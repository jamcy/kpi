package controller;

import common.RequestData;

/**
 * Responsible for changing languages.
 * @author spawn
 *
 */
public class LanguageController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		ControllerResponse resp = new ControllerResponse();
		resp.setRedirect(true);
		resp.setRedirectAddress("/");
		rd.setClientStateAttribute("lang", rd.getParameter("val"));

		return resp;
	}

}
