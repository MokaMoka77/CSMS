package com.khoabug.coffeshop.user.infrastructure.repository.iml;

import com.khoabug.coffeshop.common.paging.Pageable;
import com.khoabug.coffeshop.common.repository.impl.CrudRepository;
import com.khoabug.coffeshop.user.infrastructure.entitymapper.UserActivateMapper;
import com.khoabug.coffeshop.user.infrastructure.entitymapper.UserMapper;
import com.khoabug.coffeshop.user.model.User;
import com.khoabug.coffeshop.user.model.UserSignupRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * @author : DangKhoa
 * @since : 3/2/2023, Mon
 **/

@ApplicationScoped
public class UserRepository extends CrudRepository<User> implements com.khoabug.coffeshop.user.infrastructure.repository.UserRepository {

    @Override
    public List<User> findAll(Pageable pageable) {
        String sql = "SELECT * FROM account INNER JOIN role on role.id = account.role_id";
        return query(pageable, new UserMapper(), sql);
    }

//    @Override
//    public Long save(UserSignupRequest user) {
//        String sql = """
//                INSERT INTO account (id, email, full_name, password, role_id)
//                VALUES (?, ?, ?, ?, ?)
//                """;
//        return insert(sql, user.getId(), user.getEmail(),
//                user.getName(), user.getPassword(), user.getRoleId());
//    }

    @Override
    public void save(User user) {
        String sql = "UPDATE account SET email = ?, fullname = ?,role_id = ?, dob = ?, gender = ? ,status = ? WHERE ID = ?";
        update(sql, user.getEmail(), user.getName(), user.getRole().getId(),
                user.getDob(), user.isGender() ? "Nam" : "Nữ", user.isActive(),
                user.getId());
    }

    @Override
    public void save(UserSignupRequest user) {
        String sql = "INSERT INTO account (id, email, fullname, password, role_id, status) VALUES (?, ?, ?, ?, ?, ?) ";
        update(sql, user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getRoleId(), false);
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        return query(sql, new UserActivateMapper(), id).stream().findFirst();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM account ";
        return count(sql);
    }
}
//    @Override
//    public Optional<User> findByUserNameAndPassword(UserLoginRequest user) {
//        String sql = """
//                SELECT *
//                FROM account
//                WHERE email = ? AND password = ?
//                """;
//        return query(sql, new UserLoginRequestMapper(), user.getEmail(),
//                user.getPassword()).stream().findFirst();
//    }
