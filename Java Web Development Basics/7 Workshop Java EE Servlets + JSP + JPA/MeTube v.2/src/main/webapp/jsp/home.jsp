<%@ page import="java.util.List" %>
<%@ page import="domain.model.view.TubeDetailsViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%List<TubeDetailsViewModel> tubes = (List<TubeDetailsViewModel>) request.getSession().getAttribute("tubes");%>
<c:import url="templates/navbar.jsp"/>
<%String username = (String) request.getSession().getAttribute("username");%>
<hr class="my-2"/>
<div class="text-center mt-3">
    <h4 class="h4 text-info">Welcome, <%=username%>
    </h4>
</div>
<hr class="my-4">
<div class="container">
    <div class="row mx-3 my-5">
        <%for (TubeDetailsViewModel tube : tubes) {%>
        <div class="box-a col-xs-12 col-sm-4 pull-right text-center">
            <a href="/tube/details?id=<%=tube.getId()%>">
                <img src="https://img.youtube.com/vi/<%=tube.getYoutubeId()%>/mqdefault.jpg"
                     alt="youtube-thumbnail-picture">
            </a>
            <p class="mt-3"><%=tube.getTitle()%>
            </p>
            <p class="font-italic mt-3"><%=tube.getAuthor()%>
            </p>
        </div>
        <%}%>
    </div>
</div>
<hr class="my-3"/>
<c:import url="templates/footer.jsp"/>
</body>
</html>
