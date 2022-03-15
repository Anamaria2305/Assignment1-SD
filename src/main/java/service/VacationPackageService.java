package service;

import entity.User;
import entity.VacationDestination;
import entity.VacationPackage;
import repository.VacationDestinationRepository;
import repository.VacationPackageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VacationPackageService {

    private final VacationPackageRepository vacationPackageRepository;
    public VacationPackageService(){
        vacationPackageRepository= new VacationPackageRepository();
    }

    public void insertVacationPackageS(VacationPackage vacationPackage) {
        if( vacationPackage.getPackageName()!=null && !vacationPackage.getPackageName().isEmpty()){
            vacationPackageRepository.insertVacationPackage(vacationPackage);
        }
        else {
            System.out.println("Cannot insert vacation package in db");

        }
    }
    public void deleteVacationPackageS(String packageName) {
        if( packageName!=null && !packageName.isEmpty()){
            vacationPackageRepository.deleteVacationPackage(packageName);
        }
        else {
            System.out.println("Cannot delete vacation package from db, something is wrong with the data.");

        }
    }
    public List<VacationPackage> showAllVacationPackage(){
            return vacationPackageRepository.showAllVacationPackage();

    }
    public List<VacationPackage> showAllAvVacationPackage(String status,String status2){
        return vacationPackageRepository.showAllAvVacationPackage(status,status2);

    }
    public List<VacationPackage> filterByDestinationVacationPackage(String dstName){
        return  this.showAllVacationPackage().stream().filter(vacationPackage -> vacationPackage.getVacationDestination().getDestinationName().equals(dstName)).collect(Collectors.toList());

    }
    public List<VacationPackage> filterByPriceVacationPackage(Float pmin,Float pmax){
        return  this.showAllVacationPackage().stream().filter(vacationPackage -> vacationPackage.getPrice()>pmin && vacationPackage.getPrice()<pmax).collect(Collectors.toList());

    }
    public List<VacationPackage> filterByDateVacationPackage(Date d1,Date d2){
        return  this.showAllVacationPackage().stream().filter(vacationPackage -> vacationPackage.getStartTime().after(d1) && vacationPackage.getStartTime().before(d2)).collect(Collectors.toList());

    }

    public void updateVacationPackageS(Integer id,String packageName, Float price, Date startDate, Date endDate, String extraDet, Integer maxBooked) {
        if( packageName!=null && !packageName.isEmpty()){
            vacationPackageRepository.updateVacationPackage(id,packageName,price,startDate,endDate,extraDet,maxBooked);
        }
        else {
            System.out.println("Cannot update vacation package from db, something is wrong with the data.");

        }
    }
    public VacationPackage findById(Integer id){
        return  vacationPackageRepository.findById(id);
    }
    public void bookVacationPackage(User user2, VacationPackage vacationPackage){
        vacationPackageRepository.bookVacationPackage(user2,vacationPackage);
    }
    public void incrementCurrentBooked(Integer id,Integer curr){
        vacationPackageRepository.incrementCurrentBooked( id,curr);

    }
    public void changeStatus(Integer id,String status){
        vacationPackageRepository.changeStatus( id,status);
    }
}