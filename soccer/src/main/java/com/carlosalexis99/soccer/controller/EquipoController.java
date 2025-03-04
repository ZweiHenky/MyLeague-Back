package com.carlosalexis99.soccer.controller;

import com.carlosalexis99.soccer.domain.dto.EquipoDTO;
import com.carlosalexis99.soccer.domain.dto.EquipoMapper;
import com.carlosalexis99.soccer.domain.dto.LigaDTO;
import com.carlosalexis99.soccer.domain.dto.LigaMapper;
import com.carlosalexis99.soccer.domain.dto.response.ResponseBodyDTO;
import com.carlosalexis99.soccer.domain.service.DivisionService;
import com.carlosalexis99.soccer.domain.service.EquipoService;
import com.carlosalexis99.soccer.persistence.entities.Division;
import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import com.carlosalexis99.soccer.persistence.specification.SearchJugadorSpecification;
import com.carlosalexis99.soccer.util.Mensajes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> showEquipos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer ligaId
    ) {
        SearchJugadorSpecification specification = new SearchJugadorSpecification(nombre, ligaId);

        List<EquipoDTO> equipos = equipoService.findAll(specification).stream().map(
                EquipoMapper.mapper::equipoToDto
        ).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, equipos
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> findEquipo(@PathVariable Integer id){
        return equipoService.findById(id)
                .map(equipo -> ResponseEntity.ok().body(new ResponseBodyDTO(
                        Mensajes.SOLICITUD_EXITOSA, true, EquipoMapper.mapper.equipoToDto(equipo)
                )))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                        Mensajes.RECURSO_INEXISTENTE, false, null
                )));

    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ResponseBodyDTO> showEquiposByUsuario(@PathVariable Integer id){
        List<EquipoDTO> equipos = equipoService.getEquipoByUsuarioId(id).stream()
                .map(EquipoMapper.mapper::equipoToDto)
                .toList();
        if (equipos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                    Mensajes.RECURSO_INEXISTENTE,false,null));
        }
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, equipos));
    }

    @PostMapping
    public ResponseEntity<ResponseBodyDTO> createEquipo(@Valid @RequestBody EquipoDTO equipo, BindingResult bindingResult){
        if(equipo.id() != null){
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_CREACION, false, null
            ));
        }

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION, false, null
            ));
        }

        try{
            Equipo savedEquipo = equipoService.save(EquipoMapper.mapper.equipoDtoToEquipo(equipo));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedEquipo.getId())
                    .toUri();
            return ResponseEntity.created(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, EquipoMapper.mapper.equipoToDto(savedEquipo)
            ));
        }catch (DataAccessException e){
            return ResponseEntity.internalServerError()
                    .body(new ResponseBodyDTO(
                    Mensajes.REGISTRO_NO_PROCESADO + e.getMessage(), false, null
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> updateEquipo(@PathVariable Integer id, @Valid @RequestBody EquipoDTO equipoDTO, BindingResult bindingResult){

        ResponseEntity<ResponseBodyDTO> response = findEquipo(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND){
            return response;
        }

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION, false, bindingResult.getAllErrors()
            ));
        }

        try{
            Equipo equipo   = equipoService.update(id, EquipoMapper.mapper.equipoDtoToEquipo(equipoDTO));
            URI location    = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            return ResponseEntity.ok().location(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, EquipoMapper.mapper.equipoToDto(equipo)
            ));
        }catch (DataAccessException e){
            return ResponseEntity.internalServerError().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_FALLIDA + e.getMessage(), false, null
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> deleteEquipo(@PathVariable Integer id) throws Exception {
        try {
            equipoService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_FALLIDA + " "+ e.getMessage(), false, null
            ));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<ResponseBodyDTO> deleteEquipoFromDivision(@RequestParam Integer idEquipo,
                                                              @RequestParam Integer idLiga) throws Exception {
        try{
            equipoService.deleteEquipoFromDivision(idEquipo, idLiga);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_FALLIDA + " "+ e.getMessage(), false, null
            ));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{divisionId}/{equipoId}")
    public ResponseEntity<ResponseBodyDTO> addEquiposToDivision(@PathVariable Integer divisionId,@PathVariable Integer equipoId) {
        Optional<Division> divisionOptional = divisionService.findById(divisionId);
        if (divisionOptional.isPresent()) {
            Division division = divisionOptional.get();
            Optional<Equipo> equipo = equipoService.findById(equipoId);
            division.getEquipos().add(equipo.get());
            divisionService.save(division);

            return ResponseEntity.ok().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, null
            ));
        } else {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_FALLIDA + " ", false, null
            ));
        }
    }
}
