package com.khoabug.coffeshop.user.infrastructure.entitymapper;

import com.khoabug.coffeshop.common.mapper.RowMapper;
import com.khoabug.coffeshop.role.model.Role;
import com.khoabug.coffeshop.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : KhoaBug
 * @since : 8/17/2023 ,Thu
 */
public class UserActivateMapper implements RowMapper<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserMapper.class);

    @Override
    public User mapRow(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("fullname"));
            user.setEmail(resultSet.getString("email"));
            user.setGender(resultSet.getString("gender").equals("Nam") ? true : false);
            user.setActive(resultSet.getBoolean("status"));
            user.setDob(resultSet.getDate("dob"));
            user.setRole(new Role(resultSet.getString("role_id")));
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
        return user;
    }
}
