package auction.service;

import java.util.*;
import auction.domain.User;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import javax.persistence.EntityManager;

public class RegistrationMgr {

    private EntityManager em;

    public RegistrationMgr(EntityManager em) {
        this.em = em;
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     *
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public User registerUser(String email) {
        UserDAO userDAO = new UserDAOJPAImpl(em);

        if (!email.contains("@")) {
            return null;
        }

        User user = userDAO.findByEmail(email);
        
        if (user != null)
        {
            return user;
        }

        user = new User(email);
        try {
            userDAO.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public User getUser(String email) {
        UserDAOJPAImpl userDAO = new UserDAOJPAImpl(em);
        User user = null;
        try
        {
            user = userDAO.findByEmail(email);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<User> getUsers() {
        UserDAO userDAO = new UserDAOJPAImpl(em);
        List<User> users = null;
        try
        {
            users = userDAO.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return users;
    }
}