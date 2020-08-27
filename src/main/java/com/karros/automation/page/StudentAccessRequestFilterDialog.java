package com.karros.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentAccessRequestFilterDialog {

    private WebDriver driver;
    private static WebDriverWait wait;
    private String modalFooterXpath = "//div[contains(@class, 'modal-footer')]";
    private String modalCancelBtnXpath = modalFooterXpath + "/button[text() = 'Cancel']";
    
    public StudentAccessRequestFilterDialog(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    private By modalFilter = By.className("modal-filter");
    
    private By requestStatusDropdown = By.id("formControlsSelect");

    private By emailTextField = By.id("formHorizontalEmail");
    
    private By studentIdTextField = By.id("formHorizontalStudentID");
    
    private By studentFirstNameTextField = By.id("formHorizontalStudentFN");
    
    private By studentLastNameTextField= By.id("formHorizontalStudentLN");
    
    private By cancelButton = By.xpath(modalCancelBtnXpath);
    
    private By applyButton = By.className("btn-filter");
    
    public void setRequestStatusByVisileText(String visibleText) {
        Select requestStatusSelect = new Select(driver.findElement(requestStatusDropdown));
        requestStatusSelect.selectByVisibleText(visibleText);
    }
    
    public void setRequestStatusByValue(String value) {
        Select requestStatusSelect = new Select(driver.findElement(requestStatusDropdown));
        requestStatusSelect.selectByValue(value);
    }
    
    public void setEmail(String email) {
        driver.findElement(emailTextField).sendKeys(email);
    }
    
    public void setStudentId(String studentId) {
        driver.findElement(studentIdTextField).sendKeys(studentId);
    }
    
    public void setStudentFirstName(String studentFirstName) {
        driver.findElement(studentFirstNameTextField).sendKeys(studentFirstName);
    }
    
    public void setStudentLarstNameBy(String studentLastName) {
        driver.findElement(studentLastNameTextField).sendKeys(studentLastName);
    }
    
    public StudentAccessRequestPage applyFilter() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(applyButton)).click().perform();
        return new StudentAccessRequestPage(driver);
    }
    
    public StudentAccessRequestPage clickCancel() {
        driver.findElement(cancelButton).click();
        return new StudentAccessRequestPage(driver);
    }
    
    public void waitForDialogOpen() {
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(modalFilter),
                ExpectedConditions.presenceOfElementLocated((applyButton))));
    }
}
