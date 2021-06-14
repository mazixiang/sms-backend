package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryUtil;
import vo.Staff;

public class StaffDaoImpl implements StaffDao {
  @Override
  public void insert(Staff staff) throws Exception {
    SessionFactory factory = SessionFactoryUtil.getInstance();
    Session session = factory.openSession();
    Transaction ts = session.beginTransaction();
    session.save(staff);

    ts.commit();
  }

  @Override
  public void delete(Staff staff)throws Exception {
    SessionFactory factory = SessionFactoryUtil.getInstance();
    Session session = factory.openSession();
    Transaction ts = session.beginTransaction();
    session.delete(staff);

    ts.commit();
  }

  @Override
  public void update(Staff staff) throws Exception {
    SessionFactory factory = SessionFactoryUtil.getInstance();
    Session session = factory.openSession();
    Transaction ts = session.beginTransaction();
    session.update(staff);

    ts.commit();
  }

  @Override
  public Staff queryById(Staff staff) throws Exception {
    SessionFactory factory = SessionFactoryUtil.getInstance();
    Session session = factory.openSession();
    Transaction ts = session.beginTransaction();
    Staff res = session.get(Staff.class, staff.getId());

    ts.commit();

    return res;
  }

  @Override
  public List<Staff> queryAll() throws Exception {
    SessionFactory factory = SessionFactoryUtil.getInstance();
    Session session = factory.openSession();
    Transaction ts = session.beginTransaction();
    Query<Staff> query = session.createQuery("from Staff", Staff.class);
    List<Staff> staffListResult = query.list();

    ts.commit();

    return staffListResult;
  }
}
