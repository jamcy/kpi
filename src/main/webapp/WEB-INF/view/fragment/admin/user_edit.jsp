<%@page import="java.util.Set" %>
<%@page import="model.entity.Course" %>
<%@page import="resource.VmlResources" %>
<%@page import="model.entity.Translation" %>
<%@page import="java.util.HashMap" %>
<%@page import="model.entity.CourseRole" %>
<%@page import="model.entity.User" %>
<%@page import="java.util.Locale" %>
<%@page import="model.form.UserForm" %>
<%@page import="java.util.ResourceBundle" %>
<%
    HashMap<Long, Translation> courses = (HashMap<Long, Translation>) request
            .getAttribute("courses");
    UserForm data = (UserForm) request.getAttribute("form_data");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    ResourceBundle messages = ResourceBundle.getBundle(
            "resource.VmlResources", new Locale(lang));
%>

<form action="" method="post">
    <div class="form-group" style="width: 250px;">
        <label for="system_role">System role:</label>
        <select id="system_role" name="system_role" class="form-control">
            <option value="<%=User.SR_ADMIN%>" <%=data.getSystemRole().equals(User.SR_ADMIN) ? "selected" : "" %>>
                Administrator
            </option>
            <option value="<%=User.SR_MODERATOR%>" <%=data.getSystemRole().equals(User.SR_MODERATOR) ? "selected" : "" %>>
                Moderator
            </option>
            <option value="<%=User.SR_USER%>" <%=data.getSystemRole().equals(User.SR_USER) ? "selected" : "" %>>User
            </option>
        </select>
    </div>
    <label>Course roles:</label>
    <table class="table">
        <%
            Set<Long> courseids = courses.keySet();
            for (Long courseid : courseids) {
                String role = data.getCourseRoles().get(courseid);
                if (role == null) {
                    role = CourseRole.CR_NOTENROLLED;
                }
        %>
        <tr>
            <td><%=courses.get(courseid).toString(lang)%>
            </td>
            <td><select name="role_<%=courseid%>" class="form-control">
                <option value="<%=CourseRole.CR_TEACHER%>" <%=role.equals(CourseRole.CR_TEACHER) ? "selected" : ""%>>
                    Teacher
                </option>
                <option value="<%=CourseRole.CR_STUDENT%>" <%=role.equals(CourseRole.CR_STUDENT) ? "selected" : ""%>>
                    Student
                </option>
                <option value="<%=CourseRole.CR_MODERATOR%>" <%=role.equals(CourseRole.CR_MODERATOR) ? "selected" : ""%>>
                    Moderator
                </option>
                <option value="<%=CourseRole.CR_NOTENROLLED%>" <%=role.equals(CourseRole.CR_NOTENROLLED) ? "selected" : ""%>>
                    Not enrolled
                </option>
            </select></td>
        </tr>
        <%
            }
        %>
    </table>
    <button type="submit" class="btn btn-primary"><%=messages.getString(VmlResources.FM_BUTTON_CONFIRM)%>
    </button>
</form>