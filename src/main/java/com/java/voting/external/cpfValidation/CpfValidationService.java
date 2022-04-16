package com.java.voting.external.cpfValidation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Slf4j
public class CpfValidationService {

    public Boolean validateCpf(String cpf){
        log.info("Validating cpf");
        String url = "https://cpf-api-almfelipe.herokuapp.com/cpf/" + cpf;
        RestTemplate restTemplate = new RestTemplate();

        CpfValidationDTO cpfValidationDTO = Optional.ofNullable(restTemplate.getForObject(url, CpfValidationDTO.class)).orElse(new CpfValidationDTO(cpf, true));

        return cpfValidationDTO.isValid();
    }
}
