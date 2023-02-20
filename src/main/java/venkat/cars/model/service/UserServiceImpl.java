package venkat.cars.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import venkat.cars.model.domain.Role;
import venkat.cars.model.domain.User;
import venkat.cars.model.repository.RoleRepo;
import venkat.cars.model.repository.UserRepo;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private  UserRepo userRepo ;
	@Autowired
	private  RoleRepo roleRepo ;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepo.findByUsername(username);
		if(user == null) 
		{
			throw new UsernameNotFoundException("username not found in database ");
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		user.getRoles().forEach(role ->
		{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}

	@Override
	public User saveUser(User user) {
//		log.info("saving new user {} to the database",user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
//		log.info("saving new role {} to the database",role.getName());
		return roleRepo.save(role);
	}

	
	@Override
	public void addRoletoUser(String username, String roleName) {
		
//		log.info("Adding role {} to user {} ",roleName,username);
		User user =userRepo.findByUsername(username);
		Role role =roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}
	
	

	@Override
	public User getUser(String username) {
//		log.info("Fetching user {} ",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
//		log.info("Fetching users ");
		return userRepo.findAll();
	}

	

}
