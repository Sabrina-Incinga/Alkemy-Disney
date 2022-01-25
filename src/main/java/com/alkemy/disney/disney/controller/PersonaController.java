package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.PersonaBasicDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonaDTO>> getAll(){
        List<PersonaDTO> personas = personaService.getAllPersonas();
        return ResponseEntity.ok().body(personas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getDetailsById (@PathVariable Long id){
        PersonaDTO persona = personaService.getDetailsByID(id);
        return ResponseEntity.ok(persona);
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Byte age,
            @RequestParam(required = false) Short weight,
            @RequestParam(required = false) List<Long> movies
    ){
        List<PersonaDTO> personas = personaService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/characters")
    public ResponseEntity<List<PersonaBasicDTO>> getPersonasList(){
        List<PersonaBasicDTO> personas = personaService.getPersonasList();
        return ResponseEntity.ok().body(personas);
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> save(@RequestBody PersonaDTO persona) {
        //save persona
        PersonaDTO savedPersona = personaService.save(persona);
        //return 201 response, saved persona
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> update(@PathVariable Long id,@RequestBody PersonaDTO persona){
        PersonaDTO response = personaService.update(id, persona);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.personaService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
