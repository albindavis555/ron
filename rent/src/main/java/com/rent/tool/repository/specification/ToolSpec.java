package com.rent.tool.repository.specification;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.rent.tool.entity.ToolEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ToolSpec {
	private static Logger logger = LogManager.getLogger(ToolSpec.class);

	private ToolSpec() {
		
	}
	public static Specification<ToolEntity> getUserSpec(final String searchParam) {
		return new Specification<ToolEntity>() {
			@Override
			public Predicate toPredicate(Root<ToolEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = null;
				JSONParser parser = new JSONParser();
				JSONObject searchObject;
				try {
					searchObject = (JSONObject) parser.parse(searchParam);
					String name = (String) searchObject.get("name");
					String mobileNo = (String) searchObject.get("mobileNo");
					String deliverdDate = (String) searchObject.get("deliverdDate");
					String returnDate = (String) searchObject.get("returnDate");
					String itemName = (String) searchObject.get("itemName");
					String status = (String) searchObject.get("status");

					if (!StringUtils.isEmpty(status)) {
						Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
						finalPredicate = criteriaBuilder.and(statusPredicate);
					}

					if (!StringUtils.isEmpty(mobileNo)) {
						Predicate mobileNoPredicate = criteriaBuilder.like(root.get("mobileNo"), "%"+ mobileNo + "%");
						if(finalPredicate !=null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, mobileNoPredicate);
						}else {
							finalPredicate = criteriaBuilder.and(mobileNoPredicate);
						}
					}
			
									
					if (!StringUtils.isEmpty(deliverdDate)) {
						Predicate deliverdDatePredicate = criteriaBuilder.equal(root.get("deliverdDate"),deliverdDate);
						if(finalPredicate !=null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, deliverdDatePredicate);
						}else {
							finalPredicate = criteriaBuilder.and(deliverdDatePredicate);
						}
					}  
					
					if (!StringUtils.isEmpty(returnDate)) {
						Predicate returnDatePredicate = criteriaBuilder.equal(root.get("returnDate"),returnDate );
						if(finalPredicate !=null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, returnDatePredicate);
						}else {
							finalPredicate = criteriaBuilder.and(returnDatePredicate);
						}
					}
					
					if (!StringUtils.isEmpty(itemName)) {
						Predicate itemNamePredicate = criteriaBuilder.equal(root.get("itemName"),itemName );
						if(finalPredicate !=null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, itemNamePredicate);
						}else {
							finalPredicate = criteriaBuilder.and(itemNamePredicate);
						}
					}
					
					if (!StringUtils.isEmpty(name)) {
						Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get("ToolEmbId").get("name")),"%"+ name.toUpperCase()+"%");
						if(finalPredicate !=null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate);
						}else {
							finalPredicate = criteriaBuilder.and(namePredicate);
						}
					}
		            Order proTimeOrder = criteriaBuilder.asc(root.get("name"));
		            query.orderBy(proTimeOrder);
		            
				} catch (Exception e) {
					logger.error("Error : " + e.getMessage(), e);
//					e.printStackTrace();
				}

				return finalPredicate;
			}
		};
	}
}