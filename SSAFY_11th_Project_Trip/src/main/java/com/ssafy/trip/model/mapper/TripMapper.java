package com.ssafy.trip.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.ssafy.trip.model.TripDto;
@Mapper
public interface TripMapper {
	List<TripDto> listTripInfo(Map<String, Object> map) throws SQLException;
	Map<String, Object> locationInfo(Map<String, Object> map) throws SQLException;
	int insertList(Map<String,Object> map) throws SQLException;
	int insertAttractionList(Map<String,Object> map) throws SQLException;
	
}
