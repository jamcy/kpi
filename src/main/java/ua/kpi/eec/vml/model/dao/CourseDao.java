package ua.kpi.eec.vml.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import ua.kpi.eec.vml.model.datasrc.MoodleDataSource;
import ua.kpi.eec.vml.model.datasrc.MoodleRequestException;
import ua.kpi.eec.vml.model.entity.Course;

public class CourseDao {

	@SuppressWarnings("unchecked")
	public List<Course> selectAll() {
		//TODO implement
		return null;
	}

	public HashMap<Long, String> getMoodleCourses() {

		HashMap<Long, String> result = new HashMap<Long, String>();

		MoodleDataSource ds = MoodleDataSource.getInstance();
		Document doc = null;
		try {
			doc = ds.request(MoodleDataSource.FUNCTION_GET_COURSES,
					new HashMap<String, String>());
		} catch (MoodleRequestException e) {
			e.printStackTrace();
			return result;
		}
		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		try {
			XPathExpression expr = xPath
					.compile("/RESPONSE/MULTIPLE/SINGLE/KEY[@name='id']");
			NodeList ids = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);

			expr = xPath
					.compile("/RESPONSE/MULTIPLE/SINGLE/KEY[@name='fullname']");
			NodeList names = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);
			for (int i = 0; i < names.getLength(); i++) {
				result.put(Long.parseLong(ids.item(i).getTextContent().trim()),
						names.item(i).getTextContent().trim());
			}
			result.remove(new Long(1));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public void saveOrUpdate(Course course) {
		//TODO implement
	}

	public Course selectById(Long id) {
		//TODO get rid of
		return null;
	}
}
