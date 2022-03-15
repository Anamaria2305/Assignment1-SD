package controller;

import entity.User;
import service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logIn {
    public JPanel main;
    private JPasswordField pass;
    private JButton registerButton;
    private JTextField usern;
    private JButton logInButton;
    private JButton backButton;
    public static int takenId;
    public logIn(final JFrame frame){

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame jFrame = new JFrame("Register Screen");
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jFrame.setContentPane(new register(jFrame).main);
                jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(true);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username= usern.getText();
                String password= pass.getText();
                UserService userService=new UserService();
                try{
                    User user2=userService.findByIdAndPassword(username,password);
                    takenId=user2.getId();
                    frame.dispose();
                    JFrame jFrame = new JFrame("User Screen");
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    jFrame.setContentPane(new userScreen(jFrame).mainp);
                    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    jFrame.pack();
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);

                }
                catch(NullPointerException exc){

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
    }
}
