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
        elements: "course-description_en, course-description_uk"
    });
    $(function () {
        $("#description-tabs").tabs();
        $("#name-tabs").tabs();
    });
</script>

<sf:form action="" method="post" commandName="course">
	<sf:hidden path="id"/>
	<sf:hidden path="action"/>
	
    <label>Course name:</label>
    <div id="name-tabs">
        <ul>
            <li><a href="#name-tabs-en">en</a></li>
            <li><a href="#name-tabs-uk">uk</a></li>
        </ul>
        <div id="name-tabs-en">
        	<sf:input path="nameEn" cssClass="form-control" />
        </div>
        <div id="name-tabs-uk">
        	<sf:input path="nameUk" cssClass="form-control" />
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
        	<sf:textarea path="descriptionEn" htmlEscape="true" cssClass="form-control" />
        </div>
        <div id="description-tabs-uk">
            <sf:textarea path="descriptionEn" htmlEscape="true" cssClass="form-control" />
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><s:message code="form.button.confirm" /></button>
</sf:form>