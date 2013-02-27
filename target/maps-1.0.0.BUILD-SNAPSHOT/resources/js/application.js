$(function() {
	initialize();
});

var map = null;
var currentMarker = null;
var streetViewTemplate = 'http://maps.googleapis.com/maps/api/streetview?size=400x400&location={0},%20{1}&heading={2}&sensor=false&key=AIzaSyDGDqUmYaz8gMU1L-GQLACO2Hi0icQAosI';
var history = $('#history');
var latLong = $('#lat-long');

$('ul').on('click', 'li', function() {
	var coordinates = $(this).data('location');
	processMarker(new google.maps.LatLng(coordinates.latitude, coordinates.longitude), false);
});

function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(40.820646, -96.708527),
		zoom : 10,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		streetViewControl : false
	};
	map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

	google.maps.event.addListener(map, 'click', function(event) {
		processMarker(event.latLng, true);
	});
}

function processMarker(location, addHistory) {
	hideMarker();
	placeMarker(location);
	for (var i = 0; i < 4; i++) {
		loadImage(location, 90 * i, i + 1);
	}
	var latitude = location.lat().toFixed(6);
	var longitude = location.lng().toFixed(6);
	latLong.html(latitude + ', ' + longitude);
	var coordinate = {
		'latitude' : latitude,
		'longitude' : longitude
	};
	
	if (addHistory) {
		addCoordinateHistory(coordinate);
	}
}

function placeMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
	currentMarker = marker;
	map.setCenter(location);
}

function addCoordinateHistory(coordinate) {
	$.ajax({
		url : '/coordinates',
		type : 'post',
		data : JSON.stringify(coordinate),
		contentType : 'application/json',
		dataType : 'html',
		success : function(data) {
			history.html(data);
		}
	});
}

function hideMarker() {
	if (currentMarker != null) {
		currentMarker.setMap(null);
	}
}

function loadImage(location, heading, i) {
	var callback = function() {
		var nthElement = 'div.span3:nth-child(' + i + ')';
		$(nthElement).html(this);
	};
	$('<img src="' + streetViewTemplate.format(location.lat(), location.lng(), heading) + '">').load(callback);
}

String.format = String.prototype.format = function() {
	var string = this;
	for ( var i = 0; i < arguments.length; i++) {
		string = string.replace(/\{\d+?\}/, arguments[i]);
	}
	return string;
};