package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;


import com.ssafy.trip.model.TripDto;

public interface TripService {
	List<TripDto> listTripInfo(Map<String, String> map) throws Exception;
}
