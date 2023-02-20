package venkat.cars.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import venkat.cars.model.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
