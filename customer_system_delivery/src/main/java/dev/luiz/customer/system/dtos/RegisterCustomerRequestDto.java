package dev.luiz.customer.system.dtos;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterCustomerRequestDto {

	@Valid
	CustomerDto data;
}
