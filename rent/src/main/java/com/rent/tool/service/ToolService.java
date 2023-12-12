package com.rent.tool.service;


import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rent.tool.dto.ServiceResponse;
import com.rent.tool.dto.ToolDto;
import com.rent.tool.entity.ToolEntity;
import com.rent.tool.exception.RecordCreateException;
import com.rent.tool.exception.RecordNotFoundException;

import jakarta.validation.Valid;




@Service
public interface ToolService {
	
	

	JSONObject searchByLimit(String searchParam, int parseInt, int parseInt2);


    ServiceResponse create(@Valid ToolDto dto) throws RecordCreateException;

	
	ResponseEntity<?> update(ToolDto dto);


	List<ToolEntity> getall();

	
	ToolDto getPById(String id, String name) throws RecordNotFoundException;


	ServiceResponse delete(String id, String name) throws RecordNotFoundException;



	

	

	

}