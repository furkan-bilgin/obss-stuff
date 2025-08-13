package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.user.RoleDTO;
import com.furkanbilgin.finalproject.movieportal.repository.RoleRepository;
import com.furkanbilgin.finalproject.movieportal.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;

  public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<RoleDTO> getAllRoles() {
    return roleRepository.findAll().stream()
        .map(role -> modelMapper.map(role, RoleDTO.class))
        .collect(Collectors.toList());
  }
}
