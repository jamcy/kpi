package controller;

import model.entity.User;
import common.RequestData;

public class AccessProxy implements Controller {

	private Controller acceptor = null;

	public AccessProxy(Controller next) {
		acceptor = next;
	}

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		User user = (User) rd.getClientStateAttribute("user");

		String controller = rd.getControllerName();
		String function = rd.getFunction();

		String role = null;

		if (controller.equals("course")) {
			role = User.SR_USER;
		}

		if (controller.equals("module") && function.equals("task")) {
			role = User.SR_USER;
		}

		if (controller.equals("admin")) {
			if (function.equals("page") || function.equals("module")) {
				role = User.SR_MODERATOR;
			}

			if (function.equals("user")) {
				role = User.SR_ADMIN;
			}
		}
		boolean enough = true;
		if (user == null) {
			if (role != null) {
				enough = false;
			}
		} else {
			if (role != null) {
				if (role.equals(User.SR_ADMIN)) {
					if (user.getRole().equals(User.SR_USER)
							|| user.getRole().equals(User.SR_MODERATOR)) {
						enough = false;
					}
				}
				if (role.equals(User.SR_MODERATOR)) {
					if (user.getRole().equals(User.SR_USER)) {
						enough = false;
					}
				}
			}
		}

		if (enough) {
			return acceptor.processRequest(rd);
		}
		ControllerResponse resp = new ControllerResponse();
		resp.setNextView("error");
		resp.setPageDataAttribute("message",
				"Not enough permissions to acces this page");
		return resp;

	}

}
