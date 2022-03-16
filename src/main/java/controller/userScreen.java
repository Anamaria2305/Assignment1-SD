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

public class userScreen {
    public JPanel mainp;
    private JTextArea textArea1;
    private JButton viewAllAvailablePackageButton;
    private JScrollPane jscrollpane;
    private JTextField name;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton fdest;
    private JButton fprice;
    private JButton fper;
    private JButton bookVacationPackageButton;
    private JButton backButton;
    private JTextField idvp;
    private JButton viewAllBookedVacantionButton;
    private JButton viewAllDestinationsButton;

    public userScreen(final JFrame frame){
        viewAllAvailablePackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(null);
                List<VacationPackage> result = new ArrayList<>();
                VacationPackageService vacationPackageService=new VacationPackageService();
                try{
                    result=vacationPackageService.showAllAvVacationPackage("NOT_BOOKED","IN_PROGRESS");
                    if(result.isEmpty()){

                        textArea1.append("No available Vacation Packages");
                    }
                    else
                        textArea1.append(result.toString());
                }
               catch(NullPointerException ex){
                   textArea1.append("No available Vacation Packages");
               }

            }
        });
        fdest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String packageN=name.getText();
                textArea1.setText(null);
                List<VacationPackage> result = new ArrayList<>();
                VacationPackageService vacationPackageService=new VacationPackageService();
                result=vacationPackageService.filterByDestinationVacationPackage(packageN);
                if(result.isEmpty()){

                    textArea1.append("No existing Vacation Packages with that name");
                }
                else
                    textArea1.append(result.toString());

            }
        });
        fprice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                Float minprice= Float.parseFloat(textField2.getText());
                Float maxprice= Float.parseFloat(textField3.getText());
                    textArea1.setText(null);
                    List<VacationPackage> result = new ArrayList<>();
                    VacationPackageService vacationPackageService=new VacationPackageService();
                    result=vacationPackageService.filterByPriceVacationPackage(minprice,maxprice);
                    if(result.isEmpty()){

                        textArea1.append("No existing Vacation Packages within this range");
                    }
                    else
                        textArea1.append(result.toString());}
                catch (NumberFormatException ex){
                    JFrame f;
                    f=new JFrame();
                    JOptionPane.showMessageDialog(f,"The price field must be a number!");
                }


            }
        });
        fper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try{ String date = textField4.getText();
                    Date date12 = formatter.parse(date);
                    String date2 = textField5.getText();
                    Date date23 = formatter.parse(date2);
                    textArea1.setText(null);
                    List<VacationPackage> result = new ArrayList<>();
                    VacationPackageService vacationPackageService=new VacationPackageService();
                    try{
                         result=vacationPackageService.filterByDateVacationPackage(date12,date23);
                        if(result.isEmpty()){

                            textArea1.append("No existing Vacation Packages between these dates");
                        }
                        else
                            textArea1.append(result.toString());
                    }
                    catch(NullPointerException ex){
                        System.out.println("gol");
                    }

                }
                catch (ParseException ex) {
                    JFrame f;
                    f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Please enter date in format YYYY-MM-DD");

                }


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame jFrame = new JFrame("Login Vacay seeker");
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jFrame.setContentPane(new logIn(jFrame).main);
                jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(true);
            }
        });
        bookVacationPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idvp2=Integer.parseInt(idvp.getText());
                UserService userService=new UserService();
                User user2= userService.findById(takenId);
                VacationPackageService vacationPackageService=new VacationPackageService();
                VacationPackage vacationPackage=vacationPackageService.findById(idvp2);

                if(vacationPackage.getCurrentBooked() >= vacationPackage.getMaxBooked()){
                    JFrame f;
                    f=new JFrame();
                    JOptionPane.showMessageDialog(f,"This vacation is fully booked, please choose from our pool another one!");

                }
                else{
                    vacationPackageService.bookVacationPackage(user2,vacationPackage);
                    vacationPackageService.incrementCurrentBooked(idvp2,vacationPackage.getCurrentBooked()+1);
                    if(vacationPackage.getCurrentBooked() == vacationPackage.getMaxBooked()-1){
                        vacationPackageService.changeStatus(idvp2,"BOOKED");
                    }
                    if(vacationPackage.getCurrentBooked()==0)
                    {
                        vacationPackageService.changeStatus(idvp2,"IN_PROGRESS");
                    }
                }

            }
        });
        viewAllDestinationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(null);
                List<VacationDestination> result = new ArrayList<>();
                VacationDestinationService vacationDestinationService=new VacationDestinationService();
                try{
                    result=vacationDestinationService.showAllVacationDestination();
                    if(result.isEmpty()){

                        textArea1.append("No existing Vacation Destination");
                    }
                    else
                        textArea1.append(result.toString());
                }
                catch(NullPointerException ex){
                    textArea1.append("No existing Vacation Destination");
                }

            }
        });
        viewAllBookedVacantionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(null);
                List<VacationPackage> result = new ArrayList<>();
                UserService userService=new UserService();
                User user2= userService.findById(takenId);
                result=user2.getVacationPackage();
                if(result.isEmpty()){

                    textArea1.append("You did not book any Vacation Package");
                }
                else
                    textArea1.append(result.toString());


            }
        });
    }
}
