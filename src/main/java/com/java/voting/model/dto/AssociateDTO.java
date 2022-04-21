package com.java.voting.model.dto;

import com.java.voting.model.entity.Associate;
import lombok.Builder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssociateDTO that = (AssociateDTO) o;

        return Objects.equals(idAssociate, that.idAssociate)
                && Objects.equals(name, that.name)
                && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssociate, name, cpf);
    }
}
