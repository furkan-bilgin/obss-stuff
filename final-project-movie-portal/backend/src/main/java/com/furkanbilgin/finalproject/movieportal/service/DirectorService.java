package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import java.util.List;
import java.util.Optional;

public interface DirectorService {
  DirectorDTO saveDirector(DirectorDTO directorDTO);

  List<DirectorDTO> getAllDirectors();

  Optional<DirectorDTO> getDirectorById(Long id);

  void deleteDirectorById(Long id);

  Optional<DirectorDTO> updateDirector(Long id, DirectorDTO directorDTO);
}
