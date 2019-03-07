package helperClasses;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethods {
	
	WebDriver driver;
	
	public SeleniumMethods(WebDriver driver) { 
		this.driver = driver;
	}
	
	public enum Strategies{ 
		XPATH, CSS, ID, NAME, LINKTEXT, CLASS, TAGNAME, PARTLINK
	}

	public void basicWait(int milliSeconds) { 
		try { 
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	
	public void implicitWait(int seconds) { 
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public void waitUntilPresent(WebDriver driver, Strategies strategy, String locator, int timeoutSeconds) { 
		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
		switch(strategy) {
			case CLASS:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
				break;
			case CSS:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
				break;
			case ID:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
				break;
			case LINKTEXT:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
				break;
			case NAME:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
				break;
			case PARTLINK:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
				break;
			case TAGNAME:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
				break;
			case XPATH:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
				break;
			default:
				break; 
		}
		
	}
	
	public WebElement getElement(WebDriver driver, Strategies strategy, String locator) { 
		WebElement element = null;
		try { 
			switch(strategy) { 
				case XPATH:
					element = driver.findElement(By.xpath(locator));
					break;
				case CLASS:
					element = driver.findElement(By.className(locator));
					break;
				case CSS:
					element = driver.findElement(By.cssSelector(locator));
					break;
				case ID:
					element = driver.findElement(By.id(locator));
					break;
				case LINKTEXT:
					element = driver.findElement(By.linkText(locator));
					break;
				case NAME:
					element = driver.findElement(By.name(locator));
					break;
				case PARTLINK:
					element = driver.findElement(By.partialLinkText(locator));
					break;
				case TAGNAME:
					element = driver.findElement(By.tagName(locator));
					break;
				default:
					break; 
				}
			} catch (NoSuchElementException e) { 
				e.printStackTrace();
			} catch (StaleElementReferenceException e) { 
				System.out.println("Element is stale. Repolling after two seconds.");
				basicWait(2000);
				getElement(driver, strategy, locator);	
			}
		return element;
	}
	
	public List<WebElement> getElements(WebDriver driver, Strategies strategy, String locator) { 
		List<WebElement> elements = null;
		try { 
			switch(strategy) { 
				case XPATH:
					elements = driver.findElements(By.xpath(locator));
					break;
				case CLASS:
					elements = driver.findElements(By.className(locator));
					break;
				case CSS:
					elements = driver.findElements(By.cssSelector(locator));
					break;
				case ID:
					elements = driver.findElements(By.id(locator));
					break;
				case LINKTEXT:
					elements = driver.findElements(By.linkText(locator));
					break;
				case NAME:
					elements = driver.findElements(By.name(locator));
					break;
				case PARTLINK:
					elements = driver.findElements(By.partialLinkText(locator));
					break;
				case TAGNAME:
					elements = driver.findElements(By.tagName(locator));
					break;
				default:
					break; 
				}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return elements;
	}
	
	public void clickElement(WebDriver driver, Strategies strategy, String locator) { 
		WebElement element = getElement(driver, strategy, locator);
		element.click();
	}
	
	public void javaScriptClick(WebDriver driver, Strategies strategy, String locator) { 
		WebElement element = getElement(driver, strategy, locator);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
	}
	
	public void type(WebDriver driver, Strategies strategy, String locator, String textToType) { 
		getElement(driver, strategy, locator).sendKeys(textToType);
	}
	
	public String getText(WebDriver driver, Strategies strategy, String locator) { 
		WebElement element = getElement(driver, strategy, locator);
		return element.getText();
	}
	
	public void clearText(WebDriver driver, Strategies strategy, String locator) { 
		getElement(driver, strategy, locator).clear();
	}
	
	public int getNumberOfElementsInList(WebDriver driver, Strategies strategy, String locator) { 
		return getElements(driver, strategy, locator).size();
	}
	
	public boolean isPresent(WebDriver driver, Strategies strategy, String locator) { 
		return getElement(driver, strategy, locator).isDisplayed();
	}
	
	public boolean isSelected(WebDriver driver, Strategies strategy, String locator) { 
		return getElement(driver, strategy, locator).isSelected();
	}
	
	public void maximizeWindow(WebDriver driver) { 
		driver.manage().window().maximize();
	}
	
	public String getTitle(WebDriver driver) { 
		return driver.getTitle();
	}
	
	public void get(WebDriver driver, String url) { 
		driver.get(url);
	}
	
	public void defaultFrame(WebDriver driver) { 
		driver.switchTo().defaultContent();
	}
	
	public void switchToFrame(WebDriver driver, String frameName) { 
		driver.switchTo().frame(frameName);
	}
}
