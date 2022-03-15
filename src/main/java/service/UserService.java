package service;

import entity.User;
import repository.UserRepository;

import javax.swing.*;

public class UserService {

    private final UserRepository userRepository;

    public UserService(){
        userRepository=new UserRepository();
    }
    public void insertUser(User user) {
        if( user.getUsername()!=null && !user.getUsername().isEmpty() && user.getPassword()!=null && !user.getPassword().isEmpty()){
            userRepository.insertUser(user);
        }
        else {
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"Please enter your wished credentials!");
        }
    }
    public User findByIdAndPassword(String username,String password){
        if( username!=null && !username.isEmpty() && password!=null && !password.isEmpty() ){
            return userRepository.findByUsernameAndPassword(username,password);
        }
        else {
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"Please enter your credentials!");
        }
        return null;
    }
    public User findById(Integer id){
            return userRepository.findById(id);
    }
}
