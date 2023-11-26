package com.khoabug.coffeshop.role.infrastructure.repository.impl;

import com.khoabug.coffeshop.common.repository.impl.CrudRepository;
import com.khoabug.coffeshop.role.infrastructure.entitymapper.RoleMapper;
import com.khoabug.coffeshop.role.model.Role;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * @author : KhoaBug
 * @since : 8/16/2023 ,Wed
 */
@ApplicationScoped
public class RoleRepository extends CrudRepository<Role> implements com.khoabug.coffeshop.role.infrastructure.repository.RoleRepository {
    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM role";
        return query(sql, new RoleMapper());
    }

    @Override
    public Optional<Role> findById(String id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        return query(sql, new RoleMapper(), id).stream().findFirst();
    }

    @Override
    public int count(Object... parameter) {
        return 0;
    }
}
