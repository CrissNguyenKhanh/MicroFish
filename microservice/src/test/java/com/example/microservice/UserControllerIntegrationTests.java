package com.example.microservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.microservice.entity.User;
import com.example.microservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository.deleteAll();
	}

	@Test
	void createUserReturnsCreatedUser() throws Exception {
		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "Duong",
								  "email": "duong@example.com"
								}
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("Duong"))
				.andExpect(jsonPath("$.email").value("duong@example.com"));
	}

	@Test
	void createUserRejectsInvalidEmail() throws Exception {
		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "Duong",
								  "email": "not-an-email"
								}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("validation failed"))
				.andExpect(jsonPath("$.fieldErrors.email").value("email must be valid"));
	}

	@Test
	void createUserRejectsDuplicateEmail() throws Exception {
		userRepository.save(new User("Existing", "duong@example.com"));

		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "Duong",
								  "email": "duong@example.com"
								}
								"""))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message").value("Email already exists"));
	}

	@Test
	void getUserReturnsUserById() throws Exception {
		User user = userRepository.save(new User("Duong", "duong@example.com"));

		mockMvc.perform(get("/users/{id}", user.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.name").value("Duong"))
				.andExpect(jsonPath("$.email").value("duong@example.com"));
	}

	@Test
	void getUsersReturnsAllUsers() throws Exception {
		userRepository.save(new User("Duong", "duong@example.com"));
		userRepository.save(new User("Lan", "lan@example.com"));

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[1].id").exists());
	}

	@Test
	void updateUserReturnsUpdatedUser() throws Exception {
		User user = userRepository.save(new User("Duong", "duong@example.com"));

		mockMvc.perform(put("/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "Duong Updated",
								  "email": "duong.updated@example.com"
								}
								"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.name").value("Duong Updated"))
				.andExpect(jsonPath("$.email").value("duong.updated@example.com"));
	}

	@Test
	void deleteUserRemovesUser() throws Exception {
		User user = userRepository.save(new User("Duong", "duong@example.com"));

		mockMvc.perform(delete("/users/{id}", user.getId()))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/users/{id}", user.getId()))
				.andExpect(status().isNotFound());
	}
}
