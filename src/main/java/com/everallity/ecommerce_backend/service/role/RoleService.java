package com.everallity.ecommerce_backend.service.role;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.exception.AppException;
import com.everallity.ecommerce_backend.model.Role;
import com.everallity.ecommerce_backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface{

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRole(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE));
    }
}
