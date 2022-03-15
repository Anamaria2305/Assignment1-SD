package controller;

import entity.User;
import entity.VacationDestination;
import entity.VacationPackage;
import service.UserService;
import service.VacationDestinationService;
import service.VacationPackageService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controller.logIn.takenId;

public class crudTA {

    public JPanel crudta;
    private JTextField vacName;
    private JButton insertButton;
    private JButton delvac;
    private JTextField delName;
    private JButton insertVacationPackageButton;
    private JTextField curbooked;
    private JTextField status;
    private JTextField name;
    private JTextField price;
    private JTextField start;
    private JTextField end;
    private JTextField destid;
    private JTextField exdet;
    private JTextField maxbooked;
    private JTextField nameDel;
    private JButton delButton;
    private JButton showAllVacationPackagesButton;
    private JScrollPane jscrollpane;
    private JTextArea textarea;
    private JButton updateExistingVacationPackageButton;
    private JTextField oldname;
    private JTextField eprice;
    private JTextField estartdate;
    private JTextField eenddate;
    private JTextField eed;
    private JTextField emb;
    private JTextField ename;
    private JButton backButton;
    private JButton showAllVacationDestinationsButton;

    public crudTA(final JFrame frame){
        curbooked.setText("0");
        status.setText("NOT_BOOKED");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VacationDestination vacationDestination=new VacationDestination(vacName.getText());
                VacationDestinationService vacationDestinationService= new VacationDestinationService();
                vacationDestinationService.insertVacationDestinationS(vacationDestination);
            }
        });
        delvac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String delVac= delName.getText();
                System.out.println(delVac);
                VacationDestinationService vacationDestinationService=new VacationDestinationService();
                vacationDestinationService.deleteVacationDestinationS(delVac);
            }
        });
        insertVacationPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float price2=Float.parseFloat(price.getText());
                Integer maxbooked2=Integer.parseInt(maxbooked.getText());
                Integer iddst=Integer.parseInt(destid.getText());
                VacationDestinationService vacationDestinationService=new VacationDestinationService();
                VacationDestination vacationDestination=vacationDestinationService.findById(iddst);
                try {
                    Date date1=new SimpleDateFormat("YYYY-MM-DD").parse(start.getText());
                    Date date2=new SimpleDateFormat("YYYY-MM-DD").parse(end.getText());
                    VacationPackage vacationPackage=new VacationPackage(name.getText(),price2,date1,date2,exdet.getText(),maxbooked2,0,"NOT_BOOKED",vacationDestination);
                    VacationPackageService vacationPackageService= new VacationPackageService();
                    vacationPackageService.insertVacationPackageS(vacationPackage);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }


            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameDel2= nameDel.getText();
                VacationPackageService vacationPackageService=new VacationPackageService();
                vacationPackageService.deleteVacationPackageS(nameDel2);
            }
        });
        showAllVacationPackagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText(null);
                List<VacationPackage> result = new ArrayList<>();
                VacationPackageService vacationPackageService=new VacationPackageService();
                result=vacationPackageService.showAllVacationPackage();
                if(result.isEmpty()){

                    textarea.append("No Vacation Packages");
                }
                else
                    textarea.append(result.toString());
            }
        });
        updateExistingVacationPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float price3=Float.parseFloat(eprice.getText());
                Integer maxbooked3=Integer.parseInt(emb.getText());
                Integer id=Integer.parseInt(oldname.getText());
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String date = estartdate.getText();
                    Date date12 = formatter.parse(date);
                    String date2 = eenddate.getText();
                    Date date23 = formatter.parse(date2);
                    VacationPackageService vacationPackageService1=new VacationPackageService();
                    vacationPackageService1.updateVacationPackageS(id, ename.getText(),price3,date12,date23,eed.getText(),maxbooked3);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }



        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame = new JFrame("logIn");
                frame.setContentPane(new welcomeScreen(frame).main);
                frame.setLocation(600,200);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        showAllVacationDestinationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText(null);
                List<VacationDestination> result = new ArrayList<>();
                VacationDestinationService vacationDestinationService=new VacationDestinationService();
                result=vacationDestinationService.showAllVacationDestination();
                if(result.isEmpty()){

                    textarea.append("No existing Vacation Destination");
                }
                else
                    textarea.append(result.toString());
            }
        });
    }
}