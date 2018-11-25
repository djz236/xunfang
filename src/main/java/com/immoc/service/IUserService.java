package com.immoc.service;

import com.immoc.entity.User;
import com.immoc.web.dto.UserDTO;

public interface IUserService {
	User findeUserByName(String userName);

	ServiceResult<UserDTO> findById(Long userId);
}
