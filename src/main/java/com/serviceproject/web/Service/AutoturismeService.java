package com.serviceproject.web.Service;

import com.serviceproject.web.dto.Autoturismedto;
import com.serviceproject.web.models.Autoturisme;

import java.util.List;

public interface AutoturismeService {



    List<Autoturismedto> findAllAutoturisme();

    Autoturisme saveauto (Autoturismedto autoturismedto);

    Autoturismedto findAutoturismeById(long autosId);

    void updateAutoturisme(Autoturismedto auto);

    void delete(Long autosId) ;

    List<Autoturismedto> searchauto(String query);
}
