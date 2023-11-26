package com.khoabug.coffeshop.user.infrastructure.entitymapper;

import com.khoabug.coffeshop.common.mapper.RowMapper;
import com.khoabug.coffeshop.role.model.Role;
import com.khoabug.coffeshop.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : DangKhoa
 * @since : 3/6/2023, Mon
 **/
public class UserMapper implements RowMapper<User> {
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
//            user.setPassword(resultSet.getString("password"));
            user.setDob(resultSet.getDate("dob"));

            user.setRole(new Role(resultSet.getString("role_id"),
                    resultSet.getString("name"),
                    resultSet.getString("authorize")));
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
        return user;
    }
}
