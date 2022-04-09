package com.java.voting.associate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "ASSOCIATE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Associate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associate", nullable = false)
    private Long idAssociate;

    private String name;
}