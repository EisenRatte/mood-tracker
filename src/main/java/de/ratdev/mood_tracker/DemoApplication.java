package de.ratdev.mood_tracker;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.UnknownHostException;
import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*
 * Use the standard Mongo driver API to create a com.mongodb.Mongo instance.
 */
	public @Bean
	MongoClient mongoClient(@Value("${mongo.host}") String host,
							@Value("${mongo.db}") String database,
							@Value("${mongo.username}") String username,
							@Value("${mongo.pw}") String password) throws UnknownHostException {
		ArrayList<MongoCredential> credentials = new ArrayList<>();
		credentials.add(MongoCredential.createCredential(username, database, password.toCharArray()));
		MongoClient mongoClient = new MongoClient(new ServerAddress(host), credentials);
		mongoClient.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
		return mongoClient;
	}

	public @Bean
	MongoDbFactory mongoDbFactory(MongoClient client,
								  @Value("${mongo.db}") String database){
		return new SimpleMongoDbFactory(client, database);
	}


}
