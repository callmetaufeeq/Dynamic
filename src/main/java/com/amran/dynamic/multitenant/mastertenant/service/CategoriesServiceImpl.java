package com.amran.dynamic.multitenant.mastertenant.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.dto.CategoriesConverter;
import com.amran.dynamic.multitenant.dto.CategoriesDto;
import com.amran.dynamic.multitenant.dto.CategoriesListDto;
import com.amran.dynamic.multitenant.mastertenant.entity.Categories;
import com.amran.dynamic.multitenant.mastertenant.repository.CategoriesRepository;


@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	private CategoriesDto toDTO(Categories original) {
		CategoriesDto bean = new CategoriesDto();
		bean.setId(original.getId());
		bean.setColor(original.getColor());
		bean.setOrder(original.getOrderIndex());
		bean.setFeatured(original.getFeatured());
		bean.setParentId(original.getCategory() != null ? original.getCategory().getId() : null);
		bean.setCreatedAt(original.getCreatedAt() != null ? original.getCreatedAt().toLocalDateTime() : null);
		bean.setUpdatedAt(original.getUpdatedAt() != null ? original.getUpdatedAt().toLocalDateTime() : null);
		bean.setName(original.getName());
		bean.setDescription(original.getDescription());
		return bean;
	}

	private Categories requireOne(Long id) {
		return categoriesRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
	}

	@Override
	public Long save(CategoriesDto vO) {
		Categories bean = new Categories();
		bean.setColor(vO.getColor());
		bean.setOrderIndex(vO.getOrder());
		bean.setFeatured(vO.getFeatured());
		if (vO.getParentId() != null && vO.getParentId() > 0) {
			Categories parentCategory = requireOne(vO.getParentId());
			bean.setCategory(parentCategory);
		}
		if (vO.getCreatedAt() != null) {
			bean.setCreatedAt(Timestamp.valueOf(vO.getCreatedAt()));
		}
		if (vO.getUpdatedAt() != null) {
			bean.setUpdatedAt(Timestamp.valueOf(vO.getUpdatedAt()));
		}
		bean.setName(vO.getName());
		bean.setDescription(vO.getDescription());
		bean = categoriesRepository.save(bean);
		return bean.getId();
	}

	@Override
	public void delete(Long id) {
		categoriesRepository.deleteById(id);
	}

	@Override
	public void update(Long id, CategoriesDto vO) {
		Categories bean = requireOne(id);
		bean.setColor(vO.getColor());
		bean.setOrderIndex(vO.getOrder());
		bean.setFeatured(vO.getFeatured());
		if (vO.getParentId() != null && vO.getParentId() > 0) {
			Categories parentCategory = requireOne(vO.getParentId());
			bean.setCategory(parentCategory);
		}
		if (vO.getCreatedAt() != null) {
			bean.setCreatedAt(Timestamp.valueOf(vO.getCreatedAt()));
		}
		if (vO.getUpdatedAt() != null) {
			bean.setUpdatedAt(Timestamp.valueOf(vO.getUpdatedAt()));
		}
		bean.setName(vO.getName());
		bean.setDescription(vO.getDescription());
		categoriesRepository.save(bean);
	}

	@Override
	public CategoriesDto getById(Long id) {
		Categories original = requireOne(id);
		return toDTO(original);
	}

	@Override
	public Page<CategoriesDto> query(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Categories> categoriesPage = categoriesRepository.findAll(pageable);
		return categoriesPage.map(this::toDTO);
	}

	@Override
	public List<CategoriesListDto> getAll() {
		List<Categories> cat = categoriesRepository.findAll();
		List<CategoriesListDto> listDto = cat.stream().map(new CategoriesConverter()).collect(Collectors.toList());
		return listDto;

	}
}
