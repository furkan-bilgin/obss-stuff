package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Director;
import com.furkanbilgin.finalproject.movieportal.repository.DirectorRepository;
import com.furkanbilgin.finalproject.movieportal.service.DirectorService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

  private final DirectorRepository directorRepository;
  private final ModelMapper modelMapper;

  @Override
  public DirectorDTO saveDirector(DirectorDTO directorDTO) {
    Director director = modelMapper.map(directorDTO, Director.class);
    Director savedDirector = directorRepository.save(director);
    return modelMapper.map(savedDirector, DirectorDTO.class);
  }

  @Override
  public List<DirectorDTO> getAllDirectors() {
    return directorRepository.findAll().stream()
        .map(director -> modelMapper.map(director, DirectorDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<DirectorDTO> getAllDirectors(Pageable pageable) {
    return directorRepository.findAll(pageable).stream()
        .map((element) -> modelMapper.map(element, DirectorDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<DirectorDTO> getDirectorById(Long id) {
    return directorRepository
        .findById(id)
        .map(director -> modelMapper.map(director, DirectorDTO.class));
  }

  @Override
  public void deleteDirectorById(Long id) {
    directorRepository.deleteById(id);
  }

  @Override
  public Optional<DirectorDTO> updateDirector(Long id, DirectorDTO directorDTO) {
    return directorRepository
        .findById(id)
        .map(
            existingDirector -> {
              modelMapper.map(directorDTO, existingDirector);
              Director updatedDirector = directorRepository.save(existingDirector);
              return modelMapper.map(updatedDirector, DirectorDTO.class);
            });
  }
}
