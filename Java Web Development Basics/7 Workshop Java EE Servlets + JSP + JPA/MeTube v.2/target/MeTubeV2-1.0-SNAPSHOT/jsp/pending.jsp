<%@ page import="domain.model.view.TubeDetailsViewModel" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%
    List<TubeDetailsViewModel> tubes = (List<TubeDetailsViewModel>) request.getSession().getAttribute("tubes");
    int i = 1;
%>
<c:import url="templates/navbar.jsp"/>
<main>
    <hr class="my-2"/>
    <div class="text-center mt-3">
        <h2 class="text-info text-center">Pending Tubes</h2>
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
                <% for (TubeDetailsViewModel tube : tubes) { %>
                <tr>
                    <th scope="col"><%=i++%>
                    </th>
                    <th scope="col"><%=tube.getTitle()%>
                    </th>
                    <th scope="col"><%=tube.getAuthor()%>
                    </th>
                    <th>
                        <a class="btn btn-success" href="/admin/tube/approve?id=<%=tube.getId()%>">Approve</a>
                        <a class="btn btn-danger" href="/admin/tube/decline?id=<%=tube.getId()%>">Decline</a>
                    </th>
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
