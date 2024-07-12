package com.amran.dynamic.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amran.dynamic.multitenant.mastertenant.entity.DictionaryCount;

public interface DictionaryRepository extends JpaRepository<DictionaryCount, Long>{

	DictionaryCount getByType(String type);
}
