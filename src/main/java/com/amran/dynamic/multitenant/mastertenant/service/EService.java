package com.amran.dynamic.multitenant.mastertenant.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.amran.dynamic.multitenant.dto.EServicesDTO;
import com.amran.dynamic.multitenant.dto.EServicesListDto;
import com.amran.dynamic.multitenant.mastertenant.entity.EServices;



public interface EService {

	public Long save(EServicesDTO vO);

	public void delete(Long id);

	public void update(Long id, EServicesDTO vO);

	public EServicesDTO getById(Long id);

	public List<EServicesListDto> findAll();

	public Page<EServicesDTO> query(int page, int size);

	public List<EServices> getAll();

}
