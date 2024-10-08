package com.nisum.users.infrastructure.inbound.controllers;

import com.nisum.users.application.UserUseCase;
import com.nisum.users.infrastructure.inbound.controllers.dto.UserDTO;
import com.nisum.users.infrastructure.inbound.controllers.mapper.UserDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Usuarios", description = "Gestion de usuarios")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserUseCase userUseCase;
	private final UserDTOMapper userDTOMapper;

	@Autowired
	public UserController(UserDTOMapper userDTOMapper, UserUseCase userUseCase) {
		this.userDTOMapper = userDTOMapper;
		this.userUseCase = userUseCase;
	}

	@Operation(summary = "Registrar usuario", description = "Crea un usuario nuevo. La respuesta es la objeto usuario junto con el id, fecha de creacion, actualizacion, ultima fecha de login, token.")
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity registrarUsuario(@Valid @RequestBody UserDTO UserDTO) {
		return ResponseEntity.ok(userDTOMapper.toDTO(userUseCase.createUser(userDTOMapper.toDomain(UserDTO)).get()));
	}

	@Operation(summary = "Actualizar usuario", description = "Actualiza un usuario. La respuesta es el objeto usuario actualizado.")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity updateUser(@PathVariable("id") UUID id,
									 @Valid @RequestBody UserDTO UserDTO) {
		return ResponseEntity.ok(userDTOMapper.toDTO(userUseCase.updateUser(id, userDTOMapper.toDomain(UserDTO)).get()));
	}

	@Operation(summary = "Buscar usuario por id", description = "Retorna la informacion de un usuario asociado al id enviado.")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity findById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(userDTOMapper.toDTO(userUseCase.findById(id)));
	}

	@Operation(summary = "Eliminar usuario", description = "Elimina un usuario usando su id.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") UUID id) {
		userUseCase.deleteUser(id);
	}

	@Operation(summary = "Listar usuarios", description = "Lista la informacion de los usuarios registrados.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Collection<UserDTO> findAllUsers() {
		return userUseCase.findAllUsers()
				.stream()
				.map(userDTOMapper::toDTO)
				.collect(Collectors.toUnmodifiableList());
	}

}
