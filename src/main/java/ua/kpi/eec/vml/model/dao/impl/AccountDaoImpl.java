package ua.kpi.eec.vml.model.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.entity.Account;

@Repository
public class AccountDaoImpl extends AbstractHibernateDao<Account> implements AccountDao {

	@Override
	public Class<?> getEntityClass() {
		return Account.class;
	}

	@Transactional
	@Override
	public Account findByUsername(String username) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery("FROM Account where username=:username");
		q.setParameter("username", username);
		Account result = (Account)q.uniqueResult();
		Hibernate.initialize(result.getCourses());
		return result;
	}
	
//	public void updateUserData(User user) throws HibernateException {
//	try {
//		User u = (User) selectById(user.getMoodleId());
//		List<Long> moodleCourses = this.getEnrolledCourses(user);
//		List<Course> courses = new CourseDao().selectAll();
//		List<Long> course_id = new LinkedList<Long>();
//		for (Course c : courses) {
//			if (moodleCourses.contains(c.getId())) {
//				course_id.add(c.getId());
//			}
//		}
//		if (u == null) {
//			for (Long course : course_id) {
//				user.getCourseRoles().add(
//						new CourseRole(user.getMoodleId(), course,
//								CourseRole.CR_STUDENT));
//			}
//			save(user);
//		} else {
//			for (Long course : course_id) {
//				if (u.getRole(course).equals(CourseRole.CR_NOTENROLLED)) {
//					u.getCourseRoles().add(
//							new CourseRole(user.getMoodleId(), course,
//									CourseRole.CR_STUDENT));
//				}
//			}
//			user.setCourseRoles(u.getCourseRoles());
//			update(user);
//		}
//	} catch (HibernateException e) {
//		e.printStackTrace();
//	}
//
//}
//
//
///**
// * Checks user identity and saves obtained token as internal variable.
// * 
// * @param username
// * @param password
// * @return
// */



}
