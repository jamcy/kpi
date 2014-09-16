package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.dao.CourseDao;
import model.dao.FileDao;
import model.dao.ModuleDao;
import model.dao.PageDao;
import model.dao.RoomDao;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entity.Course;
import model.entity.CourseRole;
import model.entity.Module;
import model.entity.Page;
import model.entity.Task;
import model.entity.Translation;
import model.entity.User;
import model.form.CourseForm;
import model.form.ModuleAddForm;
import model.form.ModuleEditForm;
import model.form.PageForm;
import model.form.TaskForm;
import model.form.UserForm;
import common.RequestData;

public class AdminController implements Controller {

    @Override
    public ControllerResponse processRequest(RequestData rd) {
        ControllerResponse resp = new ControllerResponse();
        String function = rd.getFunction();
        resp.setNextView("admin");
        if (function.equals("module")) {
            module(rd, resp);
        }
        if (function.equals("user")) {
            user(rd, resp);
        }
        if (function.equals("course")) {
            course(rd, resp);
        }
        if (function.equals("page")) {
            page(rd, resp);
        }
        if (function.equals("task")) {
            task(rd, resp);
        }

        return resp;
    }

    //@Override
    //public String getMapping() {
    //    return "admin";
    //}

    private void module(RequestData rd, ControllerResponse resp) {
        String action = rd.getParameter("action");
        ModuleDao mdao = new ModuleDao();

        // Add action
        if (action != null && action.equals("add") && rd.isClientDataProvided()) {
            ModuleAddForm data = new ModuleAddForm();
            data.init(rd);
            if (data.validate()) {
                Module module = new Module();
                data.update(module);
                mdao.save(module);

                new FileDao().makeDir("/modules/" + data.getShortName());

                resp.setRedirect(true);
                HashMap<String, String> redirectParams = new HashMap<String, String>();
                redirectParams.put("action", "edit");
                redirectParams.put("id", Long.toString(module.getId()));
                resp.setRedirectAddress("/admin/module", redirectParams);
                return;

            }
            resp.setPageDataAttribute("form_data", data);
        }

        // Edit action
        if (action != null && action.equals("edit")) {

            Long id = null;
            try {
                id = Long.parseLong(rd.getParameter("id"));
            } catch (Exception e) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "Error parsing arguments");
                return;
            }
            Module module = mdao.selectById(id);
            if (module == null) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "No such module");
                return;
            }
            ModuleEditForm data = new ModuleEditForm();
            if (rd.isClientDataProvided()) {
                data.init(rd);
                if (data.validate()) {
                    data.update(module);
                    mdao.update(module);
                    resp.setRedirect(true);
                    resp.setRedirectAddress("/admin/module",
                            new HashMap<String, String>());
                }
            } else {
                data.load(module);
                resp.setPageDataAttribute("form_data", data);
                resp.setPageDataAttribute("content_view", "module_edit");
            }
            return;
        }

        List<Module> modules = mdao.selectAll();

        resp.setPageDataAttribute("rooms", new RoomDao().selectAll());
        resp.setPageDataAttribute("modules", modules);
        resp.setPageDataAttribute("form_data", new ModuleAddForm());
        resp.setPageDataAttribute("content_view", "module_list");
    }

    private void user(RequestData rd, ControllerResponse resp) {
        String action = rd.getParameter("action");
        UserDao udao = new UserDao();
        if (action != null && (action.equals("edit"))) {
            UserForm data = new UserForm();
            Long id = new Long(0);
            try {
                id = Long.parseLong(rd.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "No user id passed");
                return;
            }
            User user = udao.selectById(id);
            if (user == null) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "There is no such user");
                return;
            }
            if (rd.isClientDataProvided()) {
                data.init(rd);
                if (data.validate()) {
                    data.update(user);
                    udao.update(user);
                    resp.setRedirect(true);
                    resp.setRedirectAddress("/admin/user",
                            new HashMap<String, String>());
                    return;
                }
            } else {
                data.load(user);
            }
            resp.setPageDataAttribute("content_view", "user_edit");
            List<Course> courses = new CourseDao().selectAll();
            HashMap<Long, Translation> courseNames = new HashMap<Long, Translation>();
            for (Course course : courses) {
                courseNames.put(course.getId(), course.getName());
            }
            resp.setPageDataAttribute("courses", courseNames);
            resp.setPageDataAttribute("form_data", data);
            return;
        }
        List<User> users = udao.selectAll();
        resp.setPageDataAttribute("users", users);
        resp.setPageDataAttribute("content_view", "user_list");
    }

    private void course(RequestData rd, ControllerResponse resp) {

        String action = rd.getParameter("action");
        User user = (User) rd.getClientStateAttribute("user");
        CourseDao cdao = new CourseDao();
        List<Course> courses = cdao.selectAll();

        boolean rights = false;
        if (user != null) {
            for (Course course : courses) {
                if (user.getRole(course.getId()).equals(CourseRole.CR_TEACHER)
                        || user.getRole(course.getId()).equals(
                        CourseRole.CR_MODERATOR)) {
                    rights = true;
                }
            }
            if (user.getRole().equals(User.SR_ADMIN)
                    || user.getRole().equals(User.SR_SUPERADMIN)) {
                rights = true;
            }
        }
        if (!rights) {
            resp.setNextView("error");
            resp.setPageDataAttribute("message",
                    "Not enough rights to access this page");
            return;
        }

        if (rd.isClientDataProvided()) {

            if (action != null && action.equals("edit")) {
                // Edit form data processing

                Long id = Long.parseLong(rd.getParameter("id"));

                if (id == null) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message", "No course id passed");
                    return;
                }

                if ((user == null
                        || user.getRole(id).equals(CourseRole.CR_NOTENROLLED) || user
                        .getRole(id).equals(CourseRole.CR_STUDENT))
                        && (user.getRole().equals(User.SR_USER) || user
                        .getRole().equals(User.SR_MODERATOR))) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message",
                            "You have no permission to do that");
                    return;
                }

                CourseForm data = new CourseForm();
                data.init(rd);

                if (data.validate()) {
                    Course course = cdao.selectById(id);

                    if (course == null) {
                        resp.setNextView("error");
                        resp.setPageDataAttribute("message", "No such course");
                        return;
                    }

                    data.update(course);
                    cdao.saveOrUpdate(course);

                    resp.setRedirect(true);
                    resp.setRedirectAddress("/admin/course");
                    return;
                }
                resp.setPageDataAttribute("form_data", data);
                resp.setPageDataAttribute("content_ciew", "course_edit");
                return;

            } else {
                // Importing course
                if (user == null || user.getRole().equals(User.SR_USER)
                        || user.getRole().equals(User.SR_MODERATOR)) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message",
                            "Not enough permissions to import course");
                    return;
                }

                Long id = Long.parseLong(rd.getParameter("id"));
                if (id == null) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message", "No course id passed");
                    return;
                }

                HashMap<Long, String> mcourses = cdao.getMoodleCourses();
                Course course = new Course();
                for (Long mc_id : mcourses.keySet()) {
                    if (mc_id == id) {
                        course.setId(id);
                        course.getName().setByLanguage("en",
                                mcourses.get(mc_id));
                        course.getName().setByLanguage("uk",
                                mcourses.get(mc_id));
                        break;
                    }
                }

                cdao.saveOrUpdate(course);

                resp.setRedirect(true);
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("action", "edit");
                params.put("id", Long.toString(id));
                resp.setRedirectAddress("/admin/course", params);
                return;
            }
        } else {

            if (action != null && action.equals("edit")) {
                // Edit form data initialization

                Long id = Long.parseLong(rd.getParameter("id"));

                if (id == null) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message",
                            "No id parameter passed");
                    return;
                }

                if ((user.getRole().equals(User.SR_MODERATOR) || user.getRole()
                        .equals(User.SR_USER))
                        && (user.getRole(id).equals(CourseRole.CR_NOTENROLLED) || user
                        .getRole(id).equals(CourseRole.CR_STUDENT))) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message",
                            "You have no permission to do that");
                    return;
                }

                Course course = cdao.selectById(id);
                CourseForm data = new CourseForm();
                data.load(course);

                resp.setPageDataAttribute("content_view", "course_edit");
                resp.setPageDataAttribute("form_data", data);
                return;

            } else {
                // Course list display

                HashMap<Long, String> moodle_courses = cdao.getMoodleCourses();

                for (Course c : courses) {
                    if (moodle_courses.keySet().contains(c.getId())) {
                        moodle_courses.remove(c.getId());
                    }
                }

                if (user.getRole().equals(User.SR_ADMIN)
                        || user.getRole().equals(User.SR_SUPERADMIN)) {
                    resp.setPageDataAttribute("courses", courses);
                } else {
                    List<Course> available_courses = new LinkedList<Course>();
                    for (Course c : courses) {
                        if (user.getRole(c.getId()).equals(
                                CourseRole.CR_TEACHER)
                                || user.getRole(c.getId()).equals(
                                CourseRole.CR_MODERATOR)) {
                            available_courses.add(c);
                        }
                    }
                    resp.setPageDataAttribute("courses", available_courses);
                }
                resp.setPageDataAttribute("moodle_courses", moodle_courses);
                resp.setPageDataAttribute("content_view", "course_list");
            }

        }

    }

    private void page(RequestData rd, ControllerResponse resp) {
        String action = rd.getParameter("action");
        PageDao pdao = new PageDao();
        if (action != null && (action.equals("edit") || action.equals("add"))) {
            PageForm data = new PageForm();
            if (rd.isClientDataProvided()) {
                data.init(rd);
                if (data.validate()) {
                    if (action.equals("add")) {
                        Page page = new Page();
                        data.update(page);
                        pdao.save(page);
                    } else {
                        Long id = new Long(0);
                        try {
                            id = Long.parseLong(rd.getParameter("id"));
                        } catch (Exception e) {
                            resp.setPageDataAttribute("message",
                                    "No id passed!");
                            resp.setNextView("error");
                            return;
                        }
                        Page page = pdao.selectById(id);
                        data.update(page);
                        pdao.update(page);
                    }
                    resp.setRedirect(true);
                    resp.setRedirectAddress("/admin/page",
                            new HashMap<String, String>());
                    return;
                } else {
                    resp.setPageDataAttribute("content_view", "page_edit");
                }
            } else {
                if (action.equals("add")) {
                    resp.setPageDataAttribute("content_view", "page_edit");
                } else {
                    Long id;
                    try {
                        id = Long.parseLong(rd.getParameter("id"));
                    } catch (Exception e) {
                        resp.setPageDataAttribute("message", "No id passed!");
                        resp.setNextView("error");
                        return;
                    }
                    Page page = pdao.selectById(id);
                    data.load(page);
                    resp.setPageDataAttribute("content_view", "page_edit");
                }
            }
            resp.setPageDataAttribute("form_data", data);
            return;
        }
        List<Page> pages = pdao.selectAll();
        resp.setPageDataAttribute("pages", pages);
        resp.setPageDataAttribute("content_view", "page_list");
    }

    public void task(RequestData rd, ControllerResponse resp) {

        String action = rd.getParameter("action");
        TaskDao tdao = new TaskDao();
        User user = (User) rd.getClientStateAttribute("user");

        // Add action
        if (action != null && action.equals("add")) {
            if (rd.isClientDataProvided()) {

                Task task = new Task();
                Long taskid = Long.parseLong(rd.getParameter("task-id"));
                Long courseid = Long.parseLong(rd.getParameter("course-id"));

                task.setId(Long.parseLong(rd.getParameter("task-id")));
                List<Task> tasks = tdao.selectByCourseId(courseid);
                task.setName("undefined");
                for (Task t : tasks) {
                    if (t.getId() == taskid) {
                        task.setName(t.getName());
                    }
                }
                task.setModuleId(Long.parseLong(rd.getParameter("module-id")));
                task.setCourseId(courseid);

                if ((user == null
                        || user.getRole(task.getCourseId()).equals(
                        CourseRole.CR_NOTENROLLED) || user.getRole(
                        task.getCourseId()).equals(CourseRole.CR_STUDENT))
                        && (user.getRole().equals(User.SR_USER) || user
                        .getRole().equals(User.SR_MODERATOR))) {
                    resp.setNextView("error");
                    resp.setPageDataAttribute("message",
                            "You have no permission to do that");
                    return;
                }

                tdao.save(task);

                resp.setRedirect(true);
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("action", "edit");
                params.put("id", Long.toString(task.getId()));
                resp.setRedirectAddress("/admin/task", params);
                return;
            }
        }

        // Edit action
        if (action != null && action.equals("edit")) {
            TaskForm data = new TaskForm();
            Long id = null;
            try {
                id = Long.parseLong(rd.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "Wrong task id format");
                return;
            }

            Task task = tdao.selectById(id);

            if (task == null) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message", "No such task");
                return;
            }

            if ((user == null
                    || user.getRole(task.getCourseId()).equals(
                    CourseRole.CR_NOTENROLLED) || user.getRole(
                    task.getCourseId()).equals(CourseRole.CR_STUDENT))
                    && (user.getRole().equals(User.SR_USER) || user.getRole()
                    .equals(User.SR_MODERATOR))) {
                resp.setNextView("error");
                resp.setPageDataAttribute("message",
                        "You have no permission to do that");
                return;
            }

            if (rd.isClientDataProvided()) {
                data.init(rd);
                if (data.validate()) {
                    data.update(task);
                    tdao.update(task);
                    resp.setRedirect(true);
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("courseid", Long.toString(task.getCourseId()));
                    resp.setRedirectAddress("/admin/task", params);
                }
            } else {
                data.load(task);
            }
            resp.setPageDataAttribute("form_data", data);
            resp.setPageDataAttribute("content_view", "task_edit");
            return;
        }

        Long id = null;
        try {
            id = Long.parseLong(rd.getParameter("courseid"));
        } catch (NumberFormatException e) {
            id = new Long(0);
            resp.setNextView("error");
            resp.setPageDataAttribute("message", "Can't read course id");
            return;
        }

        if ((user == null || user.getRole(id).equals(CourseRole.CR_NOTENROLLED) || user
                .getRole(id).equals(CourseRole.CR_STUDENT))
                && (user.getRole().equals(User.SR_USER) || user.getRole()
                .equals(User.SR_MODERATOR))) {
            resp.setNextView("error");
            resp.setPageDataAttribute("message",
                    "You have no permission to do that");
            return;
        }

        List<Task> tasks = tdao.selectByCourseId(id);
        Map<Long, String> moodleTasks = tdao.getMoodleTasks(id);
        List<Module> modules = null;
        if (moodleTasks.size() != 0) {
            modules = new ModuleDao().selectAll();
        }
        for (Task task : tasks) {
            if (moodleTasks.keySet().contains(task.getId())) {
                moodleTasks.remove(task.getId());
            }
        }
        resp.setPageDataAttribute("modules", modules);
        resp.setPageDataAttribute("moodle_tasks", moodleTasks);
        resp.setPageDataAttribute("tasks", tasks);
        resp.setPageDataAttribute("course_id", id);
        resp.setPageDataAttribute("content_view", "task_list");

    }
}
