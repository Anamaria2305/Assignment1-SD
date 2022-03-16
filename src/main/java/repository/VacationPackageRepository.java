package repository;

import entity.User;
import entity.VacationDestination;
import entity.VacationPackage;

import javax.persistence.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VacationPackageRepository {
    private static final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public void insertVacationPackage(VacationPackage vacationPackage, VacationDestination vacationDestination){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        vacationPackage.setVacationDestination(vacationDestination);
        try{
        em.persist(vacationPackage);
            List<VacationPackage> vacPac= vacationDestination.getVacationPackages();
            vacPac.add(vacationPackage);
            vacationDestination.setVacationPackages(vacPac);

            em.merge(vacationDestination);
            em.merge(vacationPackage);
        }
        catch(PersistenceException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No vacation destination with this id");
        }
        em.getTransaction().commit();
        em.close();
    }
    public void deleteVacationPackage(String name){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("delete from VacationPackage where packageName=:nameN")
                    .setParameter("nameN", name)
                    .executeUpdate();

        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No vacation package with this name is found");
        }

        em.getTransaction().commit();
        em.close();
    }
    public List<VacationPackage> showAllVacationPackage(){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {return em.createQuery("SELECT a FROM VacationPackage a",VacationPackage.class)
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
    public void updateVacationPackage(Integer id,String packageName, Float price, Date startDate, Date endDate,String extraDet,Integer maxBooked){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("UPDATE VacationPackage s SET  s.packageName=:packageName, s.price=:price,s.startTime=:date12,s.endTime=:date23,s.extraDetails=:extraDet, s.maxBooked=:maxBooked where s.id=:id")
                    .setParameter("packageName",packageName).setParameter("price",price).setParameter("date12",startDate).setParameter("date23",endDate).setParameter("maxBooked",maxBooked).setParameter("extraDet",extraDet).setParameter("id",id)
                    .executeUpdate();

        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No update can be done, such entry does not exist");
        }

        em.getTransaction().commit();
        em.close();
    }

    public List<VacationPackage> showAllAvVacationPackage(String status,String status2){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {return em.createQuery("SELECT a FROM VacationPackage a WHERE a.status=:status OR a.status=:status2",VacationPackage.class)
                .setParameter("status",status).setParameter("status2",status2).getResultList();}
        catch (NoClassDefFoundError e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No available vacation packages");

        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
    public VacationPackage findById(Integer id){
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {return em.createQuery("SELECT a FROM VacationPackage a WHERE a.id=:id",VacationPackage.class)
                .setParameter("id",id).getSingleResult();}
        catch (NoClassDefFoundError e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No vacation package with that id");

        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void bookVacationPackage(User user2,VacationPackage vacationPackage){
        List<User> userList=vacationPackage.getUser();
        userList.add(user2);
        List<VacationPackage> vacationPackageList=user2.getVacationPackage();
        vacationPackageList.add(vacationPackage);
        user2.setVacationPackage(vacationPackageList);
        vacationPackage.setUser(userList);
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user2);
        em.merge(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }
    public void incrementCurrentBooked(Integer id,Integer curr){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("UPDATE VacationPackage s SET  s.currentBooked=:curr where s.id=:id")
                    .setParameter("curr",curr).setParameter("id",id)
                    .executeUpdate();

        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No increment can be done, such entry does not exist");
        }

        em.getTransaction().commit();
        em.close();
    }

    public void changeStatus(Integer id,String status){

        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("UPDATE VacationPackage s SET  s.status=:status where s.id=:id")
                    .setParameter("status",status).setParameter("id",id)
                    .executeUpdate();

        }
        catch(NoResultException e){
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"No status change can be done, such entry does not exist");
        }

        em.getTransaction().commit();
        em.close();
    }

}
