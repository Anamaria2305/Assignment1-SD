package service;

import entity.VacationDestination;
import entity.VacationPackage;
import repository.VacationDestinationRepository;

import javax.swing.*;
import java.util.List;

public class VacationDestinationService {
    private final VacationDestinationRepository vacationDestinationRepository;
    public VacationDestinationService(){
        vacationDestinationRepository=new VacationDestinationRepository();
    }

    public void insertVacationDestinationS(VacationDestination vacationDestination) {
        if( vacationDestination.getDestinationName()!=null && !vacationDestination.getDestinationName().isEmpty()){
            vacationDestinationRepository.insertVacationDestination(vacationDestination);
        }
        else {
            System.out.println("Cannot insert vacation destination in db");

        }
    }
    public void deleteVacationDestinationS(String vacationName) {
        if( vacationName!=null && !vacationName.isEmpty()){
            vacationDestinationRepository.deleteVacationDestination(vacationName);
        }
        else {
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"You did not enter any value");

        }
    }
    public VacationDestination findById(Integer id){
        if( id!=null && id>=1){
            return vacationDestinationRepository.findById(id);
        }
        else {
            System.out.println("Vacation Destination not found Service");
            return null;
        }
    }
    public List<VacationDestination> showAllVacationDestination(){
        return vacationDestinationRepository.showAllVacationDestination();

    }
}
