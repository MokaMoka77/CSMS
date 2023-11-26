package com.csms.dal;

import com.csms.modal.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO extends DBContext{
    
    // read all records from table 'categories' trong sql Server
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Name]\n" +
                "  FROM [dbo].[Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Category ca = new Category(rs.getString("Id"), 
                                           rs.getString("Name"));
                list.add(ca);
            }
        } catch (Exception e) {     
             e.printStackTrace();
        }
        return list;
    }
    
    
    public Category getCategoryById(String id){
        String sql = "SELECT [Id]\n" +
                "      ,[Name]\n" +
                "  FROM [dbo].[Category] where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category ca = new Category(rs.getString("Id"), rs.getString("Name"));
                return ca;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
//=========Drink======================================================   
    
    public ArrayList<Drink> searchByPredicates(String cid,String price,String name){
        ArrayList<Drink> listD = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Category_id]\n" +
                "      ,[Name]\n" +
                "      ,[Path_image]\n" +
                "      ,[Price]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Drink] where 3=3";
        if(!cid.equals("C0")){
            System.out.println(cid);
            sql+=" and Category_id='"+cid+"'";
        }
        if(!price.equals("0")){
            if(price.contains("-")){
                String[] okane = price.split("-");
                //sql+="and "+okane[0]+"<= price and "+okane[1]+">=price";
                sql+=" and Price between "+okane[0]+" and "+okane[1];
            }else{
                sql+=" and Price>="+price;
            }
        }
        if(!name.isEmpty()){
            sql+= " and Name like N'%"+name+"%'";
        }
        try {
           PreparedStatement ps = connection.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Drink d = new Drink();
               d.setId(rs.getString("Id"));
               d.setName(rs.getString("Name"));
               d.setStatus(rs.getBoolean("Status"));
               d.setPrice(rs.getInt("Price"));
               d.setPathImage(rs.getString("Path_image"));
               Category ca = getCategoryById(rs.getString("Category_id"));
               d.setCa(ca);
               listD.add(d);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(listD);
        return listD;
    }
   
    public ArrayList<Drink> getDrinkByCid(String cid){
        ArrayList<Drink> listD = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Category_id]\n" +
                "      ,[Name]\n" +
                "      ,[Path_image]\n" +
                "      ,[Price]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Drink] where 3=3";
        if(!cid.equals("C0")){
            sql+=" and Category_id='"+cid+"'";
        }
        try {
           PreparedStatement ps = connection.prepareStatement(sql);
           //ps.setInt(1, cid);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Drink d = new Drink();
               d.setId(rs.getString("Id"));
               d.setName(rs.getString("Name"));
               d.setPathImage(rs.getString("Path_image"));
               d.setPrice(rs.getInt("Price"));
               d.setStatus(rs.getBoolean("Status"));
               Category ca = getCategoryById(rs.getString("Category_id"));
               d.setCa(ca);
               listD.add(d);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listD;
    }
    
    public Drink getDrinkByid(String id){
        String sql = "SELECT [Id]\n" +
                "      ,[Category_id]\n" +
                "      ,[Name]\n" +
                "      ,[Path_image]\n" +
                "      ,[Price]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Drink] where Id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Drink d = new Drink();
            if(rs.next()){
               d.setId(rs.getString("Id"));
               d.setName(rs.getString("Name"));
               d.setStatus(rs.getBoolean("Status"));
               d.setPrice(rs.getInt("Price"));
               
               d.setPathImage(rs.getString("Path_image"));
               Category ca = getCategoryById(rs.getString("Category_id"));
               d.setCa(ca);
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Ingredient> searchByName(String name){
        ArrayList<Ingredient> listNL = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Name]\n" +
                "      ,[Weight]\n" +
                "      ,[Date]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Ingredient] where [name] like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ingredient nl = new Ingredient();
                nl.setId(rs.getString("Id"));
                nl.setName(rs.getString("Name"));
                nl.setWeight(rs.getInt("Weight"));
                nl.setDate(rs.getDate("Date"));
                nl.setStatus(rs.getBoolean("Status"));

                listNL.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNL;
    }
    
    public void addDrink(Drink d){
        String sql = "INSERT INTO [dbo].[Drink]\n" +
                "           ([Id]\n" +
                "           ,[Category_id]\n" +
                "           ,[Name]\n" +
                "           ,[Path_image]\n" +
                "           ,[Price]\n" +
                "           ,[Status]\n" +
                "           ,[CreatedAt]\n" +
                "           ,[CreatedBy]\n" +
                "           ,[ModifiedAt]\n" +
                "           ,[ModifiedBy])\n" +
                "     VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getId());
            ps.setString(2, d.getCa().getId());
            ps.setString(3, d.getName());
            ps.setString(4, d.getPathImage());
            ps.setInt(5, d.getPrice());
            ps.setBoolean(6, d.isStatus());
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateTime);
//            Date da = new Date(System.currentTimeMillis());
//            DateFormat df = new SimpleDateFormat();
//            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setTimestamp(7, timestamp);
            ps.setString(8, "A1");
            ps.setTimestamp(9, timestamp);
            ps.setString(10, "A1");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDrink(String id){
        String sql = "delete from Drink where id_clo=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateDrink(Drink d){
        String sql = "update Drink "
                    +"set Category_id=?, Name=?, Path_image=?, Price=?, Status=?, ModifiedAt=?, ModifiedBy=? "
                    +"where Id=?";
        try {
            System.out.println(d);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getCa().getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getPathImage());
            ps.setInt(4, d.getPrice());
            ps.setBoolean(5, d.isStatus());
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateTime);
//            Date da = new Date(System.currentTimeMillis());
//            DateFormat df = new SimpleDateFormat();
//            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setTimestamp(6, timestamp);
            ps.setString(7, "A1");
            ps.setString(8, d.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDrinkNameById(String id) {
        String sql = "SELECT [Id]\n" +
                "      ,[Category_id]\n" +
                "      ,[Name]\n" +
                "      ,[Path_image]\n" +
                "      ,[Price]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Drink] WHERE Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//=======Bill==============================================================
    
    public void addBill(Bill b){
        String sql = "INSERT INTO [dbo].[Bill]\n" +
                "           ([Id]\n" +
                "           ,[TotalPrice]\n" +
                "           ,[CreatedAt]\n" +
                "           ,[CreatedBy])\n" +
                "     VALUES\n" +
                "           (?,?,?,?)";
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

    public List<OrderLine02> getAllOrderlineByBillId(String id){
        String sql = "SELECT [Id]\n" +
                "      ,[Bill_id]\n" +
                "      ,[Drink_id]\n" +
                "      ,[Price]\n" +
                "      ,[Number]\n" +
                "  FROM [dbo].[OrderLine] WHERE Bill_id=?";
        List<OrderLine02> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderLine02 ol = new OrderLine02(
                        rs.getString("Id"),
                        rs.getString("Bill_id"),
                        rs.getString("Drink_id"),
                        rs.getString("Price"),
                        rs.getString("Number"));
                list.add(ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addOrderline(OrderLine o){
        String sql = "INSERT INTO [dbo].[OrderLine]\n" +
                "           ([Bill_id]\n" +
                "           ,[Drink_id]\n" +
                "           ,[Price]\n" +
                "           ,[Number])\n" +
                "     VALUES\n" +
                "           (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, o.getBill().getId());
            ps.setString(2, o.getDrink().getId());
            ps.setInt(3, o.getTotalPrice());
            ps.setInt(4, o.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

// ======= Ingredient =================================================
    
    public Ingredient getIngredientById(String id){
        String sql = "SELECT [Id]\n" +
                "      ,[Name]\n" +
                "      ,[Weight]\n" +
                "      ,[Date]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Ingredient] where Id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Ingredient nl = new Ingredient();
            if(rs.next()){
               nl.setId(rs.getString("Id"));
               nl.setName(rs.getString("Name"));
               nl.setWeight(rs.getInt("Weight"));
               nl.setStatus(rs.getBoolean("Status"));
            }
            return nl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Ingredient> getAllIngredients(){
        ArrayList<Ingredient> listNL = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Name]\n" +
                "      ,[Weight]\n" +
                "      ,[Date]\n" +
                "      ,[Status]\n" +
                "      ,[CreatedAt]\n" +
                "      ,[CreatedBy]\n" +
                "      ,[ModifiedAt]\n" +
                "      ,[ModifiedBy]\n" +
                "  FROM [dbo].[Ingredient]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Ingredient nl = new Ingredient();
                nl.setId(rs.getString("Id"));
                nl.setName(rs.getString("Name"));
                nl.setStatus(rs.getBoolean("Status"));
                nl.setWeight(rs.getInt("Weight"));
                nl.setDate(rs.getDate("Date"));
                listNL.add(nl);
            }
        } catch (Exception e) {     
             e.printStackTrace();
        }
        return listNL;
    }
    
    public void updateIngredient(Ingredient nl){
        String sql = "update Ingredient "
                    +"set Id=?, Name=?, Weight=?, Date=?, Status=?, ModifiedAt=?, ModifiedBy=? "
                    +"where Id=?";
        try {
            System.out.println(nl);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nl.getId());
            ps.setString(2, nl.getName());
            ps.setInt(3, nl.getWeight());
            ps.setDate(4, nl.getDate());
            ps.setBoolean(5, nl.isStatus());
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            ps.setTimestamp(6, timestamp);
            ps.setString(7, "A4");
            ps.setString(8, nl.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addIngredient(Ingredient nl){
        String sql = "INSERT INTO [dbo].[Ingredient]\n" +
                "           ([Id]\n" +
                "           ,[Name]\n" +
                "           ,[Weight]\n" +
                "           ,[Date]\n" +
                "           ,[Status]\n" +
                "           ,[CreatedAt]\n" +
                "           ,[CreatedBy]\n" +
                "           ,[ModifiedAt]\n" +
                "           ,[ModifiedBy])\n" +
                "     VALUES\n" +
                "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nl.getId());
            ps.setString(2, nl.getName());
            ps.setInt(3, nl.getWeight());
            ps.setDate(4, nl.getDate());
            ps.setBoolean(5, nl.isStatus());
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            ps.setTimestamp(6, timestamp);
            ps.setString(7, "A4");
            ps.setTimestamp(8, timestamp);
            ps.setString(9, "A4");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
// ===== Ingredient Per Day ========================================================
    
    public ArrayList<IngredientPerDay> getAllIngredientsPerDay(){
        ArrayList<IngredientPerDay> listNLPD = new ArrayList<>();
        String sql = "SELECT [Id]\n" +
                "      ,[Ingredient_id]\n" +
                "      ,[WeightPD]\n" +
                "  FROM [dbo].[Materials_Use_Per_Day]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                IngredientPerDay nlpd = new IngredientPerDay();
                nlpd.setNl(getIngredientById(rs.getString("Ingredient_id")));
                nlpd.setWeight(rs.getInt("WeightPD"));
                listNLPD.add(nlpd);
            }
        } catch (Exception e) {     
             e.printStackTrace();
        }
        return listNLPD;
    }
    
    public void addIngredientPerDay(IngredientPerDay nlpd){
        String sql = "INSERT INTO [dbo].[Materials_Use_Per_Day]\n" +
                "           ([Ingredient_id]\n" +
                "           ,[WeightPD])\n" +
                "     VALUES\n" +
                "           (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nlpd.getNl().getId());
            ps.setInt(2, nlpd.getWeight());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateIngredientPerDay(IngredientPerDay nlpd){
        String sql = "UPDATE [dbo].[Materials_Use_Per_Day]\n" +
                    "   SET [WeightPD] = ?\n" +
                    " WHERE [Ingredient_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, nlpd.getWeight());
            ps.setString(2, nlpd.getNl().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
