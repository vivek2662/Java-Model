package venkat.cars.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import venkat.cars.model.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role  findByName(String name);

}
