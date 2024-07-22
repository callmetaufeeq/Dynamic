package com.amran.dynamic.multitenant.dto;

import java.util.function.Function;

import com.amran.dynamic.multitenant.mastertenant.entity.Categories;



public class CategoriesConverter implements Function<Categories, CategoriesListDto> {

	@Override
	public CategoriesListDto apply(Categories t) {
		CategoriesListDto c = new CategoriesListDto();
		c.setId(t.getId());
		c.setColor(t.getColor());
		c.setOrder(t.getOrderIndex());
		c.setFeatured(t.getFeatured());
		c.setParentId(t.getCategory() != null ? t.getCategory().getId() : 0L);
		c.setCreatedAt(t.getCreatedAt().toLocalDateTime());
		c.setUpdatedAt(t.getUpdatedAt() != null ? t.getUpdatedAt().toLocalDateTime() : null);
		c.setName(t.getName());
		c.setDescription(t.getDescription());
		return c;

	}

}
