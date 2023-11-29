package com.qp.Assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qp.Assessment.domain.Grocery;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long>{

	List<Grocery> findAll();
	
	Grocery findByItemId(long itemId);
	
	@Query(value = "select * from Grocery where ITEM_QUANTITY!=0",nativeQuery = true)
	List<Grocery> getAvailableGroceries();
}
