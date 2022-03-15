package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="user_vac",joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="vac_id"))
    private List<VacationPackage> vacationPackage=new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public User(){}

    public List<VacationPackage> getVacationPackage() {
        return vacationPackage;
    }

    public void setVacationPackage(List<VacationPackage> vacationPackage) {
        this.vacationPackage = vacationPackage;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
