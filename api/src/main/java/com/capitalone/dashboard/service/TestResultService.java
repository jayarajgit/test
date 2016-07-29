package com.capitalone.dashboard.service;

import com.capitalone.dashboard.misc.HygieiaException;
import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.model.TestResult;
import com.capitalone.dashboard.request.TestDataCreateRequest;
import com.capitalone.dashboard.request.TestResultRequest;

public interface TestResultService {

    DataResponse<Iterable<TestResult>> search(TestResultRequest request);
    DataResponse<Iterable<TestResult>> historySearch(String componentId, String jobName);
    String create(TestDataCreateRequest request) throws HygieiaException;
}
