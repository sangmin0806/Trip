package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;


import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.TripListDto;

public interface TripService {
	List<TripDto> listTripInfo(Map<String, Object> map) throws Exception;
	List<TripListDto> getPlanLists(Map<String, Object> map) throws Exception;
	Map<String, Object> locationInfo(Map<String, Object> map) throws Exception;
	int registerPlan(Map<String, Object> map) throws Exception;
	int deletePlan(int id) throws Exception;
	List<TripDto> listTripInfoById(int id) throws Exception;
	
}
