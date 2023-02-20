package venkat.cars.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import venkat.cars.model.model.Cars;

@Repository
public interface CarRepository  extends JpaRepository<Cars, Long>{

}
