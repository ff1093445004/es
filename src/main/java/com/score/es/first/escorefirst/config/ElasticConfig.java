package com.score.es.first.escorefirst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.score.es.first.escorefirst.repository")
public class ElasticConfig {
	// private static Logger log = LoggerFactory.getLogger(ElasticConfig.class);

	// @Bean
	// public Client client() {
	// TransportClient client = null;
	// try {
	// client = new PreBuiltTransportClient(Settings.EMPTY);
	//// .addTransportAddress(new
	// InetSocketTransportAddress(InetAddress.getByName("host1"), 9300));
	//
	// } catch (Exception e) {
	// log.error(e.toString());
	// }
	// return client;
	// }
	//
	// @Bean
	// public ElasticsearchTemplate elasticsearchTemplate(Client client) throws
	// Exception {
	// return new ElasticsearchTemplate(client);
	// }
}