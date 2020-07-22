package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.selenium.pages.CounterLoggerHelper;
import ru.stqa.selenium.util.DataProviders;

public class CounterLoggerTests extends TestBase {

  private CounterLoggerHelper homepage;
  int recordsInLog;
  String time;
  double counterValue;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, CounterLoggerHelper.class);
    homepage.waitUntilPageIsLoaded();
    time = homepage.currentTimeValue();

  }

  @Test
  public void openApplicationTest() {
    Assert.assertEquals(homepage.getTitle(), "QaAutomationTask");
  }


  @Test(dataProviderClass = DataProviders.class, dataProvider = "dpIncreaseValue")
  public void increaseValueUsingPlusTest(String inputCounter, String outputCounter) {
    homepage.enterCounterByHands(inputCounter);
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.increaseValueUsingPlus()
            .currentTimeValue();
    Assert.assertTrue(homepage.lastLogRecordVerrification(outputCounter, time));
    Assert.assertEquals(outputCounter, homepage.getCounterValue());
    Assert.assertEquals(recordsInLog + 1, homepage.getQuantityLogRecords());
  }

  @Test(dataProviderClass = DataProviders.class, dataProvider = "dpIncreaseValue")
  public void increaseValueUsingUpArrowKeyTest(String inputCounter, String outputCounter) {
    homepage.enterCounterByHands(inputCounter);
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.increaseValueUsingUpArrowKey();
    Assert.assertTrue(homepage.lastLogRecordVerrification(outputCounter, time));
    Assert.assertEquals(outputCounter, homepage.getCounterValue());
    Assert.assertEquals(recordsInLog + 1, homepage.getQuantityLogRecords());
  }

  @Test(dataProviderClass = DataProviders.class, dataProvider = "dpDecreaseValue")
  public void decreaseValueUsingMinusTest(String inputCounter, String outputCounter) {
    homepage.enterCounterByHands(inputCounter);
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.decreaseValueUsingMinus();
    Assert.assertTrue(homepage.lastLogRecordVerrification(outputCounter, time));
    Assert.assertEquals(outputCounter, homepage.getCounterValue());
    Assert.assertEquals(recordsInLog + 1, homepage.getQuantityLogRecords());
  }

  @Test(dataProviderClass = DataProviders.class, dataProvider = "dpDecreaseValue")
  public void decreaseValueUsingDownArrowTest(String inputCounter, String outputCounter) {
    homepage.enterCounterByHands(inputCounter);
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.decreaseValueUsingDownArrow();
    Assert.assertTrue(homepage.lastLogRecordVerrification(outputCounter, time));
    Assert.assertEquals(outputCounter, homepage.getCounterValue());
    Assert.assertEquals(recordsInLog + 1, homepage.getQuantityLogRecords());
  }

  @Test
  public void oneClickClearButtonTest() {
    homepage.increaseValueUsingPlus()
            .increaseValueUsingPlus()
            .decreaseValueUsingMinus()
            .increaseValueUsingUpArrowKey();
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.clearLog();
    time = homepage.currentTimeValue();
    Assert.assertTrue(homepage.lastLogRecordVerrification("0", time));
    Assert.assertEquals("0", homepage.getCounterValue());
    Assert.assertEquals(1, homepage.getQuantityLogRecords());
  }

  @Test
  public void twiceClickClearButtonTest() {
    homepage.increaseValueUsingPlus()
            .increaseValueUsingUpArrowKey()
            .increaseValueUsingUpArrowKey()
            .increaseValueUsingPlus()
            .decreaseValueUsingMinus()
            .increaseValueUsingUpArrowKey();
    recordsInLog = homepage.getQuantityLogRecords();
    homepage.clearLog()
            .clearLog();
    time = homepage.currentTimeValue();
    Assert.assertTrue(homepage.lastLogRecordVerrification("0", time));
    Assert.assertEquals("0", homepage.getCounterValue());
    Assert.assertEquals("1", homepage.getQuantityLogRecords());
  }
}