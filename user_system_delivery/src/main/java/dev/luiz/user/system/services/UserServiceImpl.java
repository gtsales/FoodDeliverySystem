package dev.luiz.user.system.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.luiz.user.system.constants.RedisConstants;
import dev.luiz.user.system.dtos.EnderecoDto;
import dev.luiz.user.system.dtos.GetUserResponseDto;
import dev.luiz.user.system.dtos.RegisterUserRequestDto;
import dev.luiz.user.system.dtos.UserDto;
import dev.luiz.user.system.interfaces.UserService;
import dev.luiz.user.system.models.Endereco;
import dev.luiz.user.system.models.User;
import dev.luiz.user.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	@Override
	public void registerUser(RegisterUserRequestDto registerUserRequestDto) {
		
		log.debug("Starting register user.");
		
		User user = convertDtoToModel(registerUserRequestDto);
		
		try {
			
			userRepository.save(user);
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			
			log.error("Error trying to register User. ERRO [{}]", e.getMessage());
		}
		
		log.debug("Finishing register user.");
	}
	
	@Override
	@Transactional
	public void deleteUserByCpf(String cpf) {

		log.debug("Starting delete user.");

		int result = userRepository.deleteByCpf(cpf);

		if (result == 0) {

			throw new EntityNotFoundException("User not found with CPF: " + cpf);
		}

		log.debug("Finishing delete user.");
	}

	@Override
	@Cacheable(value = RedisConstants.USER_CACHE_NAME, key = "#cpf")
	public GetUserResponseDto findUserByCpf(String cpf) {
		
		log.debug("Starting find user by cpf.");

		User user = userRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User not found with CPF: %s", cpf)));
		
		log.debug("Finishing find user by cpf.");
		
		return convertModelToDto(user);
	}
	
	private User convertDtoToModel(RegisterUserRequestDto registerUserRequestDto) {
		
		log.debug("Starting convert User dto to model.");
		
		return User.builder()
				.cpf(registerUserRequestDto.getData().getCpf())
				.nome(registerUserRequestDto.getData().getNome())
				.email(registerUserRequestDto.getData().getEmail())
				.senha(registerUserRequestDto.getData().getSenha())
				.endereco(Endereco.builder()
						.rua(registerUserRequestDto.getData().getEnderecoDto().getRua())
						.numero(registerUserRequestDto.getData().getEnderecoDto().getNumero())
						.cidade(registerUserRequestDto.getData().getEnderecoDto().getCidade())
						.estado(registerUserRequestDto.getData().getEnderecoDto().getEstado())
						.cep(registerUserRequestDto.getData().getEnderecoDto().getCep())
						.build()).build();
	}
	
	private GetUserResponseDto convertModelToDto(User user) {
		
		log.debug("Starting convert User dto to model.");
		
		return GetUserResponseDto.builder()
				.data(UserDto.builder()
						.cpf(user.getCpf())
						.nome(user.getNome())
						.email(user.getEmail())
						.enderecoDto(EnderecoDto.builder()
								.rua(user.getEndereco().getRua())
								.numero(user.getEndereco().getNumero())
								.cidade(user.getEndereco().getCidade())
								.estado(user.getEndereco().getEstado())
								.cep(user.getEndereco().getCep())
								.build()).build()).build();
	}
}