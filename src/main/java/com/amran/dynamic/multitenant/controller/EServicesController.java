package com.amran.dynamic.multitenant.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.amran.dynamic.multitenant.dto.EServicesDTO;
import com.amran.dynamic.multitenant.dto.EServicesListDto;
import com.amran.dynamic.multitenant.mastertenant.service.EService;

@Validated
@RestController
@RequestMapping("/eServices")
public class EServicesController {

	@Autowired
	private EService eServicesService;

	@PostMapping
	public ResponseEntity<Long> save(@Valid @RequestBody EServicesDTO vO) {
		Long id = eServicesService.save(vO);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
		eServicesService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @NotNull @PathVariable("id") Long id,
			@Valid @RequestBody EServicesDTO vO) {
		eServicesService.update(id, vO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EServicesDTO> getById(@Valid @NotNull @PathVariable("id") Long id) {
		EServicesDTO dto = eServicesService.getById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<EServicesListDto> resp = eServicesService.findAll();
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
