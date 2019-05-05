package models;
import javax.persistence.Entity;


@Entity
public class Trainer extends User {

    public Trainer (String name, String email, String password, String role) {
        super(name, email, password, role);
    }

    public String getName() {
        return name;
    }


}
