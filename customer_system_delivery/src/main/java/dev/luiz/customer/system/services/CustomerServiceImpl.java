package dev.luiz.customer.system.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import dev.luiz.customer.system.constants.RedisConstants;
import dev.luiz.customer.system.dtos.EnderecoDto;
import dev.luiz.customer.system.dtos.GetCustomerResponseDto;
import dev.luiz.customer.system.dtos.OauthResponse;
import dev.luiz.customer.system.dtos.RegisterCustomerRequestDto;
import dev.luiz.customer.system.dtos.CustomerDto;
import dev.luiz.customer.system.interfaces.CustomerService;
import dev.luiz.customer.system.models.Endereco;
import dev.luiz.customer.system.models.Customer;
import dev.luiz.customer.system.repositories.CustomerRepository;
import dev.luiz.customer.system.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository userRepository;
	
	@Override
	public void registerUser(RegisterCustomerRequestDto registerUserRequestDto) {
		
		log.debug("Starting register user.");
		
		Customer user = convertDtoToModel(registerUserRequestDto);
		
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
	@Cacheable(value = RedisConstants.CUSTOMER_CACHE_NAME, key = "#cpf")
	public GetCustomerResponseDto findUserByCpf(String cpf) {
		
		log.debug("Starting find user by cpf.");

		Customer user = userRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User not found with CPF: %s", cpf)));
		
		log.debug("Finishing find user by cpf.");
		
		return convertModelToDto(user);
	}
	
	@Override
	public OauthResponse findCredentials(String cpf) {
		
		return userRepository.findByCpf(cpf).map(OauthResponse::from)
				.orElseThrow(() -> { 
					log.error("User not found for ID {}", cpf);
			return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for ID " + cpf);
			});
	}
	
	private Customer convertDtoToModel(RegisterCustomerRequestDto registerUserRequestDto) {
		
		log.debug("Starting convert User dto to model.");
		
		return Customer.builder()
				.cpf(registerUserRequestDto.getData().getCpf())
				.nome(registerUserRequestDto.getData().getNome())
				.email(registerUserRequestDto.getData().getEmail())
				.senha(EncryptUtils.encode(registerUserRequestDto.getData().getSenha()))
				.endereco(Endereco.builder()
						.rua(registerUserRequestDto.getData().getEnderecoDto().getRua())
						.numero(registerUserRequestDto.getData().getEnderecoDto().getNumero())
						.cidade(registerUserRequestDto.getData().getEnderecoDto().getCidade())
						.estado(registerUserRequestDto.getData().getEnderecoDto().getEstado())
						.cep(registerUserRequestDto.getData().getEnderecoDto().getCep())
						.build()).build();
	}
	
	private GetCustomerResponseDto convertModelToDto(Customer user) {
		
		log.debug("Starting convert User dto to model.");
		
		return GetCustomerResponseDto.builder()
				.data(CustomerDto.builder()
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