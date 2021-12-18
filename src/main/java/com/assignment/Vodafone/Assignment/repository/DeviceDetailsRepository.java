package com.assignment.Vodafone.Assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Vodafone.Assignment.entity.DeviceEntity;

@Repository
public interface DeviceDetailsRepository extends CrudRepository<DeviceEntity, String> {

	@Query(value = "SELECT * FROM DEVICE_ENTITY where PRODUCT_ID =?1 order by date_time asc", nativeQuery = true)
	List<DeviceEntity> findDeviceDetails(String PRODUCT_ID);

}
