package com.epam.mykhailo_lisevych.wp.ejb;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class ContextBean {
	@Resource
	private SessionContext sessionContext;

	public SessionContext getSessionContext() {
		return sessionContext;
	}

}
