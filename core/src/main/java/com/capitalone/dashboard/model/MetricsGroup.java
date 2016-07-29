package com.capitalone.dashboard.model;

public class MetricsGroup {

	private int totalCount;
	private String methodName;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Override
	public String toString() {
		return "MetricsGroup [totalCount=" + totalCount + ", methodName="
				+ methodName + "]";
	}

}
