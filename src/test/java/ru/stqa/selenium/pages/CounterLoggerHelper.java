package ru.stqa.selenium.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Sample page
 */
public class CounterLoggerHelper extends PageBase {

  @FindBy(xpath = "//*[@class = 'lead']")
  WebElement header;

  @FindBy(id = "counter_input")
  WebElement inputCounterField;

  @FindBy(id = "increase_btn")
  WebElement plusButton;

  @FindBy(id = "decrease_btn")
  WebElement minusButton;

  @FindBy(id = "//input[@class='form-control mt-2']")
  WebElement clearButton;

  @FindBy(xpath = "//*[@class = 'logger-wrapper']/li")
  List<WebElement> listLogRecords;

  public CounterLoggerHelper(WebDriver webDriver) {
    super(webDriver);
  }

  public CounterLoggerHelper waitUntilPageIsLoaded(){
    waitUntilElementIsVisible(header,20);
    waitUntilElementIsClickable(plusButton,15);
    waitUntilElementIsClickable(minusButton,15);
    return this;
  }

  public CounterLoggerHelper increaseValueUsingPlus() {
    plusButton.click();
    return this;
  }

  public CounterLoggerHelper decreaseValueUsingMinus() {
    minusButton.click();
    return this;
  }

  public String getCounterValue() {
    return inputCounterField.getAttribute("ng-reflect-model");
  }

  public CounterLoggerHelper increaseValueUsingUpArrowKey() {
    inputCounterField.click();
    inputCounterField.sendKeys(Keys.ARROW_UP);
    return this;
  }

  public CounterLoggerHelper enterCounterByHands(String counter) {
    inputCounterField.click();
    inputCounterField.clear();
    inputCounterField.sendKeys(counter);
    return this;
  }

  public CounterLoggerHelper decreaseValueUsingDownArrow() {
    inputCounterField.click();
    inputCounterField.sendKeys(Keys.ARROW_DOWN);
    return this;
  }

  public int getQuantityLogRecords() {
    return  listLogRecords.size();
  }

  public boolean lastLogRecordVerrification(String number, String time) {
    boolean isCorrect = true;
    int sizeLog = listLogRecords.size();
    WebElement lastRecord = listLogRecords.get(sizeLog-1);
    if(lastRecord.getText().equals(" Clicked at " + time + ", with value " + number + " "));
    return isCorrect;
  }

  public CounterLoggerHelper clearLog() {
    inputCounterField.click();
    Robot r = null;
    try {
      r = new Robot();
      r.keyPress(KeyEvent.VK_TAB);
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      r.keyPress(KeyEvent.VK_ENTER);
      driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    } catch (AWTException e) {
      e.printStackTrace();
    }
    waitUntilAttributeValueIs(inputCounterField,"ng-reflect-model","0",5);
    return this;
  }
}
