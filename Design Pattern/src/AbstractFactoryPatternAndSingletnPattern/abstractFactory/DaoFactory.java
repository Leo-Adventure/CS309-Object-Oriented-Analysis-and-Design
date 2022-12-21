package abstractFactory;

import dao.ComputerDao;
import dao.StaffDao;

public interface DaoFactory {
    ComputerDao createComputerDao();
    StaffDao createStaffDao();
}
