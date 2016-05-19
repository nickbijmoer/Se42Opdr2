package auction.domain;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select u from User as u"),
    @NamedQuery(name = "User.count", query = "select count(u) from User as u"),
})
public class User implements Serializable {

    @Id
    private String email;

    public User() {
        
    }
    
    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
}