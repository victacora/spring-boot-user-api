package com.nisum.users.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.users.application.UserUseCase;
import com.nisum.users.domain.model.Phone;
import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.inbound.controllers.UserController;
import com.nisum.users.infrastructure.inbound.controllers.dto.PhoneDTO;
import com.nisum.users.infrastructure.inbound.controllers.dto.UserDTO;
import com.nisum.users.infrastructure.inbound.controllers.mapper.UserDTOMapper;
import com.nisum.users.infrastructure.inbound.controllers.security.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserUseCase userUseCase;
	@MockBean
	private UserDTOMapper userDTOMapper;
	@MockBean
	private JwtService jwtService;
	@InjectMocks
	private UserController userController;


	@Test
	void create() throws Exception {
		// Arrange
		String userName = "Victor Puyo";
		String userEmail = "victorpuyo@gmail.cl";
		String userNumber = "3217894512";
		UserDTO request = UserDTO.builder().name(
						userName).email(userEmail).password("1A3b5c5$")
				.phoneList(
				List.of(PhoneDTO.builder().number(userNumber).cityCode("2").countryCode("+57").build())).build();

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXJtYW5wdXlvQHRlc3QuY2wiLCJpYXQiOjE3MjgzOTMxNjQsImV4cCI6MTcyODM5Njc2NH0.AN_MDgcavYQ1ABFkD55OWQSfwE6uEADpMOPvqSyfNo4";
		UserDTO response = UserDTO.builder().name(
						userName).email(userEmail).password("$2a$10$U5M5tQNLUszWRlfGOv3wR.f7M3XsEj6uv/uqRigZ8X8mN.cUEuGh.")
				.token(token)
				.phoneList(
						List.of(PhoneDTO.builder().number(userNumber).cityCode("2").countryCode("+57").build())).build();

		User user = User.builder()
				.name(userName)
				.email(userEmail).password("1A3b5c5$")
				.phoneList(List.of(Phone.builder().number(userNumber).cityCode("2").countryCode("+57").build())).build();

		when(userUseCase.createUser(user)).thenReturn(Optional.of(user));
		when(userDTOMapper.toDomain(request)).thenReturn(user);
		when(userDTOMapper.toDTO(user)).thenReturn(response);

		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.writeValueAsString(request);

		mockMvc.perform(post("/api/v1/users/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(userName))
				.andExpect(jsonPath("$.email").value(userEmail))
				.andExpect(jsonPath("$.token").value(token));
	}

}