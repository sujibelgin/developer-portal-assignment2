package developer.portal.app;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
/* Domain developer class with variables
ID as auto generated,FirstName,lastName,email,skills */
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private String skills;
//default constructor
    protected Developer() {
    }
//Parameterized Constructor
    public Developer(String firstname,String lastname,String email, String skills) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.skills = skills;
    }
}
