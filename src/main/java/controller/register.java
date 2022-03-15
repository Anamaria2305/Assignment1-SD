package controller;

import entity.User;
import service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register {
    public JPanel main;
    private JTextField iname;
    private JPasswordField ipass;
    private JButton createAccountButton;
    private JButton backButton;

    public register(final JFrame frame){
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user1=new User(iname.getText(),ipass.getText());
                UserService userService= new UserService();
                userService.insertUser(user1);
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
    }
}
