package venkat.cars.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import venkat.cars.model.model.Cars;
import venkat.cars.model.repository.CarRepository;
import venkat.cars.model.exception.*;


@RestController
@RequestMapping("/api/v1")
public class CarController {
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("/cars")
	public List<Cars> getAllCars(){
		return carRepository.findAll();
	}
	
	//create an cars using rest api
		@PostMapping("/cars")
		public Cars createCars(@RequestBody Cars cars) {
			return carRepository.save(cars);
		}
		
		@GetMapping("/cars/{id}")
		public  ResponseEntity<Cars> getCarsById(@PathVariable Long id) {
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("cars Not Found with that id: "+id));
			return ResponseEntity.ok(cars);
		}
		
		//Update cars RestApi
		@PutMapping("/cars/{id}")
		public  ResponseEntity<Cars> UpdateCar(@PathVariable Long id, @RequestBody Cars carDetails){
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("Employee Not Found with that id: "+id));
			
			cars.setCarname(carDetails.getCarname());
			cars.setCompany(carDetails.getCompany());
			cars.setFueltype(carDetails.getFueltype());
			cars.setMileage(carDetails.getMileage());
			cars.setPrice(carDetails.getPrice());
			
			Cars updatedCars=  carRepository.save(cars);
			return ResponseEntity.ok(updatedCars);
		}
		
		// Delete cars Rest Api
		@DeleteMapping("/cars/{id}")
		public ResponseEntity<Map<String,Boolean>> deleteCar(@PathVariable Long id){
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("cars Not Found with that id: "+id));
			carRepository.delete(cars);
			Map<String,Boolean> response= new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);	
		}

}
