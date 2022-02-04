package freerider.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {
    @Id
    @Column(name="VID")
    long vid;
    @Column(name="MAKE")
    String make;
    @Column(name="MODEL")
    String model;
    @Column(name="PASSENGERS")
    int passengers;
    @Column(name="POWER")
    Power power;
    @Column(name="COLOR")
    String color;
    @Transient
    LocCode location;
    @Column(name="IMAGEPATH")
    String images;

    public enum Power{
        hybrid,
        electric,
        gasoline,
        diesel
    };
    public Vehicle(){}
    public Vehicle(long id, String make, String model, String color){
        this.vid=id;
        this.make=make;
        this.model=model;
        this.color=color;
        this.images = "../resources/pictures/cars/"+Long.toString(this.vid);
    }

    public long getID(){
        return this.vid;
    }
    public String getMake(){
        return this.make;
    }
    public String getModel(){
        return this.model;
    }
    public String getColor(){
        return this.color;
    }
    public int getPassengers(){
        return this.passengers;
    }
    public Power getPower(){
        return this.power;
    }
    public LocCode getLocation(){
        return this.location;
    }
    public String getImages(){
        return this.images;
    }
    public Vehicle setID(long vid){
        this.vid = vid;
        return this;
    }
    public Vehicle setMake(String make){
        this.make = make;
        return this;
    }
    public Vehicle setModel(String model){
        this.model = model;
        return this;
    }
    public Vehicle setPassengers(int passengers){
        this.passengers = passengers;
        return this;
    }
    public Vehicle setPower(Power power){
        this.power = power;
        return this;
    }
    public Vehicle setLocation(LocCode location){
        this.location = location;
        return this;
    }
    public Vehicle setColor(String color){
        this.color=color;
        return this;
    }

    
}
