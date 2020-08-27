package com.karros.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    
    private By emailField = By.id("formHorizontalEmail");
    
    private By passwordField = By.id("formHorizontalPassword");
    
    private By loginBtn = By.className("col-login__btn");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
    
    public void login(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
        this.clickLoginBtn();
    }
}
