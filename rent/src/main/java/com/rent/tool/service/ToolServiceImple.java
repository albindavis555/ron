package com.rent.tool.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rent.tool.dto.ServiceResponse;
import com.rent.tool.dto.ToolDto;
import com.rent.tool.entity.ToolEmbId;
import com.rent.tool.entity.ToolEntity;
import com.rent.tool.exception.RecordCreateException;
import com.rent.tool.exception.RecordNotFoundException;
import com.rent.tool.repository.ToolRepo;
import com.rent.tool.repository.specification.ToolSpec;
import com.rent.tool.util.Constants;

import jakarta.validation.Valid;


@Service
public class ToolServiceImple implements ToolService  {
	
	
	private static Logger logger = LogManager.getLogger(ToolService.class);

	@Autowired
	ToolRepo repo;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public List<ToolEntity> getall() {
		return repo.findAll();
		
	}
	
	
	@Override
	public ServiceResponse create(@Valid ToolDto dto) throws RecordCreateException  {

		try {
			ToolEntity entity = new ToolEntity();
			ToolEmbId embid = new ToolEmbId();
			
			String randomId = generateRandom4DigitId();
			
			embid.setId(randomId);
			embid.setName(dto.getName());
			
			Optional<ToolEntity> entity1 = repo.findById(embid);

			if (entity1.isPresent()) {
				throw new RecordCreateException("card.credit.details.psh.VAL0001");
			} else {
				entity.setToolEmbId(embid);

				entity.setMobileNo(dto.getMobileNo());
				entity.setAddress(dto.getAddress());
    			entity.setItemName(dto.getItemName());
				entity.setQuantity(dto.getQuantity());
				entity.setAmount(dto.getAmount());
				entity.setDeliverdDate(dto.getDeliverdDate());
				entity.setReturnDate(dto.getReturnDate());
				entity.setNn(dto.getNn());
				entity.setStatus(Constants.PROCESSD);
			
				repo.save(entity);
				return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS,
						messageSource.getMessage("emp.details.psh.VAL0003", null, LocaleContextHolder.getLocale()), null);
			}
			} catch (RecordCreateException e) {
				logger.error("Error:" + e.getMessage(), e);
				return new ServiceResponse("emp.details.psh.VAL0004",
						messageSource.getMessage("emp.details.psh.VAL0004", null, LocaleContextHolder.getLocale()), null);
			} catch (Exception e) {
				logger.error("Error:" + e.getMessage(), e);
				return new ServiceResponse("emp.details.psh.VAL0004",
						messageSource.getMessage("emp.details.psh.VAL0004", null, LocaleContextHolder.getLocale()), null);
			}
		}	
	private String generateRandom4DigitId() {
	    // Your logic to generate a random 4-digit ID
	    return String.valueOf(1000 + new Random().nextInt(9000));
	}
	@Override
	public ResponseEntity<?> update(ToolDto dto) {
		ToolEmbId embid = new ToolEmbId();
	
	    embid.setId(dto.getId());
	    embid.setName(dto.getName());
//	    Optional<ToolEntity> tem = repo.findById(embid);
	    
	    ToolEntity m = repo.findById(embid).orElse(null);

	    if (m == null) {
	        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
	    }

	    if (!Constants.PROCESSD.equals(m.getStatus())) {
	        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
	    }
	    	if (dto.getId() != null) {
	            embid.setId(dto.getId());
	        }	
	    	if (dto.getName() != null) {
	            embid.setName(dto.getName());
	        }	
	        if (dto.getAddress() != null) {
	            m.setAddress(dto.getAddress());
	        }
	        if (dto.getMobileNo() != null) {
	            m.setMobileNo(dto.getMobileNo());
	        }
	        if (dto.getItemName() !=null) {
	            m.setItemName(dto.getItemName());
	        }
	        if( dto.getQuantity() !=null) {
	        	m.setQuantity(dto.getQuantity());
	        }
	        if( dto.getAmount() !=null) {
	        	m.setAmount(dto.getAmount());
	        }
	        if(dto.getDeliverdDate()!=null) {
	        	m.setDeliverdDate(dto.getDeliverdDate());
	        }
	        if(dto.getReturnDate()!=null) {
	        	m.setReturnDate(dto.getReturnDate());
	        }
	        if(dto.getStatus()!=null) {
	        	m.setStatus(dto.getStatus());
	        }
	        if(dto.getNn()!=null) {
	        	m.setNn(dto.getNn());
	        }
	        
	        repo.save(m);
	        
			return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
	    } 
	

	
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject searchByLimit(String searchParam, int start, int pageSize) {
		JSONObject result = new JSONObject();
		try {
			Pageable pageable = PageRequest.of(start / pageSize, pageSize);
			Specification<ToolEntity> spec = ToolSpec.getUserSpec(searchParam);
			Page<ToolEntity> usersList = repo.findAll(spec, pageable);
			JSONArray array = new JSONArray();
			JSONArray countByType = countByType(spec);
			for (ToolEntity users : usersList) {
				JSONObject obj = new JSONObject();
				obj.put("id", users.getToolEmbId().getId());
				obj.put("name", users.getToolEmbId().getName());
				obj.put("address", users.getAddress());
				obj.put("mobileNo", users.getMobileNo());
				obj.put("itemName", users.getItemName());
				obj.put("quantity", users.getQuantity());
				obj.put("amount", users.getAmount());
				obj.put("deliverdDate", users.getDeliverdDate());
				obj.put("returnDate", users.getReturnDate());
				obj.put("status", users.getStatus());
				obj.put("nn", users.getNn());

				
				array.add(obj);
			}
			result.put("aaData", array);
			result.put("iTotalDisplayRecords", repo.findAll(spec).size());
			result.put("iTotalRecords", repo.findAll(spec).size());
			result.put("countByType", countByType);
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage(), e);
		}
		return result;
}

	private JSONArray countByType(Specification<ToolEntity> spec) {
		JSONArray array= new JSONArray();
		try {
			List<ToolEntity> headerList = repo.findAll(spec);
			Map<String, Long> countByType = headerList.stream().collect(Collectors.groupingBy(ToolEntity::getStatus,Collectors.counting()));
			for(String status : countByType.keySet()) {
				JSONObject obj=new JSONObject();
				obj.put("name", status);
				obj.put("count", countByType.get(status));
				array.add(obj);
			}
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage(), e);
		}
		return array;
	}

	@Override
	public ToolDto getPById(String id, String name)throws RecordNotFoundException {
			
			ToolEmbId embid =  new ToolEmbId();
			embid.setId(id);
			embid.setName(name);
			
						
			Optional<ToolEntity> findByKey = repo.findById(embid);
			System.out.println(findByKey);
			if(!findByKey.isPresent()) {
				throw new RecordNotFoundException("can't find specified record.");
			}else {
			  try {
				ToolEntity entity = findByKey.get();
				ToolDto dto = new ToolDto();
				dto.setId(id);
				dto.setName(name);
				dto.setAddress(entity.getAddress());
				dto.setMobileNo(entity.getMobileNo());
				dto.setItemName(entity.getItemName());
				dto.setQuantity(entity.getQuantity());
				dto.setAmount(entity.getAmount());
				dto.setDeliverdDate(entity.getDeliverdDate());
				dto.setReturnDate(entity.getReturnDate());
				dto.setStatus(entity.getStatus());
				dto.setNn(entity.getNn());
				
				return dto;
			
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		 }
			
	}

	@Override
	public ServiceResponse delete(String id, String name) throws RecordNotFoundException {
		try {
			ToolEmbId embid= new ToolEmbId();			
			embid.setId(id);
			embid.setName(name);
	    	Optional<ToolEntity> findByKey = repo.findById(embid);
			if (!findByKey.isPresent()) {
				throw new RecordNotFoundException("parking.details.psh.VAL0014");
			} else {
				ToolEntity entity = findByKey.get();
				
				if (entity.getStatus().contentEquals(Constants.MESSAGE_STATUS.RETURNED)) {
					return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED, messageSource
							.getMessage("parking.details.psh.VAL0015", null, LocaleContextHolder.getLocale()), null);
				}
				
				entity.setStatus(Constants.MESSAGE_STATUS.RETURNED);
				
				repo.save(entity);
			}
			return new ServiceResponse(Constants.MESSAGE_STATUS.SUCCESS,
					messageSource.getMessage("parking.details.psh.VAL0016", null, LocaleContextHolder.getLocale()), null);
		} catch (RecordNotFoundException e) {
			return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED,
					messageSource.getMessage("parking.details.psh.VAL0017", null, LocaleContextHolder.getLocale()), null);
		} catch (Exception e) {
			return new ServiceResponse(Constants.MESSAGE_STATUS.FAILED,
					messageSource.getMessage("parking.details.psh.VAL0018", null, LocaleContextHolder.getLocale()), null);
		}
	}
	
	


	
}