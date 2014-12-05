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
        elements: "module-content_en, module-content_uk"
    });
    $(function () {
        $("#description-tabs").tabs();
        $("#name-tabs").tabs();
        $("#content-tabs").tabs();
    });
</script>

<sf:form action="" id="module-edit" method="post" commandName="module">
	<sf:hidden path="id"/>
    <div class="form-group">
    	<sf:input path="shortName" id="short-name" cssClass="form-control" />
    </div>
    <div class="form-group">
	   	<sf:select path="module.roomId" >
	   		<c:forEach items="${rooms }" var="room">
	   			<sf:option value="${room.id }"><u:i18n value="${room.name }" /></sf:option>
	   		</c:forEach>
	   	</sf:select>
    </div>
    <div class="form-group">
        <label>Short name:</label>
        <sf:input path="shortName" />
    </div>
    <div class="form-group">
        <label>Module name:</label>
        <div id="name-tabs">
            <ul>
                <li><a href="#name-tabs-en">en</a></li>
                <li><a href="#name-tabs-uk">uk</a></li>
            </ul>
            <div id="name-tabs-en"><sf:input htmlEscape="true" path="nameEn" /></div>
            <div id="name-tabs-uk"><sf:input htmlEscape="true" path="nameUk" /></div>
        </div>
    </div>
    <div class="form-group">
        <label for="module-image">Image url:</label>
        <sf:input path="imageUrl" />
    </div>
    <div class="form-group">
        <label>Module description:</label>
        <div id="description-tabs">
            <ul>
                <li><a href="#description-tabs-en">en</a></li>
                <li><a href="#description-tabs-uk">uk</a></li>
            </ul>
            <div id="description-tabs-en">
                <sf:textarea path="descriptionEn" cssClass="form-control" htmlEscape="true" />
            </div>
            <div id="description-tabs-uk">
            	<sf:textarea path="descriptionUk" cssClass="form-control" htmlEscape="true" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <label>Module page content:</label>
        <div id="content-tabs">
            <ul>
                <li><a href="#content-tabs-en">en</a></li>
                <li><a href="#content-tabs-uk">uk</a></li>
            </ul>
            <div id="content-tabs-en">
                <sf:textarea path="contentEn" cssClass="form-control" htmlEscape="true" />
            </div>
            <div id="content-tabs-uk">
               <sf:textarea path="contentUk" cssClass="form-control" htmlEscape="true" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="module-embed">Embed code:</label>
        <sf:textarea path="embed" htmlEscape="true" cssClass="formControl" />
    </div>
    <button type="submit" class="btn btn-primary"><s:message code="form.button.confirm" /></button>
</sf:form>