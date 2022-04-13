package com.java.voting.associate;

import lombok.Builder;

public record AssociateDTO(Long idAssociate, String name) {

    @Builder
    public AssociateDTO {}

    public static AssociateDTO createAssociateDTO(Associate associate){
        return AssociateDTO.builder()
                .idAssociate(associate.getIdAssociate())
                .name(associate.getName())
                .build();
    }
}
