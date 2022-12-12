package Singleton;

import abstractFactory.DaoFactory;
import dao.ComputerDao;
import dao.StaffDao;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {
    private static DaoFactoryImpl instance = null;
    private final Class<?> staffDaoClass;
    private final Class<?> computerDaoClass;

    public static synchronized DaoFactoryImpl getInstance(){
        if(instance == null){
            instance = new DaoFactoryImpl();
        }
        return instance;
    }

    private DaoFactoryImpl(){
        Properties properties = PropertiesReader.readProperties("/Users/leo/Downloads/src/Singleton/resources.properties");
        try {
            staffDaoClass = Class.forName("dao." + properties.getProperty("StaffDao"));
            computerDaoClass = Class.forName("dao." + properties.getProperty("ComputerDao"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public StaffDao createStaffDao() {

        try {
            return (StaffDao) staffDaoClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ComputerDao createComputerDao() {
        try {
            return (ComputerDao) computerDaoClass.getDeclaredConstructor().newInstance();
        }catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
