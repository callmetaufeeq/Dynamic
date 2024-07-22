package com.amran.dynamic.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.amran.dynamic.multitenant.mastertenant.entity.Categories;




public interface CategoriesRepository extends JpaRepository<Categories, Long>, JpaSpecificationExecutor<Categories> {

}