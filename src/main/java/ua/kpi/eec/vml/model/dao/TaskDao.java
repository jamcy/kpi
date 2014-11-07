package ua.kpi.eec.vml.model.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

import ua.kpi.eec.vml.model.datasrc.MoodleDataSource;
import ua.kpi.eec.vml.model.datasrc.MoodleRequestException;
import ua.kpi.eec.vml.model.entity.Task;

public class TaskDao {

	public Task selectById(Long id) {
		//TODO: remove
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Task> selectByCourseId(Long id) {
		//TODO: implement
		return null;
	}

	public void save(Task task) {
		//TODO: remove
	}

	public void update(Task task) {
		//TODO: remove
	}

	public Map<Long, String> getMoodleTasks(Long courseId) {
//		HashMap<Long, String> result = new HashMap<Long, String>();
//
//		MoodleDataSource ds = MoodleDataSource.getInstance();
//
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("courseid", Long.toString(courseId));
//		Document doc = null;
//		try {
//			doc = ds.request(MoodleDataSource.FUNCTION_GET_COURSE_CONTENTS, params);
//		} catch (MoodleRequestException e) {
//			e.printStackTrace();
//		}
//
//		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
//		XPath xPath = factory.newXPath();
//
//		try {
//			XPathExpression expr = xPath
//					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='modname']/VALUE");
//			NodeList res = (NodeList) expr
//					.evaluate(doc, XPathConstants.NODESET);
//			LinkedList<Integer> indices = new LinkedList<Integer>();
//			for (int i = 0; i < res.getLength(); i++) {
//				if (res.item(i).getTextContent().trim().equals("assign")) {
//					indices.add(new Integer(i));
//				}
//			}
//			expr = xPath
//					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='id']/VALUE");
//			NodeList ids = (NodeList) expr
//					.evaluate(doc, XPathConstants.NODESET);
//			expr = xPath
//					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='name']/VALUE");
//			NodeList names = (NodeList) expr.evaluate(doc,
//					XPathConstants.NODESET);
//			for (Integer i : indices) {
//				result.put(Long.parseLong(ids.item(i).getTextContent().trim()),
//						names.item(i).getTextContent().trim());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return result;
		
		//TODO: move to moodle service
		return null;
	}
}
