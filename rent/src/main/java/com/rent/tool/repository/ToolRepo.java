package com.rent.tool.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.tool.entity.ToolEmbId;
import com.rent.tool.entity.ToolEntity;


@Repository
public interface ToolRepo extends JpaRepository<ToolEntity,ToolEmbId>{

	Page<ToolEntity> findAll(Specification<ToolEntity> spec, Pageable pageable);

	List <ToolEntity>  findAll(Specification<ToolEntity> spec);
	

	

}