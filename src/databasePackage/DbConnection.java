package databasePackage;

import Entity.ContactInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DbConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:contactinfoapp;create=true");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ContactInfo> findAll() {
        List<ContactInfo> contactInfoList = new ArrayList();
        ContactInfo contactInfo = new ContactInfo();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM CONTACTLIST");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactInfo.setName(resultSet.getString("name"));
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setImageTitle(resultSet.getString("imagetitle"));
                contactInfoList.add(contactInfo);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return contactInfoList;
    }

    public void save(ContactInfo contactInfo) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO CONTACTLIST VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getPhone());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getImageTitle());
            preparedStatement.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public ContactInfo findByName(String name) {
        ContactInfo contactInfo = new ContactInfo();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM CONTACTLIST WHERE NAME = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contactInfo.setName(resultSet.getString("name"));
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setImageTitle(resultSet.getString("imagetitle"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return contactInfo;
    }

    public List<String> findAllName() {
        List<String> names = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT NAME FROM CONTACTLIST ORDER BY NAME");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return names;
    }

    public void delete(String phone) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM CONTACTLIST WHERE phone = ?");
            preparedStatement.setString(1, phone);
            preparedStatement.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void edit(String oldPhoneNumber, ContactInfo contactInfo) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE CONTACTLIST SET NAME=?, PHONE=?, EMAIL=?, ADDRESS=?, IMAGETITLE=? WHERE PHONE = ?");
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getPhone());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getImageTitle());
            preparedStatement.setString(6, oldPhoneNumber);
            preparedStatement.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isPhoneExist(String phone) {
        try {
            preparedStatement = connection.prepareStatement("SELECT PHONE FROM CONTACTLIST WHERE PHONE = ?");
            preparedStatement.setString(1, phone);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return true;
    }

    public boolean isEmailExist(String email) {
        try {
            preparedStatement = connection.prepareStatement("SELECT EMAIL FROM CONTACTLIST WHERE EMAIL = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return true;
    }

    public boolean isNameExist(String name) {
        try {
            preparedStatement = connection.prepareStatement("SELECT EMAIL FROM CONTACTLIST WHERE NAME = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return true;
    }

    public List<String> search(String name) {
        List<String> names = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT NAME FROM CONTACTLIST WHERE LOWER(NAME) LIKE ?");
            preparedStatement.setString(1, "%" + name.toLowerCase() + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    /*  
    These line of code can be used to create table in derby database. you can also use netbeans
    database support to create table in derby database.
    */
//    public void createTable() {
//        DbConnection dbConnection = new DbConnection();
//        try {
//            preparedStatement = connection.prepareStatement("CREATE TABLE CONTACTLIST (name VARCHAR(30) UNIQUE\n" +
//                    ", phone VARCHAR(15) PRIMARY KEY\n" +
//                    ", email VARCHAR(30) UNIQUE\n" +
//                    ", address VARCHAR(30)\n" +
//                    ", imagetitle VARCHAR(500))");
//            preparedStatement.execute();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        new DbConnection().createTable();
//    }
 
}
