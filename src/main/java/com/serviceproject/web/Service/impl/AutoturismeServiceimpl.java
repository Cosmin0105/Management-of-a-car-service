package com.serviceproject.web.Service.impl;

import com.serviceproject.web.Service.AutoturismeService;
import com.serviceproject.web.dto.Autoturismedto;
import com.serviceproject.web.models.Autoturisme;
import com.serviceproject.web.models.UserEntity;
import com.serviceproject.web.repository.AutoturismeRepository;
import com.serviceproject.web.repository.UserRepository;
import com.serviceproject.web.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.serviceproject.web.mapper.AutoturismeMapper.mapToAutoturisme;
import static com.serviceproject.web.mapper.AutoturismeMapper.mapToAutoturismeDto;


@Service
public class AutoturismeServiceimpl implements AutoturismeService {
    private AutoturismeRepository autoturismeRepository;
    private UserRepository userRepository;

    @Autowired
    public AutoturismeServiceimpl(AutoturismeRepository autoturismeRepository , UserRepository userRepository) {
        this.userRepository =userRepository;
        this.autoturismeRepository = autoturismeRepository;
    }

    @Override
    public List<Autoturismedto> findAllAutoturisme() {
        List<Autoturisme> autos = autoturismeRepository.findAll();
        return  autos.stream().map(autoturisme -> mapToAutoturismeDto(autoturisme)).collect(Collectors.toList());
    }

    @Override
    public Autoturisme saveauto(Autoturismedto autoturismedto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Autoturisme autoturisme= mapToAutoturisme(autoturismedto);
        autoturisme.setCreatedBy(user);
        return autoturismeRepository.save(autoturisme);
    }

    @Override
    public Autoturismedto findAutoturismeById(long autosId) {
        Autoturisme autoturisme = autoturismeRepository.findById(autosId).get();
        return mapToAutoturismeDto(autoturisme);
    }

    @Override
    public void updateAutoturisme(Autoturismedto autoturismedto3) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Autoturisme auto = mapToAutoturisme(autoturismedto3);
        auto.setCreatedBy(user);
        autoturismeRepository.save(auto);
    }

    @Override
    public void delete(Long autosId) {
        autoturismeRepository.deleteById(autosId);
    }

    @Override
    public List<Autoturismedto> searchauto(String query) {
        List<Autoturisme> autos = autoturismeRepository.searchAutoturisme(query);
        return  autos.stream().map(autoturisme -> mapToAutoturismeDto(autoturisme)).collect(Collectors.toList());
    }



}
