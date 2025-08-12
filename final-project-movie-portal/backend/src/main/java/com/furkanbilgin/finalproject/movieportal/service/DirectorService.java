package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface DirectorService {
  DirectorDTO saveDirector(DirectorDTO directorDTO);

  List<DirectorDTO> getAllDirectors();

  List<DirectorDTO> getAllDirectors(Pageable pageable);

  Optional<DirectorDTO> getDirectorById(Long id);

  void deleteDirectorById(Long id);

  Optional<DirectorDTO> updateDirector(Long id, DirectorDTO directorDTO);
}
