package test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Day2 {

	public WebDriver driver;
	public WebDriverWait wait;
	public String baseURL = "https://www.cstb.ca";	
	String driverPath = "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe";
	String expected;
	String actual;

	public static Logger log = LogManager.getLogger(Day2.class.getName());

	@DataProvider
	public String[][] getData(){
		
		String[][] data = new String[1][3];
		
		data[0][0] = "abc@gmail.com";
		data[0][1] = "abc";
		data[0][2] = "def";
		
		return data;
	}
	
	@BeforeSuite
	public void startTest() {

		log.info("Testing is started");

	}

	@BeforeTest
	public void setBaseURL() {

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();

		driver.get(baseURL); 

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		log.debug("setBaseURL is completed");
	}

	@Test
	public void verifyPartnerProgramTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB® Partner Program')]"))).perform();
		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB® Partner Program Guidelines')]")).click();

		expected = "https://cstb.ca/istqb-partner-program-guidelines";
		actual = driver.getCurrentUrl();

		Assert.assertEquals(actual, expected);
		log.debug("verifyPartnerProgramTab is completed");
	}

	@Test
	public void verifyTrainingProvidersTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(), 'ISTQB® Training Providers')]")).click();

		expected = "https://cstb.ca/accredited-training";
		actual = driver.getCurrentUrl();

		Assert.assertEquals(actual, expected);
		log.debug("verifyTrainingProvidersTab is completed");

	}

	@Test
	public void verifyCertifiedTestersTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(), 'Certified Testers')]")).click();

		expected = "https://cstb.ca/certified-testers";
		actual = driver.getCurrentUrl();

		Assert.assertEquals(actual, expected);
		log.debug("verifyCertifiedTestersTab is completed");

	}

	@Test
	public void screenPartnerProgramGuidelines() {

    		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB® Partner Program')]"))).perform();

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB® Partner Program Guidelines')]")).click();

		//checking all the steps to become partner:

		log.info("Printing all the Steps to become Partner");
		log.info("------------------------------------------");

		List<WebElement> stepsToPartnerList = driver.findElements(By.xpath("//div[@class = 'courses-listing clearfix']/following-sibling::ol//li"));

                int j;
		
		for(int i = 0; i < stepsToPartnerList.size(); i++) {

			j = i + 1;

			log.info(j + ". " + stepsToPartnerList.get(i).getText());

		}

		//Checking program Rules:

		log.info("Printing all the Program Rules: ");
		log.info("------------------------------------------");

		List<WebElement> progRules = driver.findElements(By.xpath("//p[contains(a, 'how the program works')]/following-sibling::ul//li"));

		for(int i = 0; i < progRules.size(); i++) {

			log.info(progRules.get(i).getText());
		}

		log.debug("screenPartnerProgramGuidelines is completed");

	}

	@Test 
	public void screenPartnerProgramProgramRulesLink() {
		
    		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB® Partner Program')]"))).perform();

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB® Partner Program Guidelines')]")).click();

		List<WebElement> progRulesLinks = driver.findElements(By.xpath("//p[contains(a, 'how the program works')]/following-sibling::ul//li//a"));
 
		String[] givenUrl;
		Set<String> winHandle;
		Iterator<String>;
		String parentW;
		String childW;
		String[] currentUrl;
		
		for(int i = 0; i < progRulesLinks.size(); i++) {

			givenUrl = progRulesLinks.get(i).getAttribute("href").split("/");

			progRulesLinks.get(i).click();

			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);  

			//switching to new tab:

			winHandle = driver.getWindowHandles();

			it = winHandle.iterator();

			parentW = it.next();
			childW = it.next();

			driver.switchTo().window(childW);

			currentUrl = driver.getCurrentUrl().split("/"); 

			if (givenUrl[givenUrl.length - 1].equalsIgnoreCase(currentUrl[currentUrl.length - 1])){

				log.debug(currentUrl + " has opened and matched with given URL");
			}
			else {

				log.error(currentUrl + " hasn't opened and didn't match with given URL");
			}

			driver.close();

			driver.switchTo().window(parentW);

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 

		}

		log.debug("screenPartnerProgramProgramRulesLink is completed");
	}

	@Test 
	public void screenPartnerProgramPartnerInCanada() {
		
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB® Partner Program')]"))).perform();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB® Partners in Canada')]")).click();

		String global = driver.findElement(By.xpath("//h2[contains(text(), 'Global')]//following-sibling::p[1]//a")).getAttribute("href");

		String silver = driver.findElement(By.xpath("//h2[contains(text(), 'Silver')]//following-sibling::p[1]//a")).getAttribute("href");

		log.info("Partners are: " + global + " and " + silver);

		log.debug("screenPartnerProgramPartnerInCanada is completed");
	}
	
	@Test 
	public void screenTrainingProviders() {
		
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[contains(text(), 'ISTQB® Training Providers')]")).click();
	    
		log.info("Checking Training Providers List: ");
		
	    	List<WebElement> trainingProviderList = driver.findElements(By.xpath("//p[contains(text(), 'training provider')]//following-sibling::ul//li//a"));
	        
		int j;
		
	    	for (int i = 0; i < trainingProviderList.size(); i++) {
	    	
	    		j = i + 1;
	    	
	    		log.info(j + ". " + trainingProviderList.get(i).getText());
	    	
	    		trainingProviderList.get(i).click();
	    	
	    		driver.navigate().back();
	    	
	    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	
		    	trainingProviderList = driver.findElements(By.xpath("//p[contains(text(), 'training provider')]//following-sibling::ul//li//a"));

	    	}
	    
	    	log.debug("screenTrainingProviders is completed");
	}

	@Test(dataProvider = "getData")
	private void screenHomeEmailSubscriptiong(String email, String fName, String lName) {
		
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a[contains(text(), 'Home')]")).click();
		
		driver.findElement(By.xpath("//input[@value = 'Subscribe']")).click();
	    
	    	Boolean errMsg = driver.findElement(By.xpath("//div[@for = 'mce-EMAIL']")).isDisplayed();
	    
	    	if(errMsg) {
	    	
	    		log.debug("Error message is displayed when email address is not provided");
	    	}  
	    	else {
	    	
	    		log.error("Error message is not displayed when email address is not provided");
	    	}
	    
	    	driver.findElement(By.xpath("//input[@name = 'EMAIL']")).sendKeys(email);
	    	driver.findElement(By.xpath("//input[@name = 'FNAME']")).sendKeys(fName);
	    	driver.findElement(By.xpath("//input[@name = 'LNAME']")).sendKeys(lName);
	    
	    	log.debug("screenHomeEmailSubscriptiong is completed");

	}

	@AfterTest 
	public void closeWindow() {

		driver.close();
		log.debug("Window is closed");
		LogManager.shutdown();
	}

}

