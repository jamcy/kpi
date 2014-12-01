<%@page import="java.util.Locale" %>
<%@page import="java.util.ResourceBundle" %>
<%@page import="resource.VmlResources" %>
<%@page import="model.form.ModuleEditForm" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
	ModuleForm form = (ModuleForm) request
            .getAttribute("form_data");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    ResourceBundle messages = ResourceBundle.getBundle(
            "resource.VmlResources", new Locale(lang));
%>

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
<form action="" id="module-edit" method="post">

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
</form>