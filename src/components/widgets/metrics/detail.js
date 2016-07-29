(function() {
	'use strict';

	angular.module(HygieiaConfig.module).controller('MetricsDetailController',
			MetricsDetailController);

	MetricsDetailController.$inject = ['$scope', '$filter', 'NgTableParams', 'metricsDetailTemp' ];
	function MetricsDetailController($scope,$filter,  NgTableParams, metricsDetailTemp) {
		var ctrl = this;
		
		ctrl.totalNoTransPerPage = 0;
		ctrl.totalNoTrans = 0;
		ctrl.env = "N/A";
		ctrl.title = "";
		ctrl.metricsData = metricsDetailTemp;
		$scope.data = ctrl.metricsData;

		ctrl.metricsTable = new NgTableParams({
			page : 1,
			count : 10
		}, {
			total : $scope.data.length,
			getData : function($defer, params) {
				var temp = ctrl.metricsData;
				ctrl.title = "GCH API Usage Metrics";
				if(ctrl.metricsData.length > 0) {
					ctrl.env = ctrl.metricsData[0].environment;
				}
				$scope.data = ctrl.metricsData.slice((params.page() - 1)* params.count(), params.page() * params.count());
				$scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
				ctrl.totalNoTrans = 0;
				var filterData = $filter('filter')(ctrl.metricsData, params.filter());
				for(var i =0; i< filterData.length; i++){
					ctrl.totalNoTrans = ctrl.totalNoTrans + parseInt(filterData[i].count);
				}
				ctrl.totalNoTrans = ctrl.totalNoTrans;
				
				$defer.resolve( $scope.data);
			}
		});

	}
})();