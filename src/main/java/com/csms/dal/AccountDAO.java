package com.csms.dal;

import com.csms.modal.Account;
import com.khoabug.coffeshop.common.services.PBKDF2Hasher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends DBContext {
    private PreparedStatement st;
    private ResultSet rs;
    private static final PBKDF2Hasher hasher = new PBKDF2Hasher();
    private static final Logger logger = LogManager.getLogger();
    private static final String ERR = "Error at AccountDAO: {}";
    private static final String ID = "Id";
    private static final String EMAIL = "Email";
    private static final String PASS_WORD = "Password";
    private static final String FULL_NAME = "Fullname";
    private static final String DOB = "Dob";
    private static final String ROLE_ID = "Role_id";
    private static final String GENDER = "Gender";
    private static final String STATUS = "Status";

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Email]\n" +
                "      ,[Password]\n" +
                "      ,[Fullname]\n" +
                "      ,[Dob]\n" +
                "      ,[Role_id]\n" +
                "      ,[Gender]\n" +
                "      ,[Status]\n" +
                "  FROM [dbo].[Account]";
        try {
            DBContext dbContext = new DBContext();
            st = dbContext.connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getString(ID));
                a.setEmail(rs.getString(EMAIL));
                a.setPassWord(rs.getString(PASS_WORD));
                a.setFullName(rs.getString(FULL_NAME));
                a.setDob(rs.getDate(DOB));
                a.setRoleId(ROLE_ID);
                a.setGender(rs.getString(GENDER));
                a.setStatus(rs.getBoolean(STATUS));
                list.add(a);
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return list;
    }

    public String getId(String email) {
        String sql = "SELECT Id FROM Account WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(ID);
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return null;
    }

    public String getFullName(String email) {
        String sql = "SELECT Fullname FROM Account WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(FULL_NAME);
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return null;
    }

    public String getRoleId(String email) {
        String sql = "SELECT Role_id FROM Account WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(ROLE_ID);
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return null;
    }

    public boolean checkLogin(String email, String pass) {
        String sql = "SELECT Password FROM Account WHERE Email=? AND Status=1";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next() && hasher.checkPassword(pass.toCharArray(), rs.getString(PASS_WORD))) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return false;
    }

    public boolean checkEmailExist(String email) {
        String sql = "SELECT Id FROM Account WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return false;
    }

    public boolean checkPasswordExist(String email,String newpass) {
        String sql = "SELECT Password FROM Account WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next() && hasher.checkPassword(newpass.toCharArray(), rs.getString(PASS_WORD))) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return false;
    }

    public boolean changePassword(String email, String newpass) {
        String sql = "UPDATE Account SET Password=? WHERE Email=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, hasher.hash(newpass.toCharArray()));
            st.setString(2, email);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error(ERR, e.getMessage());
        }
        return false;
    }
}
