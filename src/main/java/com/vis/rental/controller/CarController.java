package com.vis.rental.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vis.rental.dto.CarResponseDTO;
import com.vis.rental.entity.Car;
import com.vis.rental.repository.CarRespository;
import com.vis.rental.service.CarService;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CarController {

	@Autowired
	CarService carService;
	
	@Autowired
	CarRespository carRespository;
	
	@PostMapping("/add-mul-car")
	public ResponseEntity<List<Car>> AddItemsmul(@RequestBody List<Car> Car) {
		return new ResponseEntity<List<Car>>(carService.addItemsmul(Car), HttpStatus.CREATED);
	}
	
	
	@PostMapping(value = "/add-car", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Car> addItems(
	        @RequestPart("car") Car car,
	        @RequestPart("imageFile") MultipartFile imageFile
	) throws IOException {

	    if (!imageFile.isEmpty()) {
	        String uploadDir = "D:/Car_Rental/CarRental/uploads/";
	        Files.createDirectories(Paths.get(uploadDir));

	        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir, fileName);
	        Files.write(filePath, imageFile.getBytes());

	        car.setImage(fileName);
	    }

	    return new ResponseEntity<>(carService.addItem(car), HttpStatus.CREATED);
	}

	
	
//	@GetMapping("/All-cars")
//	public ResponseEntity<List<Car>> AllCustomerFind(){
//		
//		return new ResponseEntity<List<Car>>(carService.AllItemsFind(),HttpStatus.OK);
//		
//	}
	
	
	@DeleteMapping("/itemsDelete/{itemsId}")
	public ResponseEntity<String> customerDelete(@PathVariable Integer itemsId){
//		logger.info("Request received in controller to delete product ID " +customerId);
		carService.itemsDelete(itemsId);
		return new ResponseEntity<String>("Delete Product ID:"+itemsId,HttpStatus.OK);
	}
	
	@GetMapping("/carSingle/{itemId}")
	public ResponseEntity<Car> singleCar(@PathVariable Integer itemId) {
	    return ResponseEntity.ok(carService.itemFind(itemId));
	}
	
//	@PutMapping("/customerUpdate/{customerid}")
//	public ResponseEntity<Customers> updateProduct(@PathVariable Integer customerid,@RequestBody Customers newValue){
//
//		return new ResponseEntity<Customers>(customerService.customerUpdate(customerid,newValue),HttpStatus.OK);
//	}
	
	@PutMapping(
		    value = "/carUpdate/{carid}",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
		public ResponseEntity<Car> updateCar(
		        @PathVariable Integer carid,
		        @RequestPart("car") Car car,
		        @RequestPart(value = "imageFile", required = false) MultipartFile imageFile
		) throws IOException {

		    Car updatedCar = carService.updateCar(carid, car, imageFile);
		    return ResponseEntity.ok(updatedCar);
		}


	@PutMapping("/carStatus/{id}")
	public ResponseEntity<?> updateStatus(
	        @PathVariable Integer id,
	        @RequestBody Map<String, String> body) {

	    String status = body.get("status");
	    carService.updateStatus(id, status);

	    return ResponseEntity.ok().build();
	}
		
		@GetMapping("/All-cars")
		public List<CarResponseDTO> getAllCars() {

		    List<Car> cars = carRespository.findAll();

		    return cars.stream()
		            .map(car -> {
		                CarResponseDTO dto = new CarResponseDTO();
		                dto.setCarId(car.getCarId());
		                dto.setBrand(car.getBrand());
		                dto.setModel(car.getModel());
		                dto.setStatus(car.getStatus()); // 🔥 IMPORTANT
		                dto.setNumberPlate(car.getNumberPlate());
		                dto.setFuelType(car.getFuelType());
		                dto.setCategory(car.getCategory());
		                dto.setLocation(car.getLocation());
		                dto.setSeatingCapacity(car.getSeatingCapacity());
		                dto.setPricePerDat(car.getPricePerDat());
		                dto.setImage(car.getImage());
		                return dto;
		            })
		            .toList();
		}


		
}
