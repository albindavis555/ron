package com.rent.tool.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rent.tool.dto.ServiceResponse;
import com.rent.tool.dto.ToolDto;
import com.rent.tool.entity.ToolEntity;
import com.rent.tool.exception.RecordCreateException;
import com.rent.tool.exception.RecordNotFoundException;
import com.rent.tool.service.ToolService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/rent")
public class ToolController {
	
	@Autowired
	ToolService ser;

	
	@GetMapping("/get")
	public ResponseEntity<List<ToolEntity>> get(){
//		System.out.println("abc");
		List<ToolEntity> result=ser.getall();
		return new ResponseEntity<>(result,HttpStatus.OK);
	} 
		
	@PostMapping("/create")
	public ResponseEntity<ServiceResponse> create(@Valid @RequestBody ToolDto dto) throws RecordCreateException{
		return new ResponseEntity<>(ser.create(dto), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody ToolDto dto){
		return new ResponseEntity<>(ser.update(dto),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}/{name}") 
	public ResponseEntity<Object> getGuest(@PathVariable("id") String id, @PathVariable ("name") String name)throws RecordNotFoundException{
			return new ResponseEntity<>(ser.getPById(id,name),new HttpHeaders(),HttpStatus.ACCEPTED);
	}
	
		
	@GetMapping("/search")
	public ResponseEntity<JSONObject> searchByPage(@RequestParam("searchParam") String searchParam,
			@RequestParam("iDisplayStart") String iDisplayStart,
			@RequestParam("iDisplayLength") String iDisplayLength) {
		JSONObject list = ser.searchByLimit(searchParam, Integer.parseInt(iDisplayStart),
				Integer.parseInt(iDisplayLength));

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}/{name}")
	public ResponseEntity<ServiceResponse>delete1(@PathVariable ("id") String id,@PathVariable ("name") String name) throws RecordNotFoundException{
		return new ResponseEntity<>(ser.delete(id, name),new HttpHeaders(),HttpStatus.OK);
	}
	


}
