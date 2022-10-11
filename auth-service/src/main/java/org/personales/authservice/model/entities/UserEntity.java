package org.personales.authservice.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


    public UserEntity(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public UserEntity(Long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
