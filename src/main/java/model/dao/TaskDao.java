package model.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.datasrc.MoodleDataSource;
import model.datasrc.MoodleRequestException;
import model.datasrc.MysqlDataSource;
import model.entity.Task;

public class TaskDao {

	public Task selectById(Long id) {
		Task result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (Task) session.get(Task.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Task> selectByCourseId(Long id) {
		List<Task> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<Task>) session.createCriteria(Task.class)
					.add(Restrictions.eq("courseId", id))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public void save(Task task) {
		Transaction tx = null;
		Session session = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.save(task);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public void update(Task task) {
		Transaction tx = null;
		Session session = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.update(task);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public Map<Long, String> getMoodleTasks(Long courseId) {
		HashMap<Long, String> result = new HashMap<Long, String>();

		MoodleDataSource ds = MoodleDataSource.getInstance();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("courseid", Long.toString(courseId));
		Document doc = null;
		try {
			doc = ds.request(MoodleDataSource.FUNCTION_GET_COURSE_CONTENTS, params);
		} catch (MoodleRequestException e) {
			e.printStackTrace();
		}

		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			XPathExpression expr = xPath
					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='modname']/VALUE");
			NodeList res = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);
			LinkedList<Integer> indices = new LinkedList<Integer>();
			for (int i = 0; i < res.getLength(); i++) {
				if (res.item(i).getTextContent().trim().equals("assign")) {
					indices.add(new Integer(i));
				}
			}
			expr = xPath
					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='id']/VALUE");
			NodeList ids = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);
			expr = xPath
					.compile("//KEY[@name='modules']/MULTIPLE/SINGLE/KEY[@name='name']/VALUE");
			NodeList names = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);
			for (Integer i : indices) {
				result.put(Long.parseLong(ids.item(i).getTextContent().trim()),
						names.item(i).getTextContent().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
