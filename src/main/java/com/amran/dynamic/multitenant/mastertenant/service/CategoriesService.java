package com.amran.dynamic.multitenant.mastertenant.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.amran.dynamic.multitenant.dto.CategoriesDto;
import com.amran.dynamic.multitenant.dto.CategoriesListDto;



public interface CategoriesService {

    Long save(CategoriesDto vO);

    void delete(Long id);

    void update(Long id, CategoriesDto vO);

    CategoriesDto getById(Long id);

    Page<CategoriesDto> query(int page, int size);
    
    List<CategoriesListDto> getAll();
    
}
