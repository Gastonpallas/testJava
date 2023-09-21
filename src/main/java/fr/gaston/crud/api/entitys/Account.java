package fr.gaston.crud.api.entitys;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@ToString
public class Account {

    @Id
    @SequenceGenerator(name = "name_sequence", sequenceName = "name_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_sequence")
    private Long id;
    private String username;
    private String mail;
    private String password;


    public Account(Long id, String username, String mail, String password) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public Account(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public Account() {

    }
}
