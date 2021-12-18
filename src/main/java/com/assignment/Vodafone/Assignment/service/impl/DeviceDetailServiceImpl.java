package com.assignment.Vodafone.Assignment.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.Vodafone.Assignment.config.AppConstants;
import com.assignment.Vodafone.Assignment.entity.DeviceData;
import com.assignment.Vodafone.Assignment.entity.DeviceEntity;
import com.assignment.Vodafone.Assignment.exception.ApiException;
import com.assignment.Vodafone.Assignment.exception.DeviceApiResponse;
import com.assignment.Vodafone.Assignment.repository.DeviceDetailsRepository;
import com.assignment.Vodafone.Assignment.service.DeviceDetailService;
import com.assignment.Vodafone.Assignment.service.response.DeviceDetailsResponse;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class DeviceDetailServiceImpl implements DeviceDetailService {

	@Autowired
	DeviceDetailsRepository deviceDetailsRepository;

	private static final Logger log = LoggerFactory.getLogger(DeviceDetailServiceImpl.class);

	@Override
	public DeviceApiResponse uploadBatchData(String fileName) throws IOException, CsvException {
		// Parse CSV file to POJO. Skipping first 2 lines from CSV file, considering the
		// uploaded data will start from line 3
		List<DeviceData> beans = new CsvToBeanBuilder<DeviceData>(new FileReader(fileName)).withSkipLines(2)
				.withType(DeviceData.class).build().parse();
		// delete existing data before uploading the new data
		deviceDetailsRepository.deleteAll();
		// Uploading data to DB
		deviceDetailsRepository.saveAll(toEntities(beans));
		return new DeviceApiResponse(AppConstants.DATA_REFRESHED);

	}

	@Override
	public ResponseEntity<DeviceDetailsResponse> getDeviceDetail(String productId, Optional<Long> timestamp) {
		DeviceDetailsResponse deviceDetailsResponse = new DeviceDetailsResponse();
		// validating the product id
		if (Objects.isNull(productId) || productId.isEmpty()) {
			log.error("Invalid product id");
			throw new ApiException(HttpStatus.NOT_FOUND, AppConstants.INVALID_PRODUCT_ID);
		}
		// Fetching device based on product Id
		List<DeviceEntity> data = deviceDetailsRepository.findDeviceDetails(productId);
		if (Objects.isNull(data) || data.isEmpty()) {
			log.error("Device details not found for:{}", productId);
			throw new ApiException(HttpStatus.NOT_FOUND, "ERROR: Id " + productId + " not found");
		}
		// Enriching the API response
		ResponseEntity<DeviceDetailsResponse> response = enrichDeviceDetailsResponse(deviceDetailsResponse, data,
				timestamp, productId);

		return response;

	}

	private ResponseEntity<DeviceDetailsResponse> enrichDeviceDetailsResponse(
			DeviceDetailsResponse deviceDetailsResponse, List<DeviceEntity> data, Optional<Long> timestamp,
			String productId) {

		Timestamp defaultDate;
		// If TimeStamp is not present, setting it to default today's TimeStamp
		if (timestamp.isPresent()) {
			defaultDate = new Timestamp(timestamp.get());
		} else {
			defaultDate = Timestamp.from(Instant.now());
		}
		// Filtering time based on input tstamp in ascending order
		List<DeviceEntity> deviceEntityList = data.stream().filter(e -> {
			if (e.getDateTime().compareTo(defaultDate) == 0 || e.getDateTime().compareTo(defaultDate) == -1)
				return true;
			else
				return false;

		}).collect(Collectors.toList());
		if (Objects.isNull(deviceEntityList) || deviceEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, AppConstants.DEVICE_COULD_NOT_BE_LOCATED);
		}
		// getting the most accurate device for given TimeStamp
		DeviceEntity deviceEntity = deviceEntityList.get(deviceEntityList.size() - 1);
		deviceDetailsResponse.setId(deviceEntity.getProductId());
		deviceDetailsResponse.setName(AppConstants.WG.equalsIgnoreCase(deviceEntity.getProductId().substring(0, 2))
				? AppConstants.CYCLE_PLUS_TRACKER
				: AppConstants.GENERAL_TRACKER);
		//private static  SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		deviceDetailsResponse.setDatetime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(deviceEntity.getDateTime()).toString());
		setGpsData(deviceDetailsResponse, deviceEntity);
		setBattery(deviceDetailsResponse, deviceEntity);
		if (productId.startsWith(AppConstants.WG)) {
			checkStatus(deviceDetailsResponse, deviceEntityList);
		}
		log.info("Device response is:{}", deviceDetailsResponse);
		return new ResponseEntity<DeviceDetailsResponse>(deviceDetailsResponse, HttpStatus.OK);
	}

	private void checkStatus(DeviceDetailsResponse deviceDetailsResponse, List<DeviceEntity> deviceEntityList) {
		Boolean isEqualCordinates = false;
		if (deviceEntityList.size() >= 3) {

			List<DeviceEntity> lastThreedevices = deviceEntityList.subList(deviceEntityList.size() - 3,
					deviceEntityList.size());
			String latitude = deviceEntityList.get(0).getLatitude();
			String longtitude = deviceEntityList.get(0).getLongitude();
			for (DeviceEntity device : lastThreedevices) {
				if (device.getLatitude().equals(latitude) && device.getLongitude().equals(longtitude))
					isEqualCordinates = true;
				else
					isEqualCordinates = false;
			}

		}

		if (isEqualCordinates) {
			deviceDetailsResponse.setStatus(AppConstants.INACTIVE);
		} else {
			deviceDetailsResponse.setStatus(AppConstants.NA);

		}

	}

	private void setBattery(DeviceDetailsResponse deviceDetailsResponse, DeviceEntity deviceEntity) {
		float f = new Float(deviceEntity.getBattery());
		if (f >= .98) {
			deviceDetailsResponse.setBattery(AppConstants.FULL);
		} else if (f < .98 && f >= .60) {
			deviceDetailsResponse.setBattery(AppConstants.HIGH);
		} else if (f < .60 && f >= .40) {
			deviceDetailsResponse.setBattery(AppConstants.MEDIUM);
		} else if (f < .40 && f >= .10) {
			deviceDetailsResponse.setBattery(AppConstants.LOW);
		} else
			deviceDetailsResponse.setBattery(AppConstants.CRITICAL);

	}

	private void setGpsData(DeviceDetailsResponse deviceDetailsResponse, DeviceEntity deviceEntity) {
		if (AppConstants.ON.equalsIgnoreCase(deviceEntity.getAirplaneMode())) {
			deviceDetailsResponse.setStatus(AppConstants.INACTIVE);
			deviceDetailsResponse.setLat(AppConstants.EMPTY);
			deviceDetailsResponse.setLongt(AppConstants.EMPTY);
			deviceDetailsResponse.setDescription(AppConstants.LOCATION_NOT_AVAILABLE);

		} else {
			if (deviceEntity.getLatitude().isEmpty() && deviceEntity.getLongitude().isEmpty())
				deviceDetailsResponse.setStatus(AppConstants.INACTIVE);
			else
				deviceDetailsResponse.setStatus(AppConstants.ACTIVE);
			deviceDetailsResponse.setLat(deviceEntity.getLatitude());
			deviceDetailsResponse.setLongt(deviceEntity.getLongitude());
			deviceDetailsResponse.setDescription(AppConstants.LOCATION_AVAILABLE);
		}
	}

	private List<DeviceEntity> toEntities(List<DeviceData> list) {
		List<DeviceEntity> element = new ArrayList<>();
		for (DeviceData dto : list) {
			DeviceEntity entity = new DeviceEntity();
			entity.setAirplaneMode(dto.getAirplaneMode());
			entity.setBattery(dto.getBattery());
			entity.setDateTime(new Timestamp(dto.getDateTime()));
			entity.setEventId(dto.getEventId());
			entity.setLatitude(dto.getLatitude());
			entity.setLight(dto.getLight());
			entity.setLongitude(dto.getLongitude());
			entity.setProductId(dto.getProductId());
			element.add(entity);
		}
		return element;
	}

}
