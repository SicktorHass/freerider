package freerider.restapi.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.qos.logback.classic.pattern.DateConverter;
import freerider.datamodel.Customer;
import freerider.datamodel.LocCode;
import freerider.datamodel.Reservation;
import freerider.datamodel.Vehicle;
import freerider.datamodel.Reservation.ReservationStatus;

public class ReservationDTO {
    @JsonProperty("reservation-id")
    long rid;
    @JsonProperty("customer-name")
    String customerName;
    @JsonProperty("vehicle")
    Vehicle vehicle;
    @JsonProperty("reservation-begin")
    String begin;
    @JsonProperty("reservation-pickup")
    LocCode pickup;
    @JsonProperty("reservation-end")
    String end;
    @JsonProperty("reservation-dropoff")
    LocCode dropoff;
    @JsonProperty("reservation-status")
    ReservationStatus reservationStatus;
    Customer customer;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm ");
    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.GERMANY);
    public ReservationDTO(Reservation copy){
        this.rid = copy.getID();
        this.customerName = copy.getCustomer().getName();
        this.vehicle = copy.getVehicle();
        this.begin = copy.getPickupTime();
        this.pickup = copy.getPickupLoc();
        this.end = copy.getDropoffTime();
        this.dropoff = copy.getDropoffLoc();
        this.reservationStatus = copy.getReservationStatus();
    }
    ReservationDTO(){
    }
    public Optional<Reservation> create(){
        return this._create();
    }
    private Optional<Reservation> _create(){
        Date b = null;
        Date e = null;
        try{
            b = df.parse(begin);
            e = df.parse(end);
        }catch(ParseException pe){
            System.err.println("couldnt parse time");
        }

        if(b.after(e)){
            System.out.println("bad");
        }
        Reservation r = new Reservation(this.rid, this.customer, this.vehicle);
        return Optional.ofNullable(r);
    }
}
