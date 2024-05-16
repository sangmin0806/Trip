package com.ssafy.trip.model.service;

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

}
