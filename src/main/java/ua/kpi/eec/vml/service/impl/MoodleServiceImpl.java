package ua.kpi.eec.vml.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.model.dto.MoodleTokenResponse;
import ua.kpi.eec.vml.model.entity.Account;
import ua.kpi.eec.vml.service.MoodleRequestException;
import ua.kpi.eec.vml.service.MoodleService;

public class MoodleServiceImpl implements MoodleService {

	public static final String FUNCTION_GET_USER_BY_TOKEN = "core_webservice_get_site_info";
	public static final String FUNCTION_GET_USER_BY_ID = "core_user_get_users_by_id";
	public static final String FUNCTION_GET_COURSES = "core_course_get_courses";
	public static final String FUNCTION_GET_COURSE_CONTENTS = "core_course_get_contents";

	private String token;
	private String baseUrl;
	// TODO extract to property
	private String loginService = "vml";

	@Override
	public AccountData authenticate(String username, String password) throws MoodleRequestException {
		MoodleTokenResponse tokenResponse = tokenRequest(username, password);
		if (tokenResponse.getToken() != null)
			return getByToken(tokenResponse.getToken());
		throw new MoodleRequestException("Wrong credentials");
	}

	public void setToken(String moodleToken) {
		this.token = moodleToken;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Map<Integer, String> getMoodleCourses() throws MoodleRequestException {
		// TODO return DTO ?
		Map<Integer, String> result = new HashMap<Integer, String>();
		Document doc = request(buildRequestUrl(FUNCTION_GET_COURSES, Collections.<String, String> emptyMap(), token));
		try {
			NodeList idList = (NodeList) evaluateXPath(doc, "/RESPONSE/MULTIPLE/SINGLE/KEY[@name='id']");
			NodeList nameList = (NodeList) evaluateXPath(doc, "/RESPONSE/MULTIPLE/SINGLE/KEY[@name='fullname']");
			for (int i = 0; i < idList.getLength(); i++) {
				int id = Integer.parseInt(idList.item(i).getTextContent().trim());
				String name = nameList.item(i).getTextContent().trim();
				result.put(id, name);
			}
			// TODO WAT?
			result.remove(new Long(1));
		} catch (Exception e) {
			e.printStackTrace();
			throw new MoodleRequestException(e);
		}
		return result;
	}

	public Map<Integer, String> getMoodleTasks(int courseId) throws MoodleRequestException {
		// TODO return DTO?
		Map<Integer, String> result = new HashMap<Integer, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("courseid", Integer.toString(courseId));
		Document doc = request(buildRequestUrl(FUNCTION_GET_COURSE_CONTENTS, params, FUNCTION_GET_USER_BY_TOKEN));
		try {
			NodeList taskTypes = (NodeList) evaluateXPath(doc,
					"//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='modname']/VALUE");
			List<Integer> assignmentTaskIndexes = new ArrayList<Integer>();
			for (int i = 0; i < taskTypes.getLength(); i++) {
				if (taskTypes.item(i).getTextContent().trim().equals("assign")) {
					assignmentTaskIndexes.add(i);
				}
			}
			NodeList idList = (NodeList) evaluateXPath(doc,
					"//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='id']/VALUE");
			NodeList nameList = (NodeList) evaluateXPath(doc,
					"//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='name']/VALUE");
			for (Integer i : assignmentTaskIndexes) {
				int taskId = Integer.parseInt(idList.item(i).getTextContent().trim());
				String taskName = nameList.item(i).getTextContent().trim();
				result.put(taskId, taskName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MoodleRequestException(e);
		}

		return result;
	}

	private AccountData getByToken(String token) throws MoodleRequestException {
		Document doc = request(buildRequestUrl(FUNCTION_GET_USER_BY_TOKEN, new HashMap<String, String>(), token));
		AccountData account = new AccountData();
		try {
			account.setUsername(getXPathAsString(doc, "//KEY[@name='username']"));
			String firstName = getXPathAsString(doc, "//KEY[@name='firstname']");
			String lastName = getXPathAsString(doc, "//KEY[@name='lastname']");
			account.setFullName(firstName + " " + lastName);
			account.setMoodleId(Integer.parseInt(getXPathAsString(doc, "//KEY[@name='userid']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	private String getXPathAsString(Document doc, String expr) throws XPathExpressionException {
		NodeList nodeList = (NodeList) evaluateXPath(doc, expr);
		return nodeList.item(0).getTextContent().trim();
	}

	public List<Integer> getEnrolledCoursesId(int userId) throws MoodleRequestException {
		List<Integer> result = new ArrayList<Integer>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userids[0]", Integer.toString(userId));
		try {
			Document doc = request(buildRequestUrl(FUNCTION_GET_USER_BY_ID, params, token));
			String expr = "//KEY[@name='enrolledcourses']/MULTIPLE/SINGLE/KEY[@name='id']";
			NodeList res = (NodeList) evaluateXPath(doc, expr);
			for (int i = 0; i < res.getLength(); i++)
				result.add(Integer.parseInt(res.item(i).getTextContent().trim()));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return result;
	}

	private MoodleTokenResponse tokenRequest(String username, String password) throws MoodleRequestException {
		String urlString = baseUrl + "/login/token.php" + "?username=" + username + "&password=" + password
				+ "&service=" + loginService;
		Scanner scanner = null;
		try {
			URL url = new URL(urlString);
			scanner = new Scanner(url.openStream());
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine())
				builder.append(scanner.nextLine());
			Gson gson = new Gson();
			return gson.fromJson(builder.toString(), MoodleTokenResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MoodleRequestException(e);
		} finally {
			scanner.close();
		}
	}

	private String buildRequestUrl(String function, Map<String, String> params, String token) {
		String query = "";
		for (String key : params.keySet())
			query += "&" + key + "=" + params.get(key);
		String url = baseUrl + "/webservice/rest/server.php?wstoken=" + token + "&wsfunction=" + function + query;
		return url;
	}

	private Document request(String url) throws MoodleRequestException {
		Document doc = null;
		InputStream is = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL request = new URL(url);
			is = request.openStream();
			doc = builder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MoodleRequestException(e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		return doc;
	}

	private Object evaluateXPath(Document doc, String expr) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		return xpath.compile(expr).evaluate(doc, XPathConstants.NODESET);
	}
}
