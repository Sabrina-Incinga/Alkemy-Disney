package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.service.MovieOrTVSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("MovieOrTVSeries")
public class MovieOrTVSerieController {
    @Autowired
    private MovieOrTVSerieService movieOrTVSerieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieOrTVSerieDTO>> getAll(){
        List<MovieOrTVSerieDTO> movies = movieOrTVSerieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieOrTVSerieDTO> getDetailsById (@PathVariable Long id){
        MovieOrTVSerieDTO film = movieOrTVSerieService.getDetailsByID(id);
        return ResponseEntity.ok(film);
    }

    @GetMapping
    public ResponseEntity<List<MovieOrTVSerieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieOrTVSerieDTO> films = movieOrTVSerieService.getByFilters(title, genre);
        return ResponseEntity.ok(films);
    }

    @PostMapping
    public ResponseEntity<MovieOrTVSerieDTO> save(@RequestBody MovieOrTVSerieDTO movie) {
        MovieOrTVSerieDTO savedMovie = movieOrTVSerieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieOrTVSerieDTO> update(@PathVariable Long id,@RequestBody MovieOrTVSerieDTO film){
        MovieOrTVSerieDTO response = movieOrTVSerieService.update(id, film);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.movieOrTVSerieService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
