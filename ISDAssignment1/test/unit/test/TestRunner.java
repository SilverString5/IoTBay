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


    }
}
