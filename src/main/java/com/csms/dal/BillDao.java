package com.csms.dal;

import com.csms.modal.Bill;
import com.csms.modal.OrderLine02;
import com.csms.modal.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ACER
 */
public class BillDao extends DBContext {

    //addBill
    public void addBill(Bill b) {
        String sql = "INSERT INTO [dbo].[Bill]\n"
                + "           ([Id]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[CreatedAt]\n"
                + "           ,[CreatedBy])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, b.getId());
            ps.setInt(2, b.getTotalPrice());
            //String inputDateStr = "2023-08-14 14:49:58";
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) inputDateFormat.parse(b.getCreatedAt());

            Timestamp timestamp = new Timestamp(date.getTime());
            //LocalDateTime dateTime = LocalDateTime.now();
            //Timestamp timestamp = Timestamp.valueOf(dateTime);
            //System.out.println(timestamp);
            ps.setTimestamp(3, timestamp);
            ps.setString(4, "A6");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //show OrderLine
    public List<OrderLine02> getBill(String b) {
        List<OrderLine02> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[Bill_id]\n"
                + "      ,[Drink_id]\n"
                + "      ,[Price]\n"
                + "      ,[Number]\n"
                + "  FROM [dbo].[OrderLine]\n"
                + "  where Bill_id=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            st.setString(1, b);
            while (rs.next()) {
                OrderLine02 r = new OrderLine02(
                        rs.getString("Id"),
                        rs.getString("Bill_id"),
                        rs.getString("Drink_id"),
                        rs.getString("Price"),
                        rs.getString("Number"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Bill> getAllBill() {
        List<Bill> list = new ArrayList<>();
        String sql = "select * from Bill";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Bill b = new Bill(rs.getString("Id"),
                        rs.getInt("TotalPrice"),
                        rs.getString("CreatedAt"),
                        rs.getString("CreatedBy"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

//    public static void main(String[] args) {
//        BillDao c = new BillDao();
//        List<Bill> list = c.getAllBill();
//        System.out.println(list.get(0).getId());
//    }
}
