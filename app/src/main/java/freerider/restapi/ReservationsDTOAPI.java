package freerider.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import freerider.restapi.dto.ReservationDTO;
import io.swagger.annotations.ApiParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
/**
 * Spring Controller interface for /reservations REST endpoint to access the
 * collection of reservation resources maintained in a ReservationRepository.
 * 
 * Operations provided by the endpoint:
 * 
 * - GET /reservations/{rid}	- return JSON data for reservation with rid,
 * 							      status: 200 OK, 404 not found.
 * 
 * - POST /reservations    - create new reservation in the repository from JSON object
 * 							 passed with the request,
 * 							 status: 201 created, 409 conflict, 400 bad request.
 * 
 * - PUT /reservations/{rid}    - updated existing objects in the repository from JSON
 * 							    objects passed with the request,
 * 							    status: 202 accepted, 404 not found, 400 bad request.
 * 
 * - DELETE /reservations/{rid}	- delete reservation with rid,
 * 							      status: 202 accepted, 404 not found, 400 bad request.
 *
 */
@RequestMapping( "${app.api.endpoints.reservations}" )
//
public interface ReservationsDTOAPI {
    /**
	 * GET /reservations/{rid}
	 * 
	 * @return ReservationDTO.
	 */

	/*
	 * Swagger API doc annotations:
	 */
	@Operation(
		summary = "Return reservation with {rid} from repository.",
		description = "Return reservation with {rid} from repository.",
		tags={ "reservations-dto-controller" }
		)
		/*
		* Spring REST Controller annotation:
		*/
	@ApiResponses( value = {	// also auto-derived by Swagger
		@ApiResponse( responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
		@ApiResponse( responseCode = "401", description = "Unauthorized"),
		@ApiResponse( responseCode = "403", description = "Forbidden"),
		// to remove "404" from docs, set SwaggerConfig::Docket.useDefaultResponseMessages(true) // ->false
//		@ApiResponse( responseCode = "404", description = "Not Found")
	})
	@RequestMapping(
		method=RequestMethod.GET,
		value="{rid}",	// relative to interface @RequestMapping
		produces={ "application/json" }
	)
	//
	ResponseEntity<ReservationDTO> getReservation(
		@PathVariable("rid")
		@ApiParam(value = "reservation id", required = true)
		long rid
	);

	/**
	 * POST /reservations
	 * 
	 * Add new reservation from DTO
	 
	 * Swagger API doc annotations:
	 */
	@Operation(
		summary = "Add new reservation to repository.",
		description = "Add new reservation to repository.",
		tags={ "reservations-dto-controller" }
	)

	/*
	 * Spring REST Controller annotation:
	 */
	@RequestMapping(
		method = RequestMethod.POST,
		value = ""	// relative to interface @RequestMapping
	)
	//
	public ResponseEntity<ReservationDTO> postReservation( @RequestBody ReservationDTO rdto );
	
	/**
	 * PUT /reservations
	 * 
	 * Alter reservation from DTO
	 
	 * Swagger API doc annotations:
	 */
	@Operation(
		summary = "Alter an existing reservation",
		description = "Alter an existing reservation", 
		tags = {"reservations-dto-controller"}
	)
	@RequestMapping(
		method= RequestMethod.PUT,
		value= ""
	)
	public ResponseEntity<ReservationDTO> putReservation(@RequestBody ReservationDTO rdto);
	@Operation(
		summary = "Delete existing reservation",
		description = "Delete existing reservation",
		tags = {"reservations-dto-controller"}
	)
	@RequestMapping(
		method = RequestMethod.DELETE,
		value= "{rid}"
	)
	public ResponseEntity<?> deleteReservation(
		@PathVariable("rid")
		@ApiParam(value = "reservation id", required = true)
		long rid
		);


}
