package entity;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="VacationPackage")
public class VacationPackage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private String packageName;
    @Column
    private float price;
    @Column(name = "startTime", columnDefinition="DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "endTime", columnDefinition="DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column
    private String extraDetails;
    @Column
    private int maxBooked;
    @Column
    private int currentBooked;
    @Column
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vacantiondestination_id")
    private VacationDestination vacationDestination;

    @ManyToMany(mappedBy = "vacationPackage",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<User> user=new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public float getPrice() {
        return price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public int getMaxBooked() {
        return maxBooked;
    }

    public int getCurrentBooked() {
        return currentBooked;
    }

    public String getStatus() {
        return status;
    }

    public VacationDestination getVacationDestination() {
        return vacationDestination;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public VacationPackage(){}

    public void setVacationDestination(VacationDestination vacationDestination) {
        this.vacationDestination = vacationDestination;
    }

    public VacationPackage(String packageName, float price, Date startTime, Date endTime, String extraDetails, int maxBooked, int currentBooked, String status, VacationDestination vacationDestination) {
        this.packageName = packageName;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extraDetails = extraDetails;
        this.maxBooked = maxBooked;
        this.currentBooked = currentBooked;
        this.status = status;
        this.vacationDestination = vacationDestination;
    }

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return "\nVacationPackage Details:\n" +
                "id=" + id +
                "\n  packageName='" + packageName + '\'' +
                "\n  price=" + price +
                "\n  startTime=" + f.format(startTime) +
                "\n  endTime=" +  f.format(endTime) +
                "\n  extraDetails='" + extraDetails + '\'' +
                "\n  maxBooked=" + maxBooked +
                "\n  currentBooked=" + currentBooked +
                "\n  status='" + status + '\'' +
                "\n  vacationDestination=" + vacationDestination.getId() +
                "\n";
    }
}
