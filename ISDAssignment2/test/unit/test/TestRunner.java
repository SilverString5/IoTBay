package unit.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("- Testing UserDAO: ");
        Result userResult = JUnitCore.runClasses(UserDAOTest.class);
        for (Failure failure : userResult.getFailures()) {
            System.out.println(failure.toString());
        }
        Result DAOConnection = JUnitCore.runClasses(DAOTest.class);
        Result productDAOTest = JUnitCore.runClasses(ProductDAOTest.class);
        
        for (Failure failure : DAOConnection.getFailures()) {
            System.out.println(failure.toString());
        }
        
        for (Failure failure : productDAOTest.getFailures()) {
            System.out.println(failure.toString());
        }

        String userStatus = userResult.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status = " + userStatus);
        System.out.println(" Number of Tests Passed = " + userResult.getRunCount());
        System.out.println(" Number of Tests Ignored = " + userResult.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + userResult.getFailureCount());
        System.out.println(" Time = " + userResult.getRunTime() / 1000.0 + "s");

  System.out.println("- Testing AccessLogDAO: ");
        Result accessLogResult = JUnitCore.runClasses(AccessLogDAOTest.class);
        for (Failure failure : accessLogResult.getFailures()) {
            System.out.println(failure.toString());
        }

        String accessLogStatus = accessLogResult.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status = " + accessLogStatus);
        System.out.println(" Number of Tests Passed = " + accessLogResult.getRunCount());
        System.out.println(" Number of Tests Ignored = " + accessLogResult.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + accessLogResult.getFailureCount());
        System.out.println(" Time = " + accessLogResult.getRunTime() / 1000.0 + "s");


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
        
        
        /* Test Shipment DAO */
        
        System.out.println("- Testing Shipment: ");
        Result result = JUnitCore.runClasses(ShipmentDAOTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        String status = result.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status = " + status);
        System.out.println(" Number of Tests Passed = " + result.getRunCount());
        System.out.println(" Number of Tests Ignored = " + result.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + result.getFailureCount());
        System.out.println(" Time = " + result.getRunTime() / 1000.0 + "s");
        
        //Test OrderDAO
        System.out.println("- Testing Order: ");
        Result orderResult = JUnitCore.runClasses(OrderDAOTest.class);
        for(Failure failure : orderResult.getFailures()){
            System.out.println(failure.toString());
        }
        String orderStatus = orderResult.wasSuccessful() ? "Passed" : "Failed";
        System.out.println(" Test status = " + orderStatus);
        System.out.println(" Number of Tests Passed = " + orderResult.getRunCount());
        System.out.println(" Number of Tests Ignored = " + orderResult.getIgnoreCount());
        System.out.println(" Number of Tests Failed = " + orderResult.getFailureCount());
        System.out.println(" Time = " + orderResult.getRunTime() / 1000.0 + "s");
    }

}
