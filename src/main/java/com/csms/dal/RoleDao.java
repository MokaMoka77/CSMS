package com.csms.dal;

import com.csms.modal.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends DBContext {

    public List<Role> getAll() {
        List<Role> list = new ArrayList<>();
        String sql = "select * from Role";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Authorize"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getAuthorizeById(String id) {
        String sql = "SELECT Authorize FROM Role WHERE Id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("Authorize");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //insert 
    public void insert(Role c) {
        String sql = "INSERT INTO [dbo].[Role]\n"
                + "           ([Id]\n"
                + "           ,[Name]\n"
                + "           ,[Authorize])\n"
                + "     VALUES (? ,? ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getId());
            st.setString(2, c.getName());
            st.setString(3, c.getAuthorize());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //tim mot cai role khi co id
    public Role getRoleById(String id) {
        String sql = "select * from Role where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role r = new Role(rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Authorize"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //edit
    public void edit(Role c) {
        String sql = "UPDATE [dbo].[Role]\n"
                + "   SET [Id] = ?  , [Name] = ? , [Authorize] = ? \n"
                + " WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getId());
            st.setString(2, c.getName());
            st.setString(3, c.getAuthorize());
            st.setString(4, c.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        RoleDao c = new RoleDao();
//        List<Role> list = c.getAll();
//        System.out.println(list.get(0).getName());
//    }

}
