<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<form action="" method="post">
    <div class="form-group" style="width: 250px;">
        <label for="system_role">System role:</label>
        <select id="system_role" name="system_role" class="form-control">
        </select>
    </div>
    <button type="submit" class="btn btn-primary"><s:message code="form.button.confirm" /></button>
</form>
<!-- TODO: add course management form -->