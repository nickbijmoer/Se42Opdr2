package auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// opdr 1 annoteren 


@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query = "select a from USERS as a WHERE a.email = :email"),
    @NamedQuery(name="User.count", query = "select COUNT(*) from USERS as a")
})
@Entity  @Table(name="USERS")
public class User {

    @Id  @GeneratedValue
    private Long id;
    private String email;

    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
}
