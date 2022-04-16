package com.java.voting.associate;

import lombok.Builder;

public record AssociateDTO(Long idAssociate, String name, String cpf) {

    @Builder
    public AssociateDTO {
        //Construtor vazio para utilizar o builder do Lombok em um record
    }

    public static AssociateDTO createAssociateDTO(Associate associate){
        return AssociateDTO.builder()
                .idAssociate(associate.getIdAssociate())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .build();
    }
}
