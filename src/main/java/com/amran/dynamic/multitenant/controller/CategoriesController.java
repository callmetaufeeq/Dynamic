package com.amran.dynamic.multitenant.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amran.dynamic.multitenant.dto.CategoriesDto;
import com.amran.dynamic.multitenant.dto.CategoriesListDto;
import com.amran.dynamic.multitenant.mastertenant.service.CategoriesService;

@Validated
@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@PostMapping
	public ResponseEntity<String> save(@Valid @RequestBody CategoriesDto vO) {
		return new ResponseEntity<>(categoriesService.save(vO).toString(), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
		categoriesService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @NotNull @PathVariable("id") Long id,
			@Valid @RequestBody CategoriesDto vO) {
		categoriesService.update(id, vO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriesDto> getById(@Valid @NotNull @PathVariable("id") Long id) {
		CategoriesDto dto = categoriesService.getById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<Page<CategoriesDto>> query(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<CategoriesDto> dtoPage = categoriesService.query(page, size);
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<CategoriesListDto>> getAll() {
		List<CategoriesListDto> all = categoriesService.getAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

}
