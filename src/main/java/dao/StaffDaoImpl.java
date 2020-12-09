package dao;

import cn.hutool.core.util.IdUtil;
import vo.Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    public static final String dbHost = "127.0.0.1";
    public static final String dbPort = "3306";
    public static final String dbUsername = "sms_admin";
    public static final String dbName = "StaffManagementSystem";
    public static final String dbPasswd = "NeIgsyVXMLFPYOGZ";

    public static String dbUrl;

    public StaffDaoImpl() {
        dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    }

    private Connection openConnection() throws Exception {
        Class.forName(driverName);
        return DriverManager.getConnection(dbUrl, dbUsername, dbPasswd);
    }

    private Staff newStaffFromResultSet(ResultSet resultSet) throws Exception {
        Staff temp = new Staff();

        temp.setId(resultSet.getString("id"));
        temp.setName(resultSet.getString("name"));
        temp.setPassword(resultSet.getString("password"));
        temp.setGender(resultSet.getString("gender"));
        temp.setAge(resultSet.getInt("age"));
        temp.setContactInfo(resultSet.getString("contactInfo"));
        temp.setHobbies(resultSet.getString("hobbies"));
        temp.setDepartment(resultSet.getString("department"));

        return temp;
    }

    @Override
    public void insert(Staff staff) throws Exception {
        staff.setId(IdUtil.fastSimpleUUID());

        String sql = "insert into staff(id, name, password, gender, age, contactInfo, hobbies, department)" +
                "values(?,?,?,?,?,?,?,?)";

        Connection connection = openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, staff.getId());
        preparedStatement.setString(2, staff.getName());
        preparedStatement.setString(3, staff.getPassword());
        preparedStatement.setString(4, staff.getGender());
        preparedStatement.setInt(5, staff.getAge());
        preparedStatement.setString(6, staff.getContactInfo());
        preparedStatement.setString(7, staff.getHobbies());
        preparedStatement.setString(8, staff.getDepartment());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Staff staff) throws Exception {
        String sql = "delete from staff where id=?";

        Connection connection = openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, staff.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Staff staff) throws Exception {
        String sql = "update staff set name=?, password=?, gender=?, age=?, contactInfo=?, hobbies=?, department=? where id=?";

        Connection connection = openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, staff.getName());
        preparedStatement.setString(2, staff.getPassword());
        preparedStatement.setString(3, staff.getGender());
        preparedStatement.setInt(4, staff.getAge());
        preparedStatement.setString(5, staff.getContactInfo());
        preparedStatement.setString(6, staff.getHobbies());
        preparedStatement.setString(7, staff.getDepartment());
        preparedStatement.setString(8, staff.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public Staff queryById(Staff staff) throws Exception {
        String sql = "select id, name, password, gender, age, contactInfo, hobbies, department from staff where id=?";

        Connection connection = openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, staff.getId());

        ResultSet resultSet = preparedStatement.executeQuery();
        Staff staffResult = null;

        while (resultSet.next()) {
            staffResult = newStaffFromResultSet(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return staffResult;
    }

    @Override
    public List<Staff> queryAll() throws Exception {
        String sql = "select id, name, password, gender, age, contactInfo, hobbies, department from staff";

        Connection connection = openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Staff> staffListResult = new ArrayList<>();

        while (resultSet.next()) {
            Staff temp = newStaffFromResultSet(resultSet);

            staffListResult.add(temp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return staffListResult;
    }
}
