package my.app.elasticsearch.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class BuildingService {
	final Logger logger = LoggerFactory.getLogger(BuildingService.class);
	@Inject
	BuildingSearchRepository buildingSearchRepository;

	public List<ESBuilding> getNearestBuildings(String latitude, String longitude)
			throws IOException {
		List<ESBuilding> buildings = buildingSearchRepository.getBuildings(latitude, longitude);
		return buildings;
	}
}