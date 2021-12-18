package com.assignment.Vodafone.Assignment.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.assignment.Vodafone.Assignment.exception.DeviceApiResponse;
import com.assignment.Vodafone.Assignment.service.response.DeviceDetailsResponse;
import com.opencsv.exceptions.CsvException;

public interface DeviceDetailService {
	public ResponseEntity<DeviceDetailsResponse> getDeviceDetail(String productId, Optional<Long> timestamp);

	public DeviceApiResponse uploadBatchData(String fileName) throws IOException, CsvException;

}
