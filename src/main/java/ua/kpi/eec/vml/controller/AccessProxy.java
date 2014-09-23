package ua.kpi.eec.vml.controller;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.entity.Account;

public class AccessProxy implements Controller {

	private Controller acceptor = null;

	public AccessProxy(Controller next) {
		acceptor = next;
	}

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		Account user = (Account) rd.getClientStateAttribute("user");

		String controller = rd.getControllerName();
		String function = rd.getFunction();

		String role = null;

		if (controller.equals("course")) {
//			role = Account.SR_USER;
		}

		if (controller.equals("module") && function.equals("task")) {
//			role = Account.SR_USER;
		}

		if (controller.equals("admin")) {
			if (function.equals("page") || function.equals("module")) {
//				role = Acccount.SR_MODERATOR;
			}

			if (function.equals("user")) {
//				role = Acccount.SR_ADMIN;
			}
		}
		boolean enough = true;
		if (user == null) {
			if (role != null) {
				enough = false;
			}
		} else {
			if (role != null) {
//				if (role.equals(Acccount.SR_ADMIN)) {
//					if (user.getRole().equals(Acccount.SR_USER)
//							|| user.getRole().equals(Acccount.SR_MODERATOR)) {
//						enough = false;
//					}
//				}
//				if (role.equals(Acccount.SR_MODERATOR)) {
//					if (user.getRole().equals(Acccount.SR_USER)) {
//						enough = false;
//					}
//				}
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
