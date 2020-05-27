package my.app.elasticsearch.service;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.context.annotation.Value;

@Singleton
public class BuildingSearchRepository{
	private static final String LOCATION = "location";

	final Logger logger = LoggerFactory.getLogger(BuildingSearchRepository.class);

	@Value("${elasticsearch.http-hosts}")
	String host;
	
	@Value("${elasticsearch.port}")
	Integer port;

	@Value("${elasticsearch.protocol}")
	String protocol;
	
	int pageSize=20;

	String range = "5";

	@Inject
	RestHighLevelClient client;

	public List<ESBuilding> getBuildings(String latitude,String longitude) throws IOException {
        QueryBuilder queryBuilder = boolQuery().filter(
                QueryBuilders
                  .geoDistanceQuery(LOCATION)
                  .point(Double.parseDouble(latitude),Double.parseDouble(longitude))
                  .distance(Double.parseDouble(range), DistanceUnit.KILOMETERS)
                );
        GeoDistanceSortBuilder sortBuilderAsc = SortBuilders.geoDistanceSort(LOCATION, new Double(latitude), new Double(longitude))
                .order(SortOrder.ASC)
                .unit(DistanceUnit.METERS)
                .sortMode(SortMode.MIN);
		List<ESBuilding> searchBuilders = searchByquery(queryBuilder, sortBuilderAsc, 0, pageSize,
				 "7_buildings");

		return searchBuilders;
	}

	public List<ESBuilding> searchByquery(QueryBuilder query, GeoDistanceSortBuilder sortBuilderAsc, int pageNumber, int pageSize, String index)
			throws IOException {
		List<ESBuilding> esBuildings = new ArrayList<>();
		SearchResponse response = searchByQueryAndSort(query, sortBuilderAsc, pageNumber, pageSize, index);
		for (SearchHit searchHit : response.getHits()) {
			ESBuilding esBuilding = new ObjectMapper().readValue(searchHit.getSourceAsString(), ESBuilding.class);
			esBuildings.add(esBuilding);
		}
		return esBuildings;
	}

	  private SearchResponse searchByQueryAndSort(QueryBuilder query, SortBuilder<GeoDistanceSortBuilder>sortBuilderAsc, int pageNumber, int pageSize, String index) {
	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	        SearchRequest searchRequest = new SearchRequest(index);
	        int pageOffset = pageNumber == 0 ? pageNumber : (pageNumber * pageSize);
	        searchSourceBuilder.query(query).from(pageOffset).size(pageSize).sort(sortBuilderAsc);
	        logger.info(searchSourceBuilder.toString());
	        searchRequest.source(searchSourceBuilder);
	        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
	        SearchResponse response = null;
	        try {
	            response = getClient().search(searchRequest, RequestOptions.DEFAULT);
	        } catch (IOException e) {
	            logger.error("Error while querying the SearchQuery", e);
	        }
	        return response;
	    }
	  
	public RestHighLevelClient getClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost(host,port,protocol)));
	}

}