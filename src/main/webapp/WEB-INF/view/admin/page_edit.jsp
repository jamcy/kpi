<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<script src="//tinymce.cachefly.net/4.1/tinymce.min.js"></script>
<script>
    $(function () {
        $("#name-tabs").tabs();
        $("#content-tabs").tabs();
    });
    tinymce.init({
        plugins: "image, code, table, link",
        mode: "exact",
        elements: "page-content_en, page-content_uk"
    });
</script>

<sf:form action="" id="module-edit" method="post" commandName="page">
    <div class="form-group">
        <label>Name:</label>
        <div id="name-tabs">
			<ul>
			    <li><a href="#name-tabs-en">en</a></li>
			    <li><a href="#name-tabs-uk">uk</a></li>
			</ul>
			<div id="name-tabs-en"><sf:input path="nameEn" cssClass="form-control"/></div>
			<div id="name-tabs-uk"><sf:input path="nameUk" cssClass="form-control"/></div>
        </div>
    </div>
    <div class="form-group">
        <label for="url-suffix">Page url suffix:</label>
        <sf:input path="urlSuffix" cssClass="form-control" />
    </div>
    <div class="form-group">
        <label>Page content:</label>
        <div id="content-tabs">
            <ul>
                <li><a href="#content-tabs-en">en</a></li>
                <li><a href="#content-tabs-uk">uk</a></li>
            </ul>
            <div id="content-tabs-en">
                <sf:textarea path="contentEn" htmlEscape="true" class="form-control"/>
            </div>
            <div id="content-tabs-uk">
                <sf:textarea path="contentUk" htmlEscape="true" cass="form-control"/>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><s:message code="form.button.confirm" /></button>
</sf:form>