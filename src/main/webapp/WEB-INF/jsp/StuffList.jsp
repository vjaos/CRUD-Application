<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<%@include file="../jsp/head.jsp" %>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixedheader">
    <%@include file="../jsp/menu.jsp" %>
    <div class="mdl-layout__content">
        <div class="page-content">
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--4-col">
                    <div>
                        <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                            <thead>
                            <tr>
                                <th class="mdl-data-table__cell--non-numeric">NO</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="stuff" items="${listStuff}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td class="mdl-data-table__cell-non-numeric">
                                        <c:out value="${count}"/>
                                    </td>
                                    <td><c:out value="${stuff.getName()}"/></td>
                                    <td><c:out value="${stuff.getQuantity()}"/></td>
                                    <td><c:out value="${stuff.getDescription()}"/></td>
                                    <td>
                                        <a href="/edit?id=<c:out value='${stuff.getId()}'/>">Edit</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="/delete?id=<c:out value='${stuff.getId()}'/>">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>