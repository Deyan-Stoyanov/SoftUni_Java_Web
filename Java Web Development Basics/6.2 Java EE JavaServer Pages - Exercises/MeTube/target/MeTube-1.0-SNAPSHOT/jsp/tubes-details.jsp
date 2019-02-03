<%@ page import="domain.models.view.TubeDetailsViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
    <% TubeDetailsViewModel tube = (TubeDetailsViewModel) request.getSession().getAttribute("tube"); %>
    <div class="container">
        <main>
            <div class="jumbotron">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1><%= tube.getName() %></h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h3><%= tube.getDescription() %></h3>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-6 text-center">
                        <a href="<%= tube.getYoutubeLink() %>" target="_blank">Link to Video.</a>
                    </div>
                    <div class="col-md-6 text-center">
                        <p><%= tube.getUploader() %></p>
                    </div>
                </div>
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
