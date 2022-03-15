package controller;

import com.mysql.cj.Session;
import entity.User;
import entity.VacationDestination;
import entity.VacationPackage;
import service.UserService;
import service.VacationDestinationService;
import service.VacationPackageService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controller.logIn.takenId;

public class welcomeScreen {
    private JButton travelAgencyButton;
    private JButton vacaySeekerButton;
    public JPanel main;
    private static final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public static void main(String[] args) throws ParseException {
        JFrame frame = new JFrame("Welcome screen");
        frame.setContentPane(new welcomeScreen(frame).main);
        frame.setLocation(600,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Welcome back!\n");

    }
    public welcomeScreen(JFrame frame) {
        travelAgencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame jFrame = new JFrame("CRUD Travel Agency");
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jFrame.setContentPane(new crudTA(jFrame).crudta);
                jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(true);
            }
        });
        vacaySeekerButton.addActionListener(new ActionListener() {
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
    }
}
