package com.telemedine.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.telemedine.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telemedine.models.ERole;
import com.telemedine.models.Role;
import com.telemedine.models.User;
import com.telemedine.payload.request.LoginRequest;
import com.telemedine.payload.request.SignupRequest;
import com.telemedine.payload.response.JwtResponse;
import com.telemedine.payload.response.MessageResponse;
import com.telemedine.repository.UserRepository;
import com.telemedine.security.jwt.JwtUtils;
import com.telemedine.security.services.AuthService;
import com.telemedine.security.services.MailService;
import com.telemedine.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	com.telemedine.repository.VerificationTokenRepository verificationTokenRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	  MailService mailService;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AuthService authservice;

	private final Logger log = LoggerFactory.getLogger(AuthController.class);


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User user = userRepository.findByEmail(loginRequest.getEmail()).get();
		if(user.isEnabled())
		{
		log.info("user object: {}",user);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		log.info("roles : {}",roles);

		List<Role> roles1 = userRepository.findByEmail(loginRequest.getEmail()).get().getRoles();
		JwtResponse response = new JwtResponse(jwt,userDetails.getId(), userDetails.getFullname(), userDetails.getUsername(), userDetails.getPhone(),userDetails.getPhoto(),roles1);
		/*if(roles1.get(0).getName().equals(ERole.ROLE_MEDECIN))
			response.setBoutique(user.get);*/
                
		return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Veuillez verifier votre adressse mail pour valide votre compte!!");
	}
	 @PutMapping("/customer/{id}")
	  public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @Valid @RequestBody SignupRequest signUpRequest) {
		 

	    System.out.println("Update User with ID = " + id + "...");
	 
	    Optional<User> userData = userRepository.findById(id);
	 
	    if (userData.isPresent()) {
	       User _user = userData.get();
	      _user .setFullname(signUpRequest.getFullname());
	      _user .setEmail(signUpRequest.getEmail());
	      _user .setPhone(signUpRequest.getPhone());
	      _user .setPassword(encoder.encode(signUpRequest.getPassword()));
	     
	      return new ResponseEntity<>(userRepository.save(_user ), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		log.info("Registering user with infos : {}",signUpRequest);

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByPhone(signUpRequest.getPhone())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: phone number is already in use!"));
		}

		authservice.signup(signUpRequest);
       
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> roles() {

		log.info("requesting all roles...");

		List<Role> roles = roleRepository.findAll();

		log.info("Roles : {}",roles);
		return ResponseEntity.ok(roles);
	}
	 @GetMapping("accountVerification/{token}")
	    public ResponseEntity<?> verifyAccount(@PathVariable String token) {
	    	System.out.println("one vrification methode");
	        authservice.verifyAccount(token);
	        return ResponseEntity.ok(new MessageResponse("Activated successfully!"));
	}

	
}
