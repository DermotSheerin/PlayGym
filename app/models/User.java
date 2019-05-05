package models;
import play.db.jpa.Model;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="`User`")
public class User extends Model
{
    public String name;
    public String email;
    public String password;
    public String role;


    public User(String name, String email, String password, String role)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static User findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return getPassword().equals(password);
    }

    public String getPassword() {
        return password;
    }

    public boolean isMemberRole() {
        return role.equals("member");
    }

    public boolean isTrainerRole() {
        return role.equals("trainer");
    }



}