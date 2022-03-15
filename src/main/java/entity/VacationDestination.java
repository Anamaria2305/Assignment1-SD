package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="VacationDestination")
public class VacationDestination {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String destinationName;

    @OneToMany(mappedBy = "vacationDestination",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<VacationPackage> vacationPackages;

    public List<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public int getId() {
        return id;
    }

    public String getDestinationName() {
        return destinationName;
    }
    public VacationDestination(){}

    public VacationDestination(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    public String toString() {
        return "VacationDestination[" +
                "\nid=" + id +
                "\n destinationName='" + destinationName + '\'' +
                ']';
    }
}
