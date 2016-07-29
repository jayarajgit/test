/**
 * Gets code repo related data
 */
(function () {
    'use strict';

    angular
        .module(HygieiaConfig.module + '.core')
        .factory('metricsData', metricsData)
        .factory('metricsAggregatedData', metricsAggregatedData);

    function metricsData($http) {
        var testMetricsDetailRoute = 'test-data/metrics-data.json';
        var metricsDetailRoute = '/api/metrics/';

        return {
            details: details
        };

        //HygieiaConfig.local ? testDetailRoute : caDetailRoute
        function details(params, env) {
        	var url = metricsDetailRoute +"/"+env;
        	console.log(" Usage Collector metrics url" + url)
            return $http.get(HygieiaConfig.local ? testMetricsDetailRoute : url, { params: params })
            .then(function (response) {
                return response.data;
            });
    }
    }
    
    function metricsAggregatedData($http) {
        var testMetricsAggRoute = 'test-data/metrics-data.json';
        var metricsAggRoute = '/api/metrics/aggregate';

        return {
            details: details
        };

        //HygieiaConfig.local ? testDetailRoute : caDetailRoute
        function details(params, env) {
        	var url = metricsAggRoute +"/"+env;
        	console.log(" Usage Collector metrics url" + url)
        	console.log(" Usage Collector metrics params: " + params.component)
            return $http.get(HygieiaConfig.local ? testMetricsAggRoute : url, { params: params })
            .then(function (response) {
                return response.data;
            });
    }
    }
})();