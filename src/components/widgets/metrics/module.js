(function () {
    'use strict';

    var widget_state,
        config = {
        view: {
            defaults: {
                title: 'Usage Metrics' // widget title
            },
            controller: 'MetricsViewController',
            controllerAs: 'metricsView',
            templateUrl: 'components/widgets/metrics/view.html'
        },
        config: {
            controller: 'MetricsConfigController',
            controllerAs: 'metricsConfig',
            templateUrl: 'components/widgets/metrics/config.html'
        },
        getState: getState
    };

    angular
        .module(HygieiaConfig.module)
        .config(register);

    register.$inject = ['widgetManagerProvider', 'WidgetState'];
    function register(widgetManagerProvider, WidgetState) {
        widget_state = WidgetState;
        widgetManagerProvider.register('metrics', config);
    }

    function getState(widgetConfig) {
        return HygieiaConfig.local || (widgetConfig.id) ? widget_state.READY : widget_state.CONFIGURE;
    }
})();
