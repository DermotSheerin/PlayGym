package models;
import javax.persistence.Entity;


@Entity
public class Trainer extends User {



//    @OneToMany(cascade = CascadeType.ALL)             // do I remove ???

    public Trainer (String name, String email, String password, String role) {
        super(name, email, password, role);
    }

    public String getName() {
        return name;
    }




}
