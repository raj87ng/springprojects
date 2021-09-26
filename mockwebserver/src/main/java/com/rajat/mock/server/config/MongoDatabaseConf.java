package com.rajat.mock.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.rajat.mock.server.dao" )
public class MongoDatabaseConf extends AbstractReactiveMongoConfiguration{

	@Value("${mongo.db.port:27017}")
    private String port;
     
    @Value("${mongo.db.name:TestDB}")
    private String dbName;
    
    
	@Override
	protected String getDatabaseName() {
		return dbName;
	}
	
	@Override
	public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }
	
	@Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
