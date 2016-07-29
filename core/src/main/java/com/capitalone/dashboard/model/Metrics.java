package com.capitalone.dashboard.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "metrics")
public class Metrics extends BaseModel {
	private ObjectId collectorItemId;
	private Date date;
	private long count;
	private String serviceName;
	private String methodName;
	private String methodType;
	private String interfaceType;
	private String environment;
	private String client;
	private String status;
	
	private long avgExecutionTime;
	private long minExecutionTime;
	private long maxExecutionTime;

	public ObjectId getCollectorItemId() {
		return collectorItemId;
	}

	public void setCollectorItemId(ObjectId collectorItemId) {
		this.collectorItemId = collectorItemId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAvgExecutionTime() {
		return avgExecutionTime;
	}

	public void setAvgExecutionTime(long avgExecutionTime) {
		this.avgExecutionTime = avgExecutionTime;
	}

	public long getMinExecutionTime() {
		return minExecutionTime;
	}

	public void setMinExecutionTime(long minExecutionTime) {
		this.minExecutionTime = minExecutionTime;
	}

	public long getMaxExecutionTime() {
		return maxExecutionTime;
	}

	public void setMaxExecutionTime(long maxExecutionTime) {
		this.maxExecutionTime = maxExecutionTime;
	}

	@Override
	public String toString() {
		return "Metrics [collectorItemId=" + collectorItemId + ", date=" + date
				+ ", count=" + count + ", serviceName=" + serviceName
				+ ", methodName=" + methodName + ", methodType=" + methodType
				+ ", interfaceType=" + interfaceType + ", environment="
				+ environment + ", client=" + client + ", status=" + status
				+ "]";
	}
}
