package com.java.voting.associate;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "ASSOCIATE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Associate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associate", nullable = false)
    private Long idAssociate;

    private String name;
}