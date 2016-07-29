package com.capitalone.dashboard.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.model.Metrics;
import com.capitalone.dashboard.model.MetricsGroup;

public interface  MetricsService {
	 DataResponse<Iterable<Metrics>> search(ObjectId componentId,String env);
	 
	 DataResponse<List<MetricsGroup>> getAggregatedMetrics(ObjectId componentId,String env);
	 
}
