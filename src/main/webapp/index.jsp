<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body class="boxed">
<div id="wrap">
    <!-- Preload -->
    <div id="loader-wrapper">
        <div id="loader"></div>
        <div class="loader-section section-left"></div>
        <div class="loader-section section-right"></div>
    </div><!-- End Preload -->

    <jsp:include page="html/header.jsp"/>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.scrollTo.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ResizeSensor.min.js"></script>
<script src="${pageContext.request.contextPath}/js/theia-sticky-sidebar.min.js"></script>
<script src="${pageContext.request.contextPath}/js/parallax.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.themepunch.plugins.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.themepunch.revolution.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/imagesloaded.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.js"></script>
</body>
</html>
