package com.karros.automation.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StudentAccessRequestPage {

    private WebDriver driver;
    
    private By filterBtn = By.className("btn-filter");
    private By tableHeader = By.cssSelector("table.table-header th");
    private By tableRow = By.cssSelector("table.table-body tr");
    
    public StudentAccessRequestPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public StudentAccessRequestFilterDialog openStudentAccessRequestFiterDialog() {
        driver.findElement(filterBtn).click();
        return new StudentAccessRequestFilterDialog(driver);
    }
    
    public Map<String, Integer> getTableHeaderMap() {
        List<WebElement> headerEles = driver.findElements(tableHeader);

        AtomicInteger index = new AtomicInteger();
        return headerEles.stream().collect(
                Collectors.toMap(WebElement::getText, s -> index.getAndIncrement()));
    }
    
    public List<WebElement> getTableRows() {
        return driver.findElements(tableRow);
    }
    
    public List<String> getRowValueByColumn(String columnName) {
        List<String> result = new ArrayList<>();
        
        Map<String, Integer> tableHeaderMap = getTableHeaderMap();
        Integer columnIdx = tableHeaderMap.get(columnName);
        
        List<WebElement> tableRows = getTableRows();
        for (WebElement row : tableRows) {
            List<WebElement> rowValues = row.findElements(By.tagName("td"));
            result.add(rowValues.get(columnIdx).getText());
        }
        
        return result;
    }
    
    public boolean isDataFilteredEqualsBy(String columnName, String filterValue) {
        List<String> rowValues = getRowValueByColumn(columnName);
        return rowValues.stream().allMatch(v -> v.equals(filterValue));
    }
}
