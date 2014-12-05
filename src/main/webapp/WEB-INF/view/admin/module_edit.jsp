<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<sf:form action="/admin/module/add" method="post" modelAttribute="${moduleData}"> <!-- style="width: 250px;" -->
    <div class="form-group">
    	<form:input path="shortName" id="short-name" cssClass="form-control" />
    </div>
    <div class="form-group">
	   	<form:select path="room" >
	   		<c:forEach items="${rooms }" var="room">
	   			<form:option value="${room.id }"><u:i18n value="${room.name }" /></form:option>
	   		</c:forEach>
	   	</form:select>
    </div>
    <button type="submit" class="btn btn-primary">Create module</button>
</sf:form>

<sf:form action="" id="module-edit" method="post">

    <input type="hidden" name="short-name" value="<%=form.getShortName() %>"/>
    <input type="hidden" name="room-id" value="<%=form.getRoomId() %>"/>

    <div class="form-group">
        <label>Module directory:</label>
        <input type="text" value="<%=form.getShortName() %>" class="form-control" disabled="disabled"/>
    </div>
    <div class="form-group">
        <label>Module name:</label>

        <div id="name-tabs">
            <ul>
                <li><a href="#name-tabs-en">en</a></li>
                <li><a href="#name-tabs-uk">uk</a></li>
            </ul>
            <div id="name-tabs-en">
                <input type="text" name="name_en" class="form-control"
                       value="<%=(form == null) ? "" : form.getName().getByLanguage("en").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>"/>
            </div>
            <div id="name-tabs-uk">
                <input type="text" name="name_uk" class="form-control"
                       value="<%=(form == null) ? "" : form.getName().getByLanguage("uk").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="module-image">Image url:</label>
        <input type="text" name="image" id="module-image" class="form-control" value="<%=form.getImageUrl()%>"/>
    </div>
    <div class="form-group">
        <label>Module description:</label>

        <div id="description-tabs">
            <ul>
                <li><a href="#description-tabs-en">en</a></li>
                <li><a href="#description-tabs-uk">uk</a></li>
            </ul>
            <div id="description-tabs-en">
                <textarea class="form-control"
                          name="description_en"><%=form.getDescription().getByLanguage("en").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>
                </textarea>
            </div>
            <div id="description-tabs-uk">
                <textarea class="form-control"
                          name="description_uk"><%=form.getDescription().getByLanguage("uk").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>
                </textarea>
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
                <textarea id="module-content_en" class="form-control" style="height: 300px"
                          name="content_en"><%=form.getContent().getByLanguage("en").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>
                </textarea>
            </div>
            <div id="content-tabs-uk">
                <textarea id="module-content_uk" class="form-control" style="height: 300px"
                          name="content_uk"><%=form.getContent().getByLanguage("uk").replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>
                </textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="module-embed">Embed code:</label>
        <textarea class="form-control" id="module-embed" style="height: 300px"
                  name="embed"><%=form.getEmbedCode().replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>
        </textarea>
    </div>

    <button type="submit" class="btn btn-primary"><%=messages.getString(VmlResources.FM_BUTTON_CONFIRM)%>
    </button>
</sf:form>

<script src="<%=request.getContextPath()%>/js/tinymce/tinymce.min.js"></script>
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