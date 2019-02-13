<%@ page import="domain.model.view.UserProfileViewModel" %>
<%@ page import="domain.model.view.TubeProfileViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%UserProfileViewModel model = (UserProfileViewModel) request.getAttribute("model");
int i = 1;%>
<c:import url="templates/navbar.jsp"/>
<main>
    <hr class="my-2"/>
    <div class="text-center mt-3">
        <h4 class="text-info text-center">@<%=model.getUsername()%>
        </h4>
        <h4 class="text-info text-center">(<%=model.getEmail()%>)</h4>
    </div>
    <hr>
    <div class="container-fluid">
        <div class="row d-flex flex-column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Author</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for (TubeProfileViewModel tube : model.getTubes()) { %>
                <tr>
                    <th scope="col"><%=i++%></th>
                    <th scope="col"><%=tube.getTitle()%></th>
                    <th scope="col"><%=tube.getAuthor()%></th>
                    <th scope="col"><a href="/tube/details?id=<%=tube.getId()%>">Details</a></th>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <hr class="my-3"/>
</main>
<c:import url="templates/footer.jsp"/>
</body>
</html>
