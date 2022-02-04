package freerider.restapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import freerider.restapi.dto.VehicleDTO;

import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;


/**
 * Spring Controller interface for /vehicles REST endpoint to access the
 * collection of vehicle resources maintained in a VehicleRepository.
 * 
 * Operations provided by the endpoint:
 * 
 * - GET /vehicles			- return JSON data for all vehicle in the repository,
 * 							  status: 200 OK.
 * 
 * @author viha
 *
 */

/*
 * Refer to endpoint URL from swagger.properties, replaces: "/api/v1/vehicles"
 */
@RequestMapping( "${app.api.endpoints.vehicles}" )
//
public interface VehiclesDTOAPI {
    /**
	 * GET /vehicles
	 * 
	 * @return DTO Array with vehicles (compact).
	 */

	/*
	 * Swagger API doc annotations:
	*/
	@Operation(
		summary = "Return all vehicles from repository.",		// appears as text in API short-list
		description = "Return all vehicles from repository.",	// appears as text inside API
		tags={ "vehicles-dto-controller" }	// appears in swagger-ui URL: http://localhost:8080/swagger-ui/index.html#/vehicles-dto-controller
	)
	@ApiResponses( value = {	// also auto-derived by Swagger
		@ApiResponse( responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
		@ApiResponse( responseCode = "401", description = "Unauthorized"),
		@ApiResponse( responseCode = "403", description = "Forbidden"),
		// to remove "404" from docs, set SwaggerConfig::Docket.useDefaultResponseMessages(true) // ->false
//		@ApiResponse( responseCode = "404", description = "Not Found")
	})

	/*
	 * Spring REST Controller annotation:
	 */
	@RequestMapping(
		method = RequestMethod.GET,
		value = "",	// relative to interface @RequestMapping
		produces = { "application/json" }
	)
	//
	ResponseEntity<List<VehicleDTO>> getVehicles();
	
	@Operation(
		summary = "Return vehicle with {id} from repository.",
		description = "Return vehicle with {id} from repository.",
		tags={ "vehicles-dto-controller" }
	)

	/*
	 * Spring REST Controller annotation:
	 */
	@RequestMapping(
		method=RequestMethod.GET,
		value="{vid}",	// relative to interface @RequestMapping
		produces={ "application/json" }
	)
	//
	ResponseEntity<VehicleDTO> getVehicle(
		@PathVariable("vid")
		@ApiParam(value = "vehicle id", required = true)
		long vid
	);
}
