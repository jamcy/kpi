package ua.kpi.eec.vml.model.dao;

public class UserDao {

//	public void updateUserData(User user) throws HibernateException {
//		try {
//			User u = (User) selectById(user.getMoodleId());
//			List<Long> moodleCourses = this.getEnrolledCourses(user);
//			List<Course> courses = new CourseDao().selectAll();
//			List<Long> course_id = new LinkedList<Long>();
//			for (Course c : courses) {
//				if (moodleCourses.contains(c.getId())) {
//					course_id.add(c.getId());
//				}
//			}
//			if (u == null) {
//				for (Long course : course_id) {
//					user.getCourseRoles().add(
//							new CourseRole(user.getMoodleId(), course,
//									CourseRole.CR_STUDENT));
//				}
//				save(user);
//			} else {
//				for (Long course : course_id) {
//					if (u.getRole(course).equals(CourseRole.CR_NOTENROLLED)) {
//						u.getCourseRoles().add(
//								new CourseRole(user.getMoodleId(), course,
//										CourseRole.CR_STUDENT));
//					}
//				}
//				user.setCourseRoles(u.getCourseRoles());
//				update(user);
//			}
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//	/**
//	 * Checks user identity and saves obtained token as internal variable.
//	 * 
//	 * @param username
//	 * @param password
//	 * @return
//	 */
//	public boolean login(String username, String password) {
//		MoodleDataSource ds = MoodleDataSource.getInstance();
//		try {
//			String result = ds.getToken(username, password, "auth");
//			if (result != null) {
//				this.token = result;
//				return true;
//			}
//			return false;
//		} catch (MoodleRequestException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//
//	public List<Long> getEnrolledCourses(User user) {
//		HashMap<String, String> par = new HashMap<String, String>();
//		par.put("userids[0]", Long.toString(user.getMoodleId()));
//		MoodleDataSource mdl_ds = MoodleDataSource.getInstance();
//		Document doc = null;
//		try {
//			doc = mdl_ds.request(MoodleDataSource.FUNCTION_GET_USER_BY_ID, par);
//		} catch (MoodleRequestException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		List<Long> result = new LinkedList<Long>();
//
//		XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
//		XPath xPath = factory.newXPath();
//
//		try {
//			XPathExpression expr = xPath
//					.compile("//KEY[@name='enrolledcourses']/MULTIPLE/SINGLE/KEY[@name='id']");
//			NodeList res = (NodeList) expr
//					.evaluate(doc, XPathConstants.NODESET);
//			for (int i = 0; i < res.getLength(); i++) {
//				try {
//					result.add(Long.parseLong(res.item(i).getTextContent()
//							.trim()));
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		return result;
//	}
//
//	/**
//	 * Returns user token obtained after successfull authorization.
//	 * 
//	 * @return
//	 */
//	public String getUserToken() {
//		return this.token;
//	}

}