package com.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.ExpoPlanetDocPage;
import com.qa.pages.TestBase;

public class ExpoPlanetDocPageTest extends TestBase {

	ExpoPlanetDocPage expoPlanetDocPage;
	
	
	public ExpoPlanetDocPageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialization("DOC");
		expoPlanetDocPage = new ExpoPlanetDocPage();
		expoPlanetDocPage.selectAllColumns();
	}
	
	@Test(priority=1,enabled=true)
	public void orphanPlanetTest(){
		int count = expoPlanetDocPage.returnOrphanPlanetsCount("2");
		System.out.println("Total number of Orphan planet: " +count);
		Assert.assertNotNull(count);
	}	
	
	@Test(priority=2,enabled=true)
	public void hotStarPlanetNameTest(){
		String planetname = expoPlanetDocPage.returnPlanetNameOrbitingHotStar();
		System.out.println("The name of the planet orbiting hottest Star is : " +planetname);
		Assert.assertNotNull(planetname);
	}	
	
	@Test(priority=3,enabled=true)
	public void timeLineReportPlanetBySizeTest() throws IOException{
		String timelinereport = expoPlanetDocPage.returnTimeLineOfPlanetDiscoveryBySize();
		System.out.println("The timeline report of the planet identified by size is : " +timelinereport);
		Assert.assertNotNull(timelinereport);
	}	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
	
}
