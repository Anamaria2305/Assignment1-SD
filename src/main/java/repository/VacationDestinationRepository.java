package repository;

import entity.VacationDestination;
import entity.VacationPackage;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class VacationDestinationRepository {
    private static final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public void insertVacationDestination(VacationDestination vacationDestination){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationDestination);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteVacationDestination(String destinationN){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("delete from VacationDestination where destinationName=:destinationN")
                    .setParameter("destinationN", destinationN)
                    .executeUpdate();

        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No vacation destination with this name is found");
        }

        em.getTransaction().commit();
        em.close();
    }
    public  VacationDestination findById(Integer id){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT u FROM VacationDestination u WHERE u.id=:id ",
                    VacationDestination.class).setParameter("id",id).getSingleResult();
        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No vacation destination found repository");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
    public List<VacationDestination> showAllVacationDestination(){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {return em.createQuery("SELECT a FROM VacationDestination a",VacationDestination.class)
                .getResultList();}
        catch (NoClassDefFoundError e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No entries in db");

        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
}
