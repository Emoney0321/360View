<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="coordinate" items="${coordinates.coordinates}">
	<li data-location='{ "latitude" : "${coordinate.latitude}", "longitude" : "${coordinate.longitude}" }'>
		<a><i class="icon-chevron-right"></i>${coordinate.latitude}, ${coordinate.longitude}</a>
	</li>
</c:forEach>