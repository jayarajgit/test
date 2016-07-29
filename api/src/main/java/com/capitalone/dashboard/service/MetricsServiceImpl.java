package com.capitalone.dashboard.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.model.Metrics;
import com.capitalone.dashboard.model.MetricsGroup;
import com.capitalone.dashboard.model.QMetrics;
import com.capitalone.dashboard.repository.MetricsRepository;
import com.mysema.query.BooleanBuilder;

@Service
public class  MetricsServiceImpl implements MetricsService {
	private static final Log LOG = LogFactory.getLog(MetricsServiceImpl.class);
	private final MetricsRepository metricsRepository;
	private final MongoTemplate mongoTemplate;

    @Autowired
    public MetricsServiceImpl(MetricsRepository metricsRepository, MongoTemplate mongoTemplate) {
        this.metricsRepository = metricsRepository;
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	public DataResponse<Iterable<Metrics>> search(ObjectId componentId, String env) {
		final String METHOD_NAME = "MetricsServiceImpl :: search";
		LOG.info(METHOD_NAME+"  Input " +env);
        QMetrics metrics = new QMetrics("metrics");
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(metrics.environment.eq(env));
        return new DataResponse<>(metricsRepository.findAll(builder.getValue()), System.currentTimeMillis());
    }

	@Override
	public DataResponse<List<MetricsGroup>> getAggregatedMetrics(ObjectId componentId, String env) {
		final String METHOD_NAME = "MetricsServiceImpl :: getAggregatedMetrics";
		LOG.info(METHOD_NAME+"  Input " +env);
		return  new DataResponse<>(metricsRepository.getAggregatedMetrics(mongoTemplate, env), System.currentTimeMillis());
	}
	
	
}
