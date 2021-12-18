package com.assignment.Vodafone.Assignment.web.rest;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Vodafone.Assignment.config.AppConstants;
import com.assignment.Vodafone.Assignment.exception.ApiException;
import com.assignment.Vodafone.Assignment.exception.DeviceApiResponse;
import com.assignment.Vodafone.Assignment.service.impl.DeviceDetailServiceImpl;
import com.assignment.Vodafone.Assignment.service.response.DeviceDetailsResponse;
import com.assignment.Vodafone.Assignment.web.rest.dto.UploadRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/iot/event")
public class DeviceResource {

	private static final Logger log = LoggerFactory.getLogger(DeviceResource.class);

	@Autowired
	DeviceDetailServiceImpl deviceDetailService;

	@ApiOperation(value = "Upload batch device details", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "data refreshed"),
			@ApiResponse(code = 404, message = "ERROR: no data file found"),
			@ApiResponse(code = 500, message = "ERROR: A technical exception occurred") })
	@PostMapping("/v1")
	public ResponseEntity<DeviceApiResponse> batchupload(@RequestBody UploadRequest request) {
		log.info("Inside filepath is " + request.getFilepath());
		DeviceApiResponse response;

		try {
			response = deviceDetailService.uploadBatchData(request.getFilepath());
		} catch (FileNotFoundException e) {
			log.error("ERROR: no data file found");
			throw new ApiException(HttpStatus.NOT_FOUND, AppConstants.NO_DATA_FILE_FOUND);
		} catch (Exception exception) {
			log.error("ERROR: A technical exception occurred");
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.TECHNICAL_ERROR);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch device details", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetched"),
			@ApiResponse(code = 400, message = "ERROR: Device could not be located"),
			@ApiResponse(code = 404, message = "ERROR: Id <insert productId> not found"),
			@ApiResponse(code = 500, message = "ERROR: A technical exception occurred"), })
	@GetMapping("/v1")
	public ResponseEntity<DeviceDetailsResponse> fetchDeviceDetails(@RequestParam("ProductId") String productId,
			@RequestParam("tstmp") Optional<Long> timestamp) {
		log.info("fetch device details to product id " + productId);
		return deviceDetailService.getDeviceDetail(productId, timestamp);

	}

}
