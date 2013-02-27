package com.eric.maps;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eric.maps.svo.Coordinate;
import com.eric.maps.svo.Coordinates;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

@Controller
public class MapController {

	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
	public String home(ModelMap map, HttpServletRequest request) throws URISyntaxException {
		retrieveCoordinates(map, request);
		return "home";
	}

	@RequestMapping(value = "/coordinates", method = RequestMethod.POST, consumes = "application/json", produces = "text/html")
	public String addCoordinate(@RequestBody Coordinate coordinate, ModelMap map, HttpServletRequest request) throws URISyntaxException {
		Entity coordinateEntity = new Entity("Coordinate");
		coordinateEntity.setProperty("latitude", coordinate.getLatitude());
		coordinateEntity.setProperty("longitude", coordinate.getLongitude());
		datastore.put(coordinateEntity);
		return getCoordinates(map, request);
	}

	@RequestMapping(value = "/coordinates", method = RequestMethod.GET, produces = "text/html")
	public String getCoordinates(ModelMap map, HttpServletRequest request) throws URISyntaxException {
		retrieveCoordinates(map, request);
		return "coordinates";
	}

	private void retrieveCoordinates(ModelMap map, HttpServletRequest request) throws URISyntaxException {
		Query coordinateQuery = new Query("Coordinate");
		List<Entity> entities = datastore.prepare(coordinateQuery).asList(FetchOptions.Builder.withLimit(500));
		Coordinates coordinates = new Coordinates();
		coordinates.setUri(new URI(request.getRequestURI()));
		for (Entity entity : entities) {
			coordinates.addCoordinate(new Coordinate((String) entity.getProperty("latitude"), (String) entity.getProperty("longitude")));
		}
		map.addAttribute(coordinates);
	}
	
}
