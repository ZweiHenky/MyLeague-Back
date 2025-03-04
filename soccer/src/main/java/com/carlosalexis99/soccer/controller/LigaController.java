package com.carlosalexis99.soccer.controller;

import com.carlosalexis99.soccer.domain.dto.LigaDTO;
import com.carlosalexis99.soccer.domain.dto.LigaMapper;
import com.carlosalexis99.soccer.domain.dto.response.ResponseBodyDTO;
import com.carlosalexis99.soccer.domain.service.LigaService;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.specification.SearchLigaSpecification;
import com.carlosalexis99.soccer.util.Mensajes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/ligas")
public class LigaController {

    @Autowired
    private LigaService ligaService;


    @GetMapping
    public ResponseEntity<ResponseBodyDTO> showLigas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) BigDecimal minPremio,
            @RequestParam(required = false) BigDecimal maxPremio
    ){
        SearchLigaSpecification specification = new SearchLigaSpecification(nombre , minPremio, maxPremio);

        List<LigaDTO> ligas = ligaService.findAll(specification).stream().map(
                liga -> LigaMapper.mapper.ligaToDto(liga)).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, ligas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> findLiga(@PathVariable Integer id){
        Optional<Liga> ligaOptional = ligaService.findById(id);
        return ligaOptional
                .map(liga->ResponseEntity.ok(new ResponseBodyDTO(
                        Mensajes.SOLICITUD_EXITOSA,true, LigaMapper.mapper.ligaToDto(liga))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                        Mensajes.RECURSO_INEXISTENTE,false,null)));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ResponseBodyDTO> showLigasByUsuario(@PathVariable Integer id){
        List<LigaDTO> ligas = ligaService.getLigaByUsuario(id).stream().map(
                liga -> LigaMapper.mapper.ligaToDto(liga)).collect(Collectors.toList());
        if (ligas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                    Mensajes.RECURSO_INEXISTENTE,false,null));
        }
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, ligas));
    }

    @PostMapping
    public ResponseEntity<ResponseBodyDTO> createLiga(@Valid @RequestBody LigaDTO liga, BindingResult bindingResult) {
        if (liga.id() != null) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_CREACION, false, null
            ));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION , false, bindingResult.getAllErrors()
            ));
        }

        try {
            Liga savedLiga = ligaService.save(LigaMapper.mapper.LigaDtoToLiga(liga));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedLiga.getId())
                    .toUri();
            return ResponseEntity.created(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, LigaMapper.mapper.ligaToDto(savedLiga)
            ));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBodyDTO(
                            Mensajes.REGISTRO_NO_PROCESADO + e.getMessage(), false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLiga(@PathVariable Integer id,@Valid @RequestBody LigaDTO liga, BindingResult bindingResult){

        ResponseEntity<ResponseBodyDTO> findResponse = findLiga(id);
        if (findResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return findResponse;
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION , false, bindingResult.getAllErrors()
            ));
        }

        try {
            Liga updatedLiga = ligaService.update(id, LigaMapper.mapper.LigaDtoToLiga(liga));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            return ResponseEntity.ok().location(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, LigaMapper.mapper.ligaToDto(updatedLiga)
            ));
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBodyDTO(
                            Mensajes.REGISTRO_NO_PROCESADO + ex.getMessage(), false, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeLiga(@PathVariable Integer id) throws Exception {
        ligaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
