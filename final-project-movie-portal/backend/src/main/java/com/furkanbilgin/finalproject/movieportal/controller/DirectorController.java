package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import com.furkanbilgin.finalproject.movieportal.service.DirectorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {
  private final DirectorService directorService;

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
    DirectorDTO savedDirector = directorService.saveDirector(directorDTO);
    return new ResponseEntity<>(savedDirector, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
    List<DirectorDTO> directors = directorService.getAllDirectors();
    return ResponseEntity.ok(directors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
    return directorService
        .getDirectorById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<DirectorDTO> updateDirector(
      @PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
    return directorService
        .updateDirector(id, directorDTO)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void deleteDirector(@PathVariable Long id) {
    directorService.deleteDirectorById(id);
  }
}
