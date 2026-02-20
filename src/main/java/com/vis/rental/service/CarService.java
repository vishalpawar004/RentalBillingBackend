package com.vis.rental.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vis.rental.dto.CarResponseDTO;
import com.vis.rental.entity.Car;
import com.vis.rental.exceptionHandling.CustomersNotFoundException;
import com.vis.rental.repository.CarRespository;
import com.vis.rental.repository.RentalRepository;




@Service
public class CarService {
	
	@Autowired
	CarRespository carRespository;
	
	@Autowired
	RentalRepository rentalRepository;

	private static final String UPLOAD_DIR =
            "D:/Car_Rental/CarRental/uploads/";
	public List<Car> addItemsmul(List<Car> car) {
		
		
		return carRespository.saveAll(car) ;
		
	}
	
	public @Nullable List<Car> AllItemsFind() {
		
		return carRespository.findAll();
	}

	public @Nullable Car addItem(Car car) {
		
		return carRespository.save(car);
	}



	public void itemsDelete(Integer itemsId) {
		if (! carRespository.existsById(itemsId)) {
			throw new CustomersNotFoundException("customer with ID " + itemsId + " does not exist in Database");
		}
		 carRespository.deleteById(itemsId);
		
	}

	public Car itemFind(Integer itemId) {
		Optional<Car> optionalitems = carRespository.findById(itemId);
		Car car = null;
		if(optionalitems.isPresent()) {
			car = optionalitems.get();
			return car;
		}
		throw new RuntimeException("Items is not Present in stock");
		
	}
	

	public Car updateCar(
	        Integer carid,
	        Car updatedCar,
	        MultipartFile imageFile
	) throws IOException {

	    Car car = carRespository.findById(carid)
	            .orElseThrow(() -> new RuntimeException("Car not found"));

	    // 🔹 Update fields
	    car.setBrand(updatedCar.getBrand());
	    car.setModel(updatedCar.getModel());
	    car.setNumberPlate(updatedCar.getNumberPlate());
	    car.setSeatingCapacity(updatedCar.getSeatingCapacity());
	    car.setCategory(updatedCar.getCategory());
	    car.setLocation(updatedCar.getLocation());
	    car.setDescription(updatedCar.getDescription());
	    car.setFuelType(updatedCar.getFuelType());
	    car.setPricePerDat(updatedCar.getPricePerDat()); // ✅ FIXED
	    car.setStatus(updatedCar.getStatus());

	    // 🔥 Update image only if new image is sent
	    if (imageFile != null && !imageFile.isEmpty()) {

	        Files.createDirectories(Paths.get(UPLOAD_DIR));

	        // delete old image
	        if (car.getImage() != null && !car.getImage().isEmpty()) {
	            Files.deleteIfExists(
	                Paths.get(UPLOAD_DIR + car.getImage())
	            );
	        }

	        String fileName =
	            System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

	        Path path = Paths.get(UPLOAD_DIR + fileName);
	        Files.write(path, imageFile.getBytes());

	        car.setImage(fileName);
	    }

	    return carRespository.save(car);
	}


	
	
	public List<CarResponseDTO> getAllCars() {

	    List<Car> cars = carRespository.findAll();

	    return cars.stream().map(car -> {

	        CarResponseDTO dto = new CarResponseDTO();
	        dto.setCarId(car.getCarId());
	        dto.setBrand(car.getBrand());
	        dto.setModel(car.getModel());
	        dto.setNumberPlate(car.getNumberPlate());
	        dto.setSeatingCapacity(car.getSeatingCapacity());
	        dto.setFuelType(car.getFuelType());
	        dto.setCategory(car.getCategory());
	        dto.setLocation(car.getLocation());
	        dto.setPricePerDat(car.getPricePerDat());
	        dto.setImage(car.getImage());

	        // ✅ USE REAL DB STATUS
	        dto.setStatus(car.getStatus());

	        return dto;
	    }).toList();
	}


	 public void updateStatus(Integer id, String status) {
		    Car car = carRespository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Car not found"));

		    car.setStatus(status);
		    carRespository.save(car);
		}
	

}
