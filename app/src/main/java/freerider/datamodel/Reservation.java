package freerider.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @Column(name="RID")
    long rid;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID", foreignKey = @ForeignKey(name="CUSTOMER_ID_FK"))
    Customer customer;
    @ManyToOne
    @JoinColumn(name="VEHICLE_ID", foreignKey = @ForeignKey(name="VEHICLE_ID_FK"))
    Vehicle vehicle;
    @Column(name="PICKUPLOC")
    LocCode pickuploc;
    @Column(name="PICKUPTIME")
    String pickuptime;
    @Column(name="DROPOFFLOC")
    LocCode dropoffloc;
    @Column(name="DROPOFFTIME")
    String dropofftime;
    public enum ReservationStatus {
        Requested,
        Rejected,
        Confirmed,
        Cancelled,
    }
    @Column(name="STATUS")
    ReservationStatus rs = ReservationStatus.Requested; 


    public Reservation(long rid, Customer c, Vehicle v){
        this.rid =rid;
        this.customer=c;
        this.vehicle=v;
    }
    public Reservation(){}
    public Reservation pickup(LocCode l, String date){
        this.pickuploc = l;
        this.pickuptime = date;
        return this;
    }
    public Reservation dropoff(LocCode l, String date){
        this.dropoffloc = l;
        this.dropofftime = date;
        return this;
    }
    public long getID(){
        return this.rid;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public LocCode getPickupLoc(){
        return this.pickuploc;
    }
    public String getPickupTime(){
        return this.pickuptime;
    }
    public String getDropoffTime(){
        return this.dropofftime;
    }
    public LocCode getDropoffLoc(){
        return this.dropoffloc;
    }
    public ReservationStatus getReservationStatus(){
        return this.rs;
    }


}
