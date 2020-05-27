package my.app.elasticsearch.service;

import javax.inject.Singleton;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;

@Factory
public class ElasticSearchConfig {
	@Value("${elasticsearch.http-hosts}")
	String host;
	
	@Value("${elasticsearch.port}")
	Integer port;

	@Value("${elasticsearch.protocol}")
	String protocol;

	@Bean
	@Singleton
	 RestHighLevelClient getClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost(host,port,protocol)));
    }

}
