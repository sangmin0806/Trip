package com.ssafy.trip.model.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.mapper.TripMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class TripServiceImpl implements TripService {

	private final TripMapper tripMapper;

	public TripServiceImpl(TripMapper tripMapper) {
		super();
		this.tripMapper = tripMapper;
	}
	@Override
	public List<TripDto> listTripInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return tripMapper.listTripInfo(map);
	}
	@Override
	public Map<String, Object> locationInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return tripMapper.locationInfo(map);
	}
	@Override
	public int putTripList(Map<String, Object> map) throws Exception {
		tripMapper.insertList(map);
		BigInteger bigIntegerListId = (BigInteger) map.get("listId");
        int listId = bigIntegerListId.intValue();
		map.put("listId", listId);
		System.out.println(map);
		return tripMapper.insertAttractionList(map);
	}

}
