package com.capitalone.dashboard.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.model.Metrics;
import com.capitalone.dashboard.model.MetricsGroup;
import com.capitalone.dashboard.service.MetricsService;

@RestController
public class MetricsController {
    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @RequestMapping(value = "/metrics/{env}", method = GET, produces = APPLICATION_JSON_VALUE)
    public DataResponse<Iterable<Metrics>> qualityData(
			@RequestParam(value = "component", required = true) String cId,
			@PathVariable String env) {
    	ObjectId componentId = new ObjectId(cId);
        return this.metricsService.search(componentId, env);
    }

    @RequestMapping(value = "/metrics/aggregate/{env}", method = GET, produces = APPLICATION_JSON_VALUE)
	public DataResponse<List<MetricsGroup>> getAggregatedMetrics(
			@RequestParam(value = "component", required = true) String cId,
			@PathVariable String env) {
    	ObjectId componentId = new ObjectId(cId);
        return this.metricsService.getAggregatedMetrics(componentId, env);
    }
    
}
