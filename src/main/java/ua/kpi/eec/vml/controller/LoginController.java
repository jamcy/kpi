package ua.kpi.eec.vml.controller;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.dao.UserDao;
import ua.kpi.eec.vml.model.form.LoginForm;


/**
 * Corresponds to user identity operations.
 * 
 * @author spawn
 * 
 */
public class LoginController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		ControllerResponse resp = new ControllerResponse();

		// logout action
		if (rd.getParameter("action") != null
				&& rd.getParameter("action").equals("logout")) {
			logout(rd);
			resp.setRedirect(true);
			resp.setRedirectAddress("/");
			return resp;
		}

		if (rd.issetClientStateAttribute("user")) {
			resp.setRedirect(true);
			resp.setRedirectAddress((String) rd
					.getClientStateAttribute("calling_page_address"));
			return resp;
		}

		// login action
		if (!rd.isClientDataProvided()) {
			rd.setClientStateAttribute("return_address",
					rd.getClientStateAttribute("calling_page_address"));
		}
		LoginForm data = new LoginForm();
		data.init(rd);
		resp.setNextView("login");
		if (rd.isClientDataProvided()) {
			if (data.validate() && login(rd)) {
				resp.setRedirect(true);
				resp.setRedirectAddress("/course");
				/*resp.setRedirectAddress((String) rd
						.getClientStateAttribute("return_address"));
				rd.unsetClientStateAttribute("return_address");*/
			} else {
				data.setMessage(LoginForm.FM_LOGIN_FAILED);
			}
		}
		resp.setPageDataAttribute("form-data", data);
		return resp;
	}

	/**
	 * Checks user identity in moodle system. If true, writes obtained token
	 * into cilent state and rereads data from moodle to database.
	 * 
	 * @param rd
	 * @return
	 */
	private boolean login(RequestData rd) {
		UserDao udao = new UserDao();
		String username = rd.getParameter("username");
		String password = rd.getParameter("password");
		LoginForm lf = new LoginForm();
		lf.init(rd);
//		if (udao.login(username, password)) {

//			User user = udao.getByToken(udao.getUserToken());
//
//			if (user == null) {
//				return false;
//			}
//
//			User dbuser = udao.selectById(user.getMoodleId());
//			if (dbuser != null) {
//				user.setRole(dbuser.getRole());
//			}
//
//			udao.updateUserData(user);
//			rd.setClientStateAttribute("user", user);
//			if (user.getLanguage().equals("en")
//					|| user.getLanguage().equals("uk")) {
//				rd.setClientStateAttribute("lang", user.getLanguage());
//
//				return true;
//			}
//		}
		return false;
	}

	/**
	 * Unsets user state attributes.
	 * 
	 * @param rd
	 */
	private void logout(RequestData rd) {
		rd.unsetClientStateAttribute("user");
	}

}
