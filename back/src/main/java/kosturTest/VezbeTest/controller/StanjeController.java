package kosturTest.VezbeTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosturTest.VezbeTest.dto.StanjeDTO;
import kosturTest.VezbeTest.model.Stanje;
import kosturTest.VezbeTest.service.StanjeService;
import kosturTest.VezbeTest.support.StanjeToStanjeDto;



@RestController
@RequestMapping(value = "/api/stanja", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class StanjeController {
	
	@Autowired
	private StanjeService stanjeService;
	
	@Autowired
	private StanjeToStanjeDto toStanjeDto;
	
	@GetMapping
    public ResponseEntity<List<StanjeDTO>> getAll(){

        List<Stanje> stanja = stanjeService.findAll();

        return new ResponseEntity<>(toStanjeDto.convert(stanja), HttpStatus.OK);
    }

}
