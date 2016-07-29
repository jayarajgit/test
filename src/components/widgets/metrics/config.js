/**
 * Usage Metrics Collector widget configuration
 */
(function() {
	'use strict';
	angular.module(HygieiaConfig.module).controller('MetricsConfigController', MetricsConfigController);
	MetricsConfigController.$inject = [ 'modalData', '$modalInstance'];
	function MetricsConfigController(modalData, $modalInstance) {
		var ctrl = this;
		var widgetConfig = modalData.widgetConfig;
		ctrl.envOptions = [{
			name: 'QA1',
			value: 'QA1'
		}, {
			name: 'QA2',
			value: 'QA2'
		},{
			name: 'QA3',
			value: 'QA3'
		},{
			name: 'QA4',
			value: 'QA4'
		}];
		ctrl.submit = submitForm;
		ctrl.submitted = false;
		
		function submitForm(valid, data) {
			ctrl.submitted = true;
			if (valid ) {
				processCollectorItemResponse(data);
			}
		}
		function processCollectorItemResponse(response) {
			var postObj = {
				name : 'metrics',
				componentId : modalData.dashboard.application.components[0].id
			};
			// pass this new config to the modal closing so it's saved
			$modalInstance.close(postObj);
		}
	}
})();
