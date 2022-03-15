package repository;

import entity.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;
import javax.swing.*;

public class UserRepository {
    private static final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public void insertUser(User user){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
        em.persist(user);
        em.getTransaction().commit();
       }
        catch(PersistenceException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"The username MUST be unique!");
            em.getTransaction().rollback();
        }

        em.close();
    }
    public  User findByUsernameAndPassword(String username,String password){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password",
                    User.class).setParameter("username",username).setParameter("password",password).getSingleResult();
        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"Wrong credentials or no existing account!Try again or create an account!");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
    public  User findById(Integer id){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.id=:id",
                    User.class).setParameter("id",id).getSingleResult();
        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

}
