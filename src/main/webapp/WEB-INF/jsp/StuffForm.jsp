<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../jsp/head.jsp" %>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%@include file="../jsp/menu.jsp" %>
    <main class="mdl-layout__content">
        <div class="page-content">
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-card mdl-shadow--6dp">
                        <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                            <h2 class="mdl-card__title-text">
                                <c:if test="${stuff != null}">Edit</c:if>
                                <c:if test="${stuff == null}">Add New</c:if>
                            </h2>
                        </div>

                        <div class="mdl-card__supporting-text">
                            <c:if test="${stuff != null}">
                            <form name="stuffForm" action="update" method="post"
                                  onsubmit="return validateForm()">
                                </c:if>
                                <c:if test="${stuff == null}">
                                <form name="stuffForm" action="insert" method="post"
                                      onsubmit="return validateForm()">
                                    </c:if>
                                    <c:if test="${stuff != null}">
                                    <input type="hidden" name="id" value="<c:out value='${stuff.getId()}'/>"/>
                                    </c:if>
                                    <div class="mdl-textfield mdl-js-textfield">
                                        <input class="mdl-textfield__input" type="text" name="name"
                                               value="<c:out value='${stuff.getName()}'/>" id="name"/><label
                                            class="mdl-textfield__label" for="name">Name</label>
                                    </div>
                                    <div class="mdl-textfield mdl-js-textfield">
                                        <input class="mdl-textfield__input" type="text" name="description"
                                               value="<c:out value='${stuff.getDescription()}'/>" id="description"/>
                                        <label class="mdl-textfield__label" for="description">Description</label>
                                    </div>
                                    <div class="mdl-textfield mdl-js-textfield">
                                        <c:choose>
                                            <c:when test="${stuff != null}">
                                                <input class="mdl-textfield__input" type="text" name="quantity"
                                                       value="<c:out value='${stuff.getQuantity()}'/>" id="quantity">
                                            </c:when>
                                            <c:otherwise>
                                                <input class="mdl-textfield__input" type="text" name="quantity"
                                                       value="<c:out value='1'/>" id="quantity">
                                            </c:otherwise>
                                        </c:choose>
                                        <label class="mdl-textfield__label" for="quantity">Quantity</label>
                                    </div>
                                    <input type="submit"
                                           class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                           value="save">
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<script type="text/javascript">
    function validateForm() {
        var quantity = document.forms["stuffForm"]["quantity"].value;
        var name = document.forms["stuffForm"]["name"].value
        if (name == "" || quantity == "") {
            alert("Name/Quantity must be filled out")
            return false;
        }
    }
</script>
</body>
</html>