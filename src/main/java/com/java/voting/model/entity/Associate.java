package com.java.voting.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity @Table(name = "ASSOCIATE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Associate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associate", nullable = false)
    private Long idAssociate;

    private String name;

    private String cpf;

    @Transient @JsonIgnore @OneToMany(mappedBy = "associate")
    private List<Vote> votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Associate associate = (Associate) o;

        return Objects.equals(this.idAssociate, associate.getIdAssociate())
                && Objects.equals(this.name, associate.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAssociate(), getName());
    }
}