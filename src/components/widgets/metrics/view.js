(function() {
	'use strict';

	angular.module(HygieiaConfig.module).controller('MetricsViewController',
			MetricsViewController);

	MetricsViewController.$inject = [ '$q', '$scope', 'metricsAggregatedData', 'metricsData', '$modal' ];
	function MetricsViewController($q, $scope, metricsAggregatedData,metricsData, $modal) {
		var ctrl = this;

		ctrl.metricsChartOptions = {
				 plugins: [
				Chartist.plugins.ctPointLabels({
                    textAnchor: 'middle'
                })
				],
				// Default mobile configuration
				  stackBars: true,
				  axisX: {
				    labelInterpolationFnc: function(value) {/*
				      return value.split(/\s+/).map(function(word) {
				        return word[0];
				      }).join('');
				    */
				    	return value.slice(0,7);
				    }
				  },
				  axisY: {
				    offset: 20
				  }
				
		};
		
		ctrl.metricsChartOptionsLow = {
				 plugins: [
				Chartist.plugins.ctPointLabels({
                   textAnchor: 'middle'
               })
				],
				// Default moble configuration
				  stackBars: true,
				  axisX: {
				    labelInterpolationFnc: function(value) {/*
				      return value.split(/\s+/).map(function(word) {
				        return word[0];
				      }).join('');
				    */
				    	return value.slice(0,7);
				    }
				  },
				  axisY: {
				    offset: 20
				  }
				
		};
		ctrl.metricsDetailTemp = [];
		ctrl.showDetail = showDetail;
		ctrl.load = function() {
			var deferred = $q.defer();
			var params = {
				component : $scope.widgetConfig.componentId
			};
			var env = "QA1";//$scope.widgetConfig.options.metricsEnv;
			metricsAggregatedData.details(params, env).then(function(data) {
				processResponse(data.result);
				deferred.resolve(data.lastUpdated);
			});
			metricsData.details(params, env).then(function(data) {
				ctrl.metricsDetailTemp = data.result;
				deferred.resolve(data.lastUpdated);
			});
			
			return deferred.promise;
		};

		function showDetail() {
            $modal.open({
                controller: 'MetricsDetailController',
                controllerAs: 'metricsDetail',
                templateUrl: 'components/widgets/metrics/detail.html',
                size: 'lg',
                resolve: {
                	metricsDetailTemp: function() {
                        return ctrl.metricsDetailTemp;
                    }
                }
            });
        }
		
		function processResponse(data) {
			var dataCollection = [];
			var countCollection = [];
			var maxRecords = 5;
			for (var i = 0; i < data.length && i < maxRecords; i++) {
				console.log(data[i].methodName);
				dataCollection.push(data[i].methodName);
				countCollection.push(data[i].totalCount);
			}
			ctrl.metricsChartData = {
				labels : dataCollection,
				series : [ countCollection ]
			};
			
			ctrl.metricsChartDataLow = {
					labels: ['1', '2', '3','4', '5'],
					  series: [
					    [12, 11, 10,6,7],
					    [9, 8, 7,8,5],
					    [3, 2, 5,1,2]
					   
					  ]
				};
			
		}
	}
})();
