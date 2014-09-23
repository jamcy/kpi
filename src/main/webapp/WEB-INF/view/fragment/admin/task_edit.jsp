<%@page import="model.form.TaskForm" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.ResourceBundle" %>
<%@page import="resource.VmlResources" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    TaskForm data = (TaskForm) request.getAttribute("form_data");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    ResourceBundle messages = ResourceBundle.getBundle(
            "resource.VmlResources", new Locale(lang));
%>
<script src="/js/tinymce/tinymce.min.js"></script>
<script>
    tinymce.init({
        plugins: "image, code, table, link",
        mode: "exact",
        elements: "task_en, task_uk, theory_en, theory_uk"
    });
    $(function () {
        $("#task-tabs").tabs();
        $("#theory-tabs").tabs();

    });
</script>
<form action="" id="module-edit" method="post">
    <div class="form-group">
        <label for="name-input">Task name:</label>
        <input type="text" name="name" id="name-input" class="form-control" value="<%=data.getName()%>"/>
    </div>
    <div class="form-group">
        <label>Task:</label>

        <div id="task-tabs">
            <ul>
                <li><a href="#task-tabs-en">en</a></li>
                <li><a href="#task-tabs-uk">uk</a></li>
            </ul>
            <div id="task-tabs-en">
                <textarea name="task_en"><%=data.getTask().getByLanguage("en")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
            <div id="task-tabs-uk">
                <textarea name="task_uk"><%=data.getTask().getByLanguage("uk")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="template-input">Task template path:</label>
        <input type="text" name="template" id="template-input" class="form-control" value="<%=data.getTemplate() %>"/>
    </div>
    <div class="form-group">
        <label>Task theory:</label>

        <div id="theory-tabs">
            <ul>
                <li><a href="#theory-tabs-en">en</a></li>
                <li><a href="#theory-tabs-uk">uk</a></li>
            </ul>
            <div id="theory-tabs-en">
                <textarea name="theory_en"><%=data.getTheory().getByLanguage("en")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
            <div id="theory-tabs-uk">
                <textarea name="theory_uk"><%=data.getTheory().getByLanguage("uk")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><%=messages.getString(VmlResources.FM_BUTTON_CONFIRM)%>
    </button>
</form>