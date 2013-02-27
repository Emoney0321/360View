<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>360 View</title>
<link href="/resources/css/application.css" rel="stylesheet" />
<link href="/resources/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="well">
				<h3>Click on the map or a history link to view images for a 360 degree view of that location.</h3>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2 bs-docs-sidebar">
				<h3>Coordinate History</h3>
				<ul id="history" class="nav nav-list bs-docs-sidenav affix-top">
					<%@ include file="coordinates.jsp" %>
				</ul>
			</div>
			<div class="span10">
				<div class="row-fluid">
					<div id="map_canvas"></div>
				</div>
				<div class="row-fluid">
					<p id="lat-long"></p>
				</div>
				<div class="row-fluid">
					<div class="span3"></div>
					<div class="span3"></div>
					<div class="span3"></div>
					<div class="span3"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDGDqUmYaz8gMU1L-GQLACO2Hi0icQAosI&sensor=false"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="/resources/js/jquery.json-2.4.js"></script>
	<script src="/resources/js/application.js"></script>
</body>
</html>