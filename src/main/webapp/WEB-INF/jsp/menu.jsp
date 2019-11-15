<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">CRUD Application</span>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation mdl-layout--large-screen-only">

            <a class="mdl-navigation__link" href="/new">Add New</a>

            <a class="mdl-navigation__link" href="/list">Show All</a>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">CRUD Application</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="/new">Add New</a>
        <a class="mdl-navigation__link" href="/list">Show All</a>
    </nav>
</div>