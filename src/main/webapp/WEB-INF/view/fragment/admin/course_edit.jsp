<%@page import="java.util.Locale" %>
<%@page import="model.form.CourseForm" %>
<%@page import="java.util.ResourceBundle" %>
<%@page import="resource.VmlResources" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    ResourceBundle messages = ResourceBundle.getBundle(
            "resource.VmlResources", new Locale(lang));
    CourseForm data = (CourseForm) request.getAttribute("form_data");
%>
<script src="<%=request.getContextPath()%>/js/tinymce/tinymce.min.js"></script>
<script>
    tinymce.init({
        plugins: "image, code, table, link",
        mode: "exact",
        elements: "course-description_en, course-description_uk"
    });
    $(function () {
        $("#description-tabs").tabs();
        $("#name-tabs").tabs();
    });
</script>

<form action="" method="post">

    <input type="hidden" name="action" value="<%=data.getAction()%>"/> <input
        type="hidden" name="id" value="<%=data.getId()%>"/>
    <label>Course name:</label>

    <div id="name-tabs">
        <ul>
            <li><a href="#name-tabs-en">en</a></li>
            <li><a href="#name-tabs-uk">uk</a></li>
        </ul>
        <div id="name-tabs-en">
            <input type="text" name="name_en" class="form-control"
                   value="<%=data.getName().getByLanguage("en")%>"/>
        </div>
        <div id="name-tabs-uk">
            <input type="text" name="name_uk" class="form-control"
                   value="<%=data.getName().getByLanguage("uk")%>"/>
        </div>
    </div>
    <br>
    <label>Course description:</label>

    <div id="description-tabs">
        <ul>
            <li><a href="#description-tabs-en">en</a></li>
            <li><a href="#description-tabs-uk">uk</a></li>
        </ul>
        <div id="description-tabs-en">
            <textarea name="description_en" class="form-control"
                      id="course-description_en"><%=data.getDescription().getByLanguage("en")%>
            </textarea>
        </div>
        <div id="description-tabs-uk">
            <textarea name="description_uk" class="form-control"
                      id="course-description_uk"><%=data.getDescription().getByLanguage("uk")%>
            </textarea>
        </div>
    </div>

    <button type="submit" class="btn btn-primary"><%=messages.getString(VmlResources.FM_BUTTON_CONFIRM)%>
    </button>
</form>