package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Metrics;
import com.capitalone.dashboard.model.MetricsGroup;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

public interface MetricsRepository extends CrudRepository<Metrics, ObjectId>, QueryDslPredicateExecutor<Metrics> {
	
	List<Metrics> findByCollectorItemId(ObjectId collectorItemId, String environment);
	
	default List<MetricsGroup> getAggregatedMetrics(MongoTemplate template, String environment) { 
			Aggregation agg = newAggregation(
					group("methodName").sum("count").as("totalCount"), 
	 				project("totalCount").and("methodName").previousOperation(), 
					sort(Sort.Direction.DESC, "totalCount")); 
			AggregationResults<MetricsGroup> groupResults = template.aggregate(agg, Metrics.class, MetricsGroup.class); 
			return groupResults.getMappedResults(); 
	 	} 
}
