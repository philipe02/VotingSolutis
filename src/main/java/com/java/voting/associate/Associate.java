package com.java.voting.associate;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity @Table(name = "ASSOCIATE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Associate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associate", nullable = false)
    private Long idAssociate;

    private String name;

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