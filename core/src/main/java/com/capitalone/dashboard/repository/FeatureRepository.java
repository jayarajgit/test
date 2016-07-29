/*************************DA-BOARD-LICENSE-START*********************************
 * Copyright 2014 CapitalOne, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************DA-BOARD-LICENSE-END*********************************/

package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Feature;

/**
 * Repository for {@link FeatureCollector}.
 */
public interface FeatureRepository extends CrudRepository<Feature, ObjectId>,
		QueryDslPredicateExecutor<Feature> {
	/**
	 * This essentially returns the max change date from the collection, based
	 * on the last change date (or default delta change date property) available
	 * 
	 * @param collectorId
	 *            Collector ID of source system collector
	 * @param changeDate
	 *            Last available change date or delta begin date property
	 * @return A single Change Date value that is the maximum value of the
	 *         existing collection
	 */
	// 1 not called in feature service impl in api
	@Query
	List<Feature> findTopByCollectorIdAndChangeDateGreaterThanOrderByChangeDateDesc(
			ObjectId collectorId, String changeDate);

	// 2 not called in feature service impl in api
	@Query(value = "{'sId' : ?0}", fields = "{'sId' : 1}")
	List<Feature> getFeatureIdById(String sId);
	
	//3 used in  getRelevantStories in service impl
	@Query(value = "{'sTeamID' : ?0 , 'isDeleted' : 'False', $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}")
	List<Feature> queryByOrderBySStatusDesc(String sTeamID, String currentISODateTime);
	
	@Query(value = "{ 'isDeleted' : 'False','sSprintAssetState':'ACTIVE', $and : [{'sSprintID' : {$ne : null}} ]}")
	List<Feature> queryByOrderBySStatusDescUpdate(String sTeamID, String currentISODateTime);
	
	//4 used in getCurrentSprintDetail in impl
	@Query(value = "{'sTeamID' : ?0 , 'isDeleted' : 'False', $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}, $orderby: { 'sStatus' :-1 }", fields = "{'sSprintID' : 1, 'sSprintName' : 1,'sSprintBeginDate' : 1, 'sSprintEndDate' : 1}")
	List<Feature> getCurrentSprintDetail(String sTeamID, String currentISODateTime);
	// need to check by adding order and fields
	@Query(value = "{ 'isDeleted' : 'False','sSprintAssetState':'ACTIVE', $and : [{'sSprintID' : {$ne : null}} ]}")
	List<Feature> getCurrentSprintDetailUpdate(String sTeamID, String currentISODateTime);
	
	//5 in progess , used in getFeatureEstimates
	@Query(value = " {'sTeamID' : ?0 , 'isDeleted' : 'False', $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}, $orderby: { 'sEpicID' :-1 }", fields = "{'sEpicID' : 1,'sEpicNumber' : 1,'sEpicName' : 1,'sEstimate' : 1}")
	List<Feature> getInProgressFeaturesEstimatesByTeamId(String sTeamID, String currentISODateTime);
	
	@Query(value = " { 'isDeleted' : 'False', $and : [{'sSprintID' : {$ne : null}} ]}")
	List<Feature> getInProgressFeaturesEstimatesByTeamIdUpdate(String sTeamID, String currentISODateTime);

	//6 total , 
	@Query(value = " {'sTeamID' : ?0 , 'isDeleted' : 'False', $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}, $orderby: { 'sStatus' :-1 }", fields = "{'sStatus': 1,'sEstimate' : 1}")
	List<Feature> getSprintBacklogTotal(String sTeamID, String currentISODateTime);

	@Query(value = "{'isDeleted' : 'False','sSprintAssetState':'ACTIVE', $and : [{'sSprintID' : {$ne : null}} ]}")
	List<Feature> getSprintBacklogTotalUpdate(String sTeamID, String currentISODateTime);

	// 7 wip,
	@Query(value = " {'sTeamID' : ?0 , $and : [{'isDeleted' : 'False'} , {'sState' : 'Active'}] , $or : [{'sStatus' : 'In Progress'} , {'sStatus' : 'Waiting'} , {'sStatus' : 'Impeded'}] , $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}, $orderby: { 'sStatus' :-1 }", fields = "{'sStatus': 1,'sEstimate' : 1}")
	List<Feature> getSprintBacklogInProgress(String sTeamID, String currentISODateTime);

	@Query(value = " {'sSprintAssetState':'ACTIVE' , $or : [{'sStatus' : 'In Progress'} , {'sStatus' : 'Waiting'} , {'sStatus' : 'Impeded'}, {'sStatus' : 'New'}] , $and : [{'sSprintID' : {$ne : null}}]}", fields = "{'sStatus': 1,'sEstimate' : 1}")
	List<Feature> getSprintBacklogInProgressUpdate(String sTeamID, String currentISODateTime);

	//8 done,
	@Query(value = " {'sTeamID' : ?0 , 'isDeleted' : 'False' , $or : [{'sStatus' : 'Done'} , {'sStatus' : 'Accepted'}] , $and : [{'sSprintID' : {$ne : null}} , {'sSprintBeginDate' : {$lte : ?1}} , {'sSprintEndDate' : {$gte : ?1}}]}, $orderby: { 'sStatus' :-1 }", fields = "{'sStatus': 1,'sEstimate' : 1}")
	List<Feature> getSprintBacklogDone(String sTeamID, String currentISODateTime);

	@Query(value = "{ 'sSprintAssetState' : 'ACTIVE' , 'isDeleted' : 'False' , $or : [{'sStatus' : 'Complete'}, {'sStatus' : 'Done'} , {'sStatus' : 'Accepted'}] , $and : [{'sSprintID' : {$ne : null} }]} ", fields = "{'sStatus': 1,'sEstimate' : 1}")
	List<Feature> getSprintBacklogDoneUpdate(String sTeamID, String currentISODateTime);
	// not in service impl
	@Query(value = " {'sNumber' : ?0 }")
	List<Feature> getStoryByNumber(String sNumber);
}