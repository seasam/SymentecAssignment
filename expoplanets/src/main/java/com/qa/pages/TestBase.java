package com.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class TestBase {
	
	public  static WebDriver driver;
	public static Properties prop;
	
	public static int PAGE_LOAD_TIMEOUT=10;
	public static int IMPLICIT_WAIT=10;
	
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(String testtype){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
						
			
			
			WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver();
		}
		else if(browserName.equals("FF")){
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver(); 
		}
						
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		if(testtype.equals("JSON")) {
		
		driver.get(prop.getProperty("url_json"));
		
		}
		else if(testtype.equals("DOC")) {
			driver.get(prop.getProperty("url_doc"));
			
		}
		
	}
	
	
	public WebElement findElement(By elementlocator) {
		
	return driver.findElement(elementlocator);
	}
	
	
	
	
	
	
	
	

}
