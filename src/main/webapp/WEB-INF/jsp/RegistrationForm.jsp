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
                            <h2 class="mdl-card__title-text">Registration</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <form name="userForm" action="" method="post"
                                  onsubmit="return validateForm()">
                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="text" name="firstname"
                                           value="" id="firstname"/><label
                                        class="mdl-textfield__label" for="firstname">First Name</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="text" name="lastname"
                                           value="" id="lastname"/>
                                    <label class="mdl-textfield__label" for="lastname">Last Name</label>
                                </div>
                                    <div class="mdl-textfield mdl-js-textfield">
                                        <input class="mdl-textfield__input" type="text" name="username"
                                               value="" id="username"/>
                                        <label class="mdl-textfield__label" for="username">Username</label>
                                    </div>
                                    <div class="mdl-textfield mdl-js-textfield">
                                        <input class="mdl-textfield__input" type="text" name="password"
                                               value="" id="password"/>
                                        <label class="mdl-textfield__label" for="password">Password</label>
                                    </div>
                                <input type="submit"
                                       class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                       value="register">
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
        var first_name = document.forms["userForm"]["firstname"].value;
        var last_name = document.forms["userForm"]["lastname"].value
        var username = document.forms["userForm"]["username"].value
        var password = document.forms["userForm"]["password"].value
        if (first_name == "" || last_name == "" || username == "" || password == "") {
            alert("All fields must be filled out")
            return false;
        }
    }
</script>
</body>
</html>