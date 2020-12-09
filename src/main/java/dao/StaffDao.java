package dao;

import vo.Staff;

import java.util.List;

public interface StaffDao {
    public void insert(Staff staff) throws Exception;
    public void delete(Staff staff) throws Exception;
    public void update(Staff staff) throws Exception;
    public Staff queryById(Staff staff) throws Exception;
    public List<Staff> queryAll() throws Exception;
}
