package abstractFactory;

import abstractFactory.DaoFactory;
import dao.ComputerDao;
import dao.SqlServerComputerDao;
import dao.SqlServerStaffDao;
import dao.StaffDao;

public class SqlServerDaoFactory implements DaoFactory {
    @Override
    public ComputerDao createComputerDao() {
        return new SqlServerComputerDao();
    }

    @Override
    public StaffDao createStaffDao() {
        return new SqlServerStaffDao();
    }
}
