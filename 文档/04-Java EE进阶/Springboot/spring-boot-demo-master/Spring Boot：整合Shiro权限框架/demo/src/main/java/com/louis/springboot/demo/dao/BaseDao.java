package com.louis.springboot.demo.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseDao<T, I extends Serializable>
		extends PagingAndSortingRepository<T, I>, JpaSpecificationExecutor<T> {

}