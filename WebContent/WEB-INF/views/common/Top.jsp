<%
/* 
@Project : WIC
@File name : Top.jsp
@Date : 2020.11.10
@Author : 문지연
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/18cfffd9e0.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="resource/style/top-style.css">
    <title>Document</title>
</head>
<body>
	<c:set var="id" value="${sessionScope.id }"/>
    <header>
    	<nav id="topNav">
        <ul>
            <li class="logo"><a href="<%=request.getContextPath()%>/mainPage.my">WIC</a></li>
            <li class="btn-bar"><span class="fas fa-bars"></span></li>
            <div class="nav-items">
            <c:choose >
            	<c:when test="${not empty id }">
	            	<c:if test="${id=='admin@admin.com'}">
	                <li><a href="<%=request.getContextPath()%>/mainPage.my" class="active">HOME</a></li>
	                <li><a href="<%=request.getContextPath()%>/myPage.my">MY CLOSET</a></li>
	                <li><a href="<%=request.getContextPath()%>/managePage.Mg">MANAGE</a></li>
	                <li><a href="<%=request.getContextPath()%>/ProductListPage.Pd">PRODUCT</a></li>
	                <li><a href="<%=request.getContextPath()%>/signOut.my">LOGOUT</a></li>

	                </c:if>
	                <c:if test="${id !='admin@admin.com' }">
	                <li><a href="<%=request.getContextPath()%>/mainPage.my" class="active">HOME</a></li>
	                <li><a href="<%=request.getContextPath()%>/myPage.my">MY CLOSET</a></li>
	                <li><a href="<%=request.getContextPath()%>/ProductListPage.Pd">PRODUCT</a></li>
	                <li><a href="<%=request.getContextPath()%>/signOut.my">LOGOUT</a></li>
	                </c:if>
	                </c:when>
                <c:otherwise>
                <li><a href="<%=request.getContextPath()%>/mainPage.my" class="active">HOME</a></li>
                <li><a href="<%=request.getContextPath()%>/ProductListPage.Pd">PRODUCT</a></li>
                <li><a href="<%=request.getContextPath()%>/signUpPage.my">SIGN IN/REGISTER</a></li>
                </c:otherwise>
            </c:choose>   
            </div>
            <li class="search-icon">
                <form action="#">
                    <input id="search-input" class="search-input" type="search" placeholder="Search" required>
                    <label class="icon">
                        <span class="fas fa-search"></span>
                    </label>
                </form>
            </li>
        </ul>
    </nav>
    </header>
    
    <script>
        $("nav ul li.btn-bar span").click(function(){
                 $("nav ul div.nav-items").toggleClass("show");
                 $("nav ul li.btn-bar span").toggleClass("show");
             });
     </script>
</body>
</html> 