<script src="/js/tinymce/tinymce.min.js"></script>
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

<form action="" id="module-edit" method="post">
    <div class="form-group">
        <label><%=messages.getString(VmlResources.FM_MODULE_NAME_LBL)%>
        </label>

        <div id="name-tabs">
            <div class="form-group">
            </div>
            <ul>
                <li><a href="#name-tabs-en">en</a></li>
                <li><a href="#name-tabs-uk">uk</a></li>
            </ul>
            <div id="name-tabs-en">
                <input type="text" name="name_en" class="form-control"
                       value="<%=(data == null) ? "" : data.getName().getByLanguage("en")%>"/>
            </div>
            <div id="name-tabs-uk">
                <input type="text" name="name_uk" class="form-control"
                       value="<%=(data == null) ? "" : data.getName().getByLanguage("uk")%>"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="url-suffix">Page url suffix:</label>
        <input type="text" name="url-suffix" id="url-suffix" class="form-control"
               value="<%=(data == null) ? "" : data.getSuffix()%>"/>
    </div>
    <div class="form-group">
        <label>Page content:</label>

        <div id="content-tabs">
            <ul>
                <li><a href="#content-tabs-en">en</a></li>
                <li><a href="#content-tabs-uk">uk</a></li>
            </ul>
            <div id="content-tabs-en">
                <textarea id="page-content_en" name="content_en"
                          class="form-control"><%=data.getContent().getByLanguage("en")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
            <div id="content-tabs-uk">
                <textarea id="page-content_uk" name="content_uk"
                          class="form-control"><%=data.getContent().getByLanguage("uk")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
                </textarea>
            </div>
        </div>
    </div>
    <button class="btn btn-primary" type="submit"><%=messages.getString(VmlResources.FM_BUTTON_CONFIRM)%>
    </button>
</form>