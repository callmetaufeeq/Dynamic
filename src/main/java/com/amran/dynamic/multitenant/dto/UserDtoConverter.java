package com.amran.dynamic.multitenant.dto;

import java.util.function.Function;

import com.amran.dynamic.multitenant.mastertenant.entity.UserTenant;
import com.amran.dynamic.multitenant.mastertenant.userUtils.StatusType;

public class UserDtoConverter implements Function<UserDto, UserTenant>{

	@Override
	public UserTenant apply(UserDto t) {
		
		UserTenant user = new UserTenant();
		user.setId(t.getId());
		user.setTenantClientId(t.getTenantClientId());
		user.setFirstName(t.getFirstName());
		user.setLastName(t.getLastName());
		user.setEmailId(t.getEmailId());
		user.setMobileNumber(t.getMobileNumber());
		user.setUserId(t.getUserId());
		user.setPassword(t.getPassword());
		user.setGender(t.getGender());
		user.setJoinDate(t.getJoinDate());
		user.setStatus(StatusType.Active.toString());
		user.setCreated(t.getCreated());
		user.setModified(t.getModified());
		return user;
	}

    public UserDto apply(UserTenant user) {
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmailId(user.getEmailId());
        dto.setTenantClientId(user.getTenantClientId());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setUserId(user.getUserId());
        dto.setGender(user.getGender());
        dto.setJoinDate(user.getJoinDate());
        dto.setStatus(StatusType.Active.toString());
        dto.setCreated(user.getCreated());
        dto.setModified(user.getModified());
        dto.setPassword(user.getPassword());
        return dto;
    }

}
