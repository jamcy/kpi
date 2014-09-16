package model.dao;

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

import model.datasrc.MoodleDataSource;
import model.datasrc.MoodleRequestException;
import model.datasrc.MysqlDataSource;
import model.entity.Course;

public class CourseDao {

	@SuppressWarnings("unchecked")
	public List<Course> selectAll() {
		Session session = null;
		Transaction tx = null;
		List<Course> result = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<Course>) session.createQuery(
					"FROM Course ORDER BY id").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
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
		Transaction tx = null;
		Session session = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(course);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public Course selectById(Long id) {
		Session session = null;
		Transaction tx = null;
		Course result = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (Course) session.get(Course.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}
}
