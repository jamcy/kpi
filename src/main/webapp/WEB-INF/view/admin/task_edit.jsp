<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<script src="//tinymce.cachefly.net/4.1/tinymce.min.js"></script>
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

<sf:form action="" id="module-edit" method="post">
    <div class="form-group">
        <label for="name-input">Task name:</label>
        <sf:input path="name" cssClass="form-control"/>
    </div>
    <div class="form-group">
        <label>Task:</label>
        <div id="task-tabs">
            <ul>
                <li><a href="#task-tabs-en">en</a></li>
                <li><a href="#task-tabs-uk">uk</a></li>
            </ul>
            <div id="task-tabs-en">
            	<sf:textarea path="taskEn" htmlEscape="true" cssClass="form-control"/>
            </div>
            <div id="task-tabs-uk">
                <sf:textarea path="taskUk" htmlEscape="true" cssClass="form-control"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="template-input">Task template path:</label>
        <sf:input path="template" cssClass="form-control"/>
    </div>
    <div class="form-group">
        <label>Task theory:</label>
        <div id="theory-tabs">
            <ul>
                <li><a href="#theory-tabs-en">en</a></li>
                <li><a href="#theory-tabs-uk">uk</a></li>
            </ul>
            <div id="theory-tabs-en">
                <sf:textarea path="theoryEn" htmlEscape="true" cssClass="form-control"/>
            </div>
            <div id="theory-tabs-uk">
                <sf:textarea path="theoryUk" htmlEscape="true" cssClass="form-control"/>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><s:message code="form.button.confirm" /></button>
</sf:form>