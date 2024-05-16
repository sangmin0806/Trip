package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;


import com.ssafy.trip.model.TripDto;

public interface TripService {
	List<TripDto> listTripInfo(Map<String, Object> map) throws Exception;
	Map<String, Object> locationInfo(Map<String, Object> map) throws Exception;
}
