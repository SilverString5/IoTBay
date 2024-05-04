package unit.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 *
 * @author jijianlan
 */
public class TestRunner {
    
    public static void main(String[] args) {
        
        Result DAOConnection = JUnitCore.runClasses(DAOTest.class);
        Result productDAOTest = JUnitCore.runClasses(ProductDAOTest.class);
        
        for (Failure failure : DAOConnection.getFailures()) {
            System.out.println(failure.toString());
        }
        
        for (Failure failure : productDAOTest.getFailures()) {
            System.out.println(failure.toString());
        }

        String connectionTestStatus = DAOConnection.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status of DAO Connection = " + connectionTestStatus);
        System.out.println(" Number of Tests Passed = " + DAOConnection.getRunCount());
        System.out.println(" Number of Tests Ignored = " + DAOConnection.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + DAOConnection.getFailureCount());
        System.out.println(" Time = " + DAOConnection.getRunTime() / 1000.0 + "s");
        
        String productDAOTeststatus = productDAOTest.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status of productDAOTest = " + productDAOTeststatus);
        System.out.println(" Number of Tests Passed = " + productDAOTest.getRunCount());
        System.out.println(" Number of Tests Ignored = " + productDAOTest.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + productDAOTest.getFailureCount());
        System.out.println(" Time = " + productDAOTest.getRunTime() / 1000.0 + "s");
    }
    
}
