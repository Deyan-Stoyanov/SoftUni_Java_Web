<%@ page import="java.util.List" %>
<%@ page import="domain.models.view.TubeAllViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% List<TubeAllViewModel> tubes = (List<TubeAllViewModel>) request.getSession().getAttribute("allTubes"); %>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>All Tubes</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>Check our tubes below.</h3>
                </div>
            </div>
            <hr/>
            <% if (tubes == null || tubes.isEmpty()) { %>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>No tubes - <a href="/tubes/create">Create some</a>!</h3>
                </div>
            </div>
            <% } else { %>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <ul>
                        <% for (TubeAllViewModel tubeAllViewModel : tubes) { %>
                        <li>
                            <a href="/tubes/details?name=<%= tubeAllViewModel.getName() %>"><%= tubeAllViewModel.getName() %>
                            </a>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
            <% } %>
            <hr/>
            <div class="row d-flex justify-content-center">
                <a href="/">Back to Home.</a>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
