package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Abstract class representation of a PageBase in the UI. PageBase object pattern
 */
public abstract class PageBase {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public PageBase(WebDriver driver) {
    this.driver = driver;
  }

  public String getTitle() {
    return driver.getTitle();
  }
  public void waitUntilElementIsClickable(By locator, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .elementToBeClickable(locator));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void waitUntilElementIsClickable(WebElement element, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .elementToBeClickable(element));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void waitUntilAttributeValueIs(By locator, String attribute, String value, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .attributeToBe(locator, attribute, value));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilAttributeValueIs(WebElement element, String attribute, String value, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .attributeToBe(element, attribute, value));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilTextValueIs(WebElement element, String text, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .textToBePresentInElement(element, text));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilElementIsVisible(By locator, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .visibilityOfElementLocated(locator));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilElementIsVisible(WebElement element, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .visibilityOf(element));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilElementIsNotVisible(By locator, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .invisibilityOfElementLocated(locator));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilAllElementsAreVisible(By locator, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .visibilityOfAllElementsLocatedBy(locator));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilAllElementsAreVisible(List<WebElement> elementList, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .visibilityOfAllElements(elementList));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilFrameIsLoadedAndSwitchToIt(WebElement frame, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .frameToBeAvailableAndSwitchToIt(frame));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilNumberOfWindows(int number, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions
              .numberOfWindowsToBe(number));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String currentTimeValue() {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      return dtf.format(now);
    }

}
