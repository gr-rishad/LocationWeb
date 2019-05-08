package com.rishad.location.web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rishad.location.web.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query(value="SELECT type,count(type) from location group by type",nativeQuery = true)
	public List<Object[]> findTypeAndTypeCount();

}
