package com.immoc.service.user;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.immoc.entity.Role;
import com.immoc.entity.User;
import com.immoc.repository.RoleRepository;
import com.immoc.repository.UserRepository;
import com.immoc.service.IUserService;
import com.immoc.service.ServiceResult;
import com.immoc.web.dto.UserDTO;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public User findeUserByName(String userName) {
		User user=userRepository.findByName(userName);
		
		if(user==null){
			return null;
		}
		
		List<Role> roles = roleRepository.findRolesByUserId(user.getId());
		if(roles==null||roles.isEmpty()){
			throw new DisabledException("权限非法");
		}
		
		List<GrantedAuthority> anthorities=new ArrayList<>();
				roles.forEach(role->anthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
				user.setAuthorityList(anthorities);
		return user;
	}
	 @Override
	    public ServiceResult<UserDTO> findById(Long userId) {
	        User user = userRepository.findOne(userId);
	        if (user == null) {
	            return ServiceResult.notFound();
	        }
	        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
	        return ServiceResult.of(userDTO);
	    }
}
