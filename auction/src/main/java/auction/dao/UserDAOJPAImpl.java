package auction.dao;

import auction.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class UserDAOJPAImpl implements UserDAO {
    EntityManagerFactory ef = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU");
    EntityManager em = ef.createEntityManager();

    public UserDAOJPAImpl() {
        //users = new HashMap<String, User>();
    }

    @Override
    public int count() {
                Query q = em.createNamedQuery("User.count", User.class);
                return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(User user) {
         if (findByEmail(user.getEmail()) != null) {
            throw new EntityExistsException();
        }
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void edit(User user) {
        if (findByEmail(user.getEmail()) == null) {
            throw new IllegalArgumentException();
        }
        em.merge(user);
    }


    @Override
    public List<User> findAll() {
        Query q = em.createNamedQuery("User.getAll", User.class);
        return q.getResultList();
    }

    @Override
    public User findByEmail(String email) {
       return em.find(User.class, email);
    }

    @Override
    public void remove(User user) {
        em.remove(user.getEmail());
    }
    
    
    
    
    
}