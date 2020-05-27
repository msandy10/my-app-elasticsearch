package my.app.elasticsearch.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller("/buildings")
public class LocationController {
	private static Logger logger = LoggerFactory.getLogger(LocationController.class);
	@Inject
	BuildingService buildingService;

	@Get(uri = "/latitude/{latitude}/longitude/{longitude}", produces = "application/json")
	public List<ESBuilding> searchNearByBuildings(@NotEmpty @PathVariable("latitude") final String latitude,
			@NotEmpty @PathVariable("longitude") final String longitude) throws Exception {
		return buildingService.getNearestBuildings(latitude, longitude);
	}
}
