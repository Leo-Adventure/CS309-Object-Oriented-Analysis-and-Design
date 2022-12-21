package abstractFactory;

import abstractFactory.DaoFactory;
import dao.ComputerDao;
import dao.MysqlComputerDao;
import dao.MysqlStaffDao;
import dao.StaffDao;

public class MysqlDaoFactory implements DaoFactory {
    @Override
    public ComputerDao createComputerDao(){
        return new MysqlComputerDao();
    }
    @Override
    public StaffDao createStaffDao(){
        return new MysqlStaffDao();
    }
}
