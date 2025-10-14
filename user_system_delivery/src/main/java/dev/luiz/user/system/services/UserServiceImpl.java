package dev.luiz.user.system.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import dev.luiz.user.system.dtos.RegisterUserRequestDto;
import dev.luiz.user.system.interfaces.UserService;
import dev.luiz.user.system.models.Endereco;
import dev.luiz.user.system.models.User;
import dev.luiz.user.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	@Override
	public void registerUser(RegisterUserRequestDto registerUserRequestDto) {
		
		log.error("Starting register user.");
		
		User user = convertDtoToModel(registerUserRequestDto);
		
		try {
			
			userRepository.save(user);
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			
			log.error("Error trying to register User. ERRO [{}]", e.getMessage());
		}
		
		log.error("Finishing register user.");
	}
	
	private User convertDtoToModel(RegisterUserRequestDto registerUserRequestDto) {
		
		log.error("Starting convert User dto to model.");
		
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
}