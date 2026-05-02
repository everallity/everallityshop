package com.everallity.ecommerce_backend.service.role;

import com.everallity.ecommerce_backend.entity.Role;

public interface RoleServiceInterface {
    Role findRole(String name);
}
