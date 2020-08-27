package com.karros.automation;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.karros.automation.config.WebdriverHandler;
import com.karros.automation.page.LoginPage;
import com.karros.automation.page.StudentAccessRequestFilterDialog;
import com.karros.automation.page.StudentAccessRequestPage;

public class StudentAccessRequestPageTest {

    private static WebDriver driver;
    
    private static String url = "http://ktvn-test.s3-website.us-east-1.amazonaws.com/";
    private LoginPage loginPage;
    private StudentAccessRequestPage studentAccessRequestPage;
    private StudentAccessRequestFilterDialog studentAccessRequestFilterDialog;
    
    private String email = "admin@test.com";
    private String password = "test123";
    
    @BeforeClass
    public static void setUp() {
        WebdriverHandler.init();
        driver = WebdriverHandler.getWebDriver();
        driver.get(url);
    }
    
    /**
     * Verify filter Student Access Request with INACTIVE
     */
    @Test
    public void testFilterStudentAccessRequestWithInactive() {
        String requestStatusValue = "Inactive";
        String requestStatusColumnName = "Request Status";
        
        loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        
        studentAccessRequestPage = new StudentAccessRequestPage(driver);
        
        studentAccessRequestFilterDialog = studentAccessRequestPage.openStudentAccessRequestFiterDialog();
        studentAccessRequestFilterDialog.waitForDialogOpen();
        
        studentAccessRequestFilterDialog.setRequestStatusByVisileText(requestStatusValue);
        studentAccessRequestFilterDialog.applyFilter();
        
        assertTrue("Result contains value other than " + requestStatusValue + ". Actual Result: " + studentAccessRequestPage.getRowValueByColumn(requestStatusColumnName), 
                studentAccessRequestPage.isDataFilteredEqualsBy(requestStatusColumnName, requestStatusValue));
    }
    
    @AfterClass
    public static void tearDown() {
        WebdriverHandler.quit();
    }
}
