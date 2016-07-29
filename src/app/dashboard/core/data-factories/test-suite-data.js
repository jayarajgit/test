/**
 * Gets test suite related data
 */
(function () {
    'use strict';

    angular
        .module(HygieiaConfig.module + '.core')
        .factory('testSuiteData', testSuiteData)
        .factory('testSuiteHistoryData', testSuiteHistoryData);

    function testSuiteData($http) {
        var testDetailRoute = 'test-data/test_suite_detail.json';
        var caDetailRoute = '/api/quality/test/';

        return {
            details: details
        };

        // search for test suite data
        function details(params) {
            return $http.get(HygieiaConfig.local ? testDetailRoute : caDetailRoute, { params: params })
                .then(function (response) {
                    return response.data;
                });
        }
    }
    function testSuiteHistoryData($http) {
        var caHistoryDetailRoute = '/api/quality/historyTest/';
        return {
            details: details
        };
        // search for test suite data
        function details(params) {
            return $http.get(caHistoryDetailRoute, { params: params })
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();
