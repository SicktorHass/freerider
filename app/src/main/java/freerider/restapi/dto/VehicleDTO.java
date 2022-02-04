package freerider.restapi.dto;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonProperty;

import freerider.datamodel.Vehicle;
import freerider.datamodel.Vehicle.Power;;

public class VehicleDTO {
    /*
	 * internal counter to create unique number for each generated DTO.
	 */
	private static long serialno = 0;

	/**
	 * JsonProperty, serial number incremented for each DTO.
	 */
	@JsonProperty("serialnumber")
	private long serial;

	/**
	 * JsonProperty, unique identifier to track DTO.
	 */
	@JsonProperty("uuid")
	private long uuid;
    
	/**
     * JsonProperty, id externalized as String (not long that is internally used).
	 */
    @JsonProperty("vehicle-id")
	private String vid;
    
    @JsonProperty("vehicle-make")
    private String make;
    
	@JsonProperty("vehicle-model")
	private String model;

	@JsonProperty("vehicle-passengers")
	private int passengers;

    @JsonProperty("vehicle-power")
	private Power power;

    @JsonProperty("vehicle-color")
	private String color;

    public VehicleDTO(Vehicle copy){
		this.vid = Long.toString(copy.getID());
		this.make = copy.getMake();
		this.model = copy.getModel();
		this.passengers = copy.getPassengers();
		this.color = copy.getColor();
		this.power = copy.getPower();
		this.serial = serialno++;
		this.uuid = ThreadLocalRandom.current().nextInt( 10000000, 100000000 );
    }
    VehicleDTO(){}

	public Optional<Vehicle> create(){
		return this._create();
	}
	private Optional<Vehicle> _create(){
		Vehicle v = new Vehicle();
		v.setID(Long.parseLong(this.vid))
		 	.setMake(this.make)
			.setModel(this.model)
			.setPassengers(this.passengers)
			.setColor(this.color)
			.setPower(this.power);
		return Optional.ofNullable(v);
	}
}
