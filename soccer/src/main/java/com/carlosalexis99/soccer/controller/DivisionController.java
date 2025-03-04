package com.carlosalexis99.soccer.controller;

import com.carlosalexis99.soccer.domain.dto.DivisionDTO;
import com.carlosalexis99.soccer.domain.dto.DivisionMapper;
import com.carlosalexis99.soccer.domain.dto.response.ResponseBodyDTO;
import com.carlosalexis99.soccer.domain.service.DivisionService;
import com.carlosalexis99.soccer.persistence.entities.Division;
import com.carlosalexis99.soccer.util.Mensajes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> showDivisiones(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) BigDecimal minPremio,
            @RequestParam(required = false) BigDecimal maxPremio
    ){

        List<DivisionDTO> divisiones = divisionService.findAll().stream().map(
                division -> DivisionMapper.mapper.divisionToDto(division)).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, divisiones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> findDivision(@PathVariable Integer id){
        Optional<Division> divisionOptional = divisionService.findById(id);
        return divisionOptional
                .map(division->ResponseEntity.ok(new ResponseBodyDTO(
                        Mensajes.SOLICITUD_EXITOSA,true, DivisionMapper.mapper.divisionToDto(division))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                        Mensajes.RECURSO_INEXISTENTE,false,null)));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ResponseBodyDTO> showDivisionesByUsuario(@PathVariable Integer id){
        List<DivisionDTO> divisiones = divisionService.findById(id).stream().map(
                division -> DivisionMapper.mapper.divisionToDto(division)).collect(Collectors.toList());
        if (divisiones.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                    Mensajes.RECURSO_INEXISTENTE,false,null));
        }
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, divisiones));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDTO> createDivision(@Valid @RequestBody Division division, BindingResult bindingResult) {
//        if (division.id() != null) {
//            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
//                    Mensajes.ERROR_DE_CREACION, false, null
//            ));
//        }
//
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
//                    Mensajes.ERROR_DE_VALIDACION +"HOLAAAAAAAAAAA" , false, bindingResult.getAllErrors()
//            ));
//        }

        try {
            Division savedDivision = divisionService.save(division);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedDivision.getId())
                    .toUri();
            return ResponseEntity.created(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, DivisionMapper.mapper.divisionToDto(savedDivision)
            ));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBodyDTO(
                            Mensajes.REGISTRO_NO_PROCESADO + e.getMessage(), false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDivision(@PathVariable Integer id,@Valid @RequestBody DivisionDTO division, BindingResult bindingResult){

        ResponseEntity<ResponseBodyDTO> findResponse = findDivision(id);
        if (findResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return findResponse;
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION , false, bindingResult.getAllErrors()
            ));
        }

        try {
            Division updatedDivision = divisionService.update(id, DivisionMapper.mapper.dtoToDivision(division));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            return ResponseEntity.ok().location(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, DivisionMapper.mapper.divisionToDto(updatedDivision)
            ));
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBodyDTO(
                            Mensajes.REGISTRO_NO_PROCESADO + ex.getMessage(), false, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDivision(@PathVariable Integer id) throws Exception {
        divisionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{divisionId}/{ligaId}")
    public ResponseEntity<ResponseBodyDTO> asignarLiga(@PathVariable Integer divisionId, @PathVariable Integer ligaId) {
        Division division = divisionService.addLigaToDivision(divisionId, ligaId);
        if (division != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            return ResponseEntity.ok().location(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, DivisionMapper.mapper.divisionToDto(division)
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
