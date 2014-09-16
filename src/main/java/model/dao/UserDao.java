package model.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.datasrc.MoodleDataSource;
import model.datasrc.MoodleRequestException;
import model.datasrc.MysqlDataSource;
import model.entity.Course;
import model.entity.CourseRole;
import model.entity.User;

public class UserDao {

	private String token = null;

	@SuppressWarnings("unchecked")
	public List<User> selectAll() {
		List<User> result = null;
		SessionFactory factory = MysqlDataSource.getInstance().getFactory();
		try {
			factory.getCurrentSession().beginTransaction();
			result = (List<User>) factory.getCurrentSession()
					.createQuery("from User order by lastName").list();
			factory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			factory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public User selectById(long id) {
		User result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (User) session.get(User.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	/**
	 * Updates user data based on data receieved from moodle.
	 * 
	 * @param user
	 * @throws HibernateException
	 */
	public void updateUserData(User user) throws HibernateException {
		try {
			User u = (User) selectById(user.getMoodleId());
			List<Long> moodleCourses = this.getEnrolledCourses(user);
			List<Course> courses = new CourseDao().selectAll();
			List<Long> course_id = new LinkedList<Long>();
			for (Course c : courses) {
				if (moodleCourses.contains(c.getId())) {
					course_id.add(c.getId());
				}
			}
			if (u == null) {
				for (Long course : course_id) {
					user.getCourseRoles().add(
							new CourseRole(user.getMoodleId(), course,
									CourseRole.CR_STUDENT));
				}
				save(user);
			} else {
				for (Long course : course_id) {
					if (u.getRole(course).equals(CourseRole.CR_NOTENROLLED)) {
						u.getCourseRoles().add(
								new CourseRole(user.getMoodleId(), course,
										CourseRole.CR_STUDENT));
					}
				}
				user.setCourseRoles(u.getCourseRoles());
				update(user);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	public void update(User user) {
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public void save(User user) {
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();

			session.save(user);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * Checks user identity and saves obtained token as internal variable.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password) {
		MoodleDataSource ds = MoodleDataSource.getInstance();
		try {
			String result = ds.getToken(username, password, "auth");
			if (result != null) {
				this.token = result;
				return true;
			}
			return false;
		} catch (MoodleRequestException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Reads user data from moodle by user token.
	 * 
	 * @param token
	 * @return
	 */
	public User getByToken(String token) {

		HashMap<String, String> par = new HashMap<String, String>();
		par.put("token", token);
		MoodleDataSource mdl_ds = MoodleDataSource.getInstance();
		Document doc = null;
		try {
			doc = mdl_ds.request(MoodleDataSource.FUNCTION_GET_USER_BY_TOKEN, par);
		} catch (MoodleRequestException e) {
			e.printStackTrace();
		}

		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		User result = new User();
		try {
			XPathExpression expr = xPath.compile("//KEY[@name='username']");
			NodeList res = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);
			result.setUsername(res.item(0).getTextContent().trim());

			expr = xPath.compile("//KEY[@name='firstname']");
			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			result.setFirstName(res.item(0).getTextContent().trim());

			expr = xPath.compile("//KEY[@name='lastname']");
			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			result.setLastName(res.item(0).getTextContent().trim());

			expr = xPath.compile("//KEY[@name='lang']");
			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			String lang = res.item(0).getTextContent().trim();
			if (lang.equals("ua")) {
				lang = "uk";
			} else {
				if (!lang.equals("en")) {
					lang = "en";
				}
			}
			result.setLanguage(lang);

			expr = xPath.compile("//KEY[@name='userid']");
			res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			result.setMoodleId(Long.parseLong(res.item(0).getTextContent()
					.trim()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public List<Long> getEnrolledCourses(User user) {
		HashMap<String, String> par = new HashMap<String, String>();
		par.put("userids[0]", Long.toString(user.getMoodleId()));
		MoodleDataSource mdl_ds = MoodleDataSource.getInstance();
		Document doc = null;
		try {
			doc = mdl_ds.request(MoodleDataSource.FUNCTION_GET_USER_BY_ID, par);
		} catch (MoodleRequestException e) {
			e.printStackTrace();
			return null;
		}

		List<Long> result = new LinkedList<Long>();

		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		try {
			XPathExpression expr = xPath
					.compile("//KEY[@name='enrolledcourses']/MULTIPLE/SINGLE/KEY[@name='id']");
			NodeList res = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < res.getLength(); i++) {
				try {
					result.add(Long.parseLong(res.item(i).getTextContent()
							.trim()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	/**
	 * Returns user token obtained after successfull authorization.
	 * 
	 * @return
	 */
	public String getUserToken() {
		return this.token;
	}

}