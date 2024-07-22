package com.amran.dynamic.multitenant.mastertenant.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.dto.EServiceConverter;
import com.amran.dynamic.multitenant.dto.EServicesDTO;
import com.amran.dynamic.multitenant.dto.EServicesListDto;
import com.amran.dynamic.multitenant.mastertenant.entity.Categories;
import com.amran.dynamic.multitenant.mastertenant.entity.EServices;
import com.amran.dynamic.multitenant.mastertenant.repository.CategoriesRepository;
import com.amran.dynamic.multitenant.mastertenant.repository.EServicesRepository;


@Service
public class EServicesServiceImpl implements EService {

	@Autowired
	private EServicesRepository eServicesRepository;
	
	@Autowired
	private CategoriesRepository repo;

	@Override
	public Long save(EServicesDTO dto) {
		EServices bean = new EServices();
		BeanUtils.copyProperties(dto, bean);
		
		Optional<Categories> findById = repo.findById(dto.getCategoryId());
		bean.setCategories(findById.get());
		
		bean = eServicesRepository.save(bean);
		return bean.getId();
	}

	@Override
	public void delete(Long id) {
		eServicesRepository.deleteById(id);
	}

	@Override
	public void update(Long id, EServicesDTO dto) {
		EServices bean = requireOne(id);
		BeanUtils.copyProperties(dto, bean);
		eServicesRepository.save(bean);
	}

	@Override
	public EServicesDTO getById(Long id) {
		EServices original = requireOne(id);
		return toDTO(original);
	}

	@Override
	public List<EServicesListDto> findAll() {
		List<EServices> eServicesList = eServicesRepository.findAll();
		List<EServicesListDto> listDto= eServicesList.stream().map(new EServiceConverter()).collect(Collectors.toList());
		return listDto;
	}

	@Override
	public Page<EServicesDTO> query(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<EServices> eServicesPage = eServicesRepository.findAll(pageable);
		return eServicesPage.map(this::toDTO);
	}

	private EServicesDTO toDTO(EServices original) {
		EServicesDTO bean = new EServicesDTO();
		BeanUtils.copyProperties(original, bean);
		return bean;
	}

	private EServices requireOne(Long id) {
		return eServicesRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
	}

	@Override
	public List<EServices> getAll() {
		List<EServices> findAll = eServicesRepository.findAll();
		return findAll;
	}
}
