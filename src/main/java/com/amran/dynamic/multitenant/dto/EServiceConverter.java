package com.amran.dynamic.multitenant.dto;

import java.util.function.Function;

import com.amran.dynamic.multitenant.mastertenant.entity.EServices;



public class EServiceConverter implements Function<EServices, EServicesListDto>{

	
	@Override
	public EServicesListDto apply(EServices t) {
		EServicesListDto e=new EServicesListDto();
		e.setId(t.getId());
		e.setPrice(t.getPrice());
		e.setDescription(t.getDescription());
		e.setName(t.getName());
		e.setServiceFrequency(t.getServiceFrequency());
		e.setCost(t.getCost());
		e.setFixed(t.isFixed());
		e.setOnline(t.isOnline());
		e.setGender(t.getGender());
        e.setStart(t.getStart());
        e.setProcess(t.getProcess());
        e.setFinish(t.getFinish());
        e.setTotalServiceDuration(t.getTotalServiceDuration());
        
        CategoriesDto cd=new CategoriesDto();
        cd.setId(t.getCategories().getId());
        cd.setName(t.getCategories().getName());
        cd.setColor(t.getCategories().getColor());
        cd.setOrder(t.getCategories().getOrderIndex());
        cd.setFeatured(t.getCategories().getFeatured());
        cd.setParentId(t.getCategories()!= null ? t.getCategories().getId() : 0L);
        cd.setDescription(t.getCategories().getDescription());
        cd.setCreatedAt(t.getCategories().getCreatedAt().toLocalDateTime());
        cd.setUpdatedAt(t.getCategories().getUpdatedAt().toLocalDateTime());
        e.setCategoriesDto(cd);
        
        return e;
	}

}
