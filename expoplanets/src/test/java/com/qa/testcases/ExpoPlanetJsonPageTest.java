package com.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.jsonUtil.JsonUtil;
import com.qa.pages.ExpoPlanetJsonPage;
import com.qa.pages.TestBase;

public class ExpoPlanetJsonPageTest extends TestBase {

	ExpoPlanetJsonPage expoPlanetJsonPage;
	
	
	public ExpoPlanetJsonPageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialization("JSON");
		expoPlanetJsonPage = new ExpoPlanetJsonPage();
		
	}
	
	@Test(priority=1,enabled=true)
	public void orphanPlanetTest() throws IOException, ParseException{
		String arraystr=expoPlanetJsonPage.returnJsonContent();
		System.out.println("THE Array is"+arraystr);
		
		JsonUtil.arrayToJsonConvert(arraystr);
		String jsonstr=JsonUtil.jsonFileReader();
		
		System.out.println("THE Array is jsonSTR"+jsonstr);
		
		
		int count = expoPlanetJsonPage.returnOrphanPlanetsCount("3", jsonstr);
		System.out.println("Total number of Orphan planet: " +count);
		Assert.assertNotNull(count);
	}	
	
	@Test(priority=2,enabled=true)
	public void hotStarPlanetNameTest() throws IOException, ParseException{
		String arraystr=expoPlanetJsonPage.returnJsonContent();
		JsonUtil.arrayToJsonConvert(arraystr);
		String jsonstr=JsonUtil.jsonFileReader();			
		
		String planetname = expoPlanetJsonPage.returnPlanetNameOrbitingHotStar(jsonstr);
		System.out.println("The name of the planet orbiting hottest Star is : " +planetname);
		Assert.assertNotNull(planetname);
	}	
	
	@Test(priority=3,enabled=true)
	public void timeLineReportPlanetBySizeTest() throws IOException, ParseException{
		String arraystr=expoPlanetJsonPage.returnJsonContent();
		JsonUtil.arrayToJsonConvert(arraystr);
		String jsonstr=JsonUtil.jsonFileReader();		
		
		
		String timelinereport = expoPlanetJsonPage.returnTimeLineOfPlanetDiscoveryBySize(jsonstr);
		System.out.println("The timeline report of the planet identified by size is : " +timelinereport);
		Assert.assertNotNull(timelinereport);
	}	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
	
}
