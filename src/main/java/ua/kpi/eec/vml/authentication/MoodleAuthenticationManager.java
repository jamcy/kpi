package ua.kpi.eec.vml.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MoodleAuthenticationManager implements AuthenticationManager {

	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		if (auth.getName().equals(auth.getCredentials())) {
			return new UsernamePasswordAuthenticationToken(auth.getName(),
					auth.getCredentials(), AUTHORITIES);
		}
		throw new BadCredentialsException("Bad Credentials");
	}
	
//	/**
//	 * Reads user data from moodle by user token.
//	 * 
//	 * @param token
//	 * @return
//	 */
//	public User getByToken(String token) {
//
//		HashMap<String, String> par = new HashMap<String, String>();
//		par.put("token", token);
//		MoodleDataSource mdl_ds = MoodleDataSource.getInstance();
//		Document doc = null;
//		try {
//			doc = mdl_ds.request(MoodleDataSource.FUNCTION_GET_USER_BY_TOKEN, par);
//		} catch (MoodleRequestException e) {
//			e.printStackTrace();
//		}
//
//		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
//		XPath xPath = factory.newXPath();
//
//		User result = new User();
//		try {
//			XPathExpression expr = xPath.compile("//KEY[@name='username']");
//			NodeList res = (NodeList) expr
//					.evaluate(doc, XPathConstants.NODESET);
//			result.setUsername(res.item(0).getTextContent().trim());
//
//			expr = xPath.compile("//KEY[@name='firstname']");
//			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//			result.setFirstName(res.item(0).getTextContent().trim());
//
//			expr = xPath.compile("//KEY[@name='lastname']");
//			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//			result.setLastName(res.item(0).getTextContent().trim());
//
//			expr = xPath.compile("//KEY[@name='lang']");
//			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//			String lang = res.item(0).getTextContent().trim();
//			if (lang.equals("ua")) {
//				lang = "uk";
//			} else {
//				if (!lang.equals("en")) {
//					lang = "en";
//				}
//			}
//			result.setLanguage(lang);
//
//			expr = xPath.compile("//KEY[@name='userid']");
//			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//			result.setMoodleId(Long.parseLong(res.item(0).getTextContent()
//					.trim()));
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		return result;
//	}

}
