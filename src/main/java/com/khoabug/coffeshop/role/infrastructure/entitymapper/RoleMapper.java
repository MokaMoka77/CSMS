package com.khoabug.coffeshop.role.infrastructure.entitymapper;

import com.khoabug.coffeshop.common.mapper.RowMapper;
import com.khoabug.coffeshop.role.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : KhoaBug
 * @since : 8/16/2023 ,Wed
 */
public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setId(resultSet.getString("id"));
            role.setName(resultSet.getString("name"));
            role.setAuthorize(resultSet.getString("authorize"));
        } catch (SQLException exception) {
            return null;
        }
        return role;
    }
}
