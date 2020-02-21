package com.qa.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ExpoPlanetDocPage extends TestBase{

	public static String timeReportloc ="/src/main/java/com" + "/qa/pages/TimeReportStmts.txt";
	
	By tableloc = By.xpath("//table[@class='DataTable_Table-sc-rjxc30 gMUozE']");
	
	By colselectloc = By.xpath("//div[@class='DataExplorerPreview_TitleBarRight-sc-q5ilfx dlhBfn']/span[1]");

    By selectAllloc = By.xpath("//*[@id=\"site-content\"]//div[@class='ColumnSelector_QuickSelectButton-sc-lslrkz fDMfyA' and text()='Select All']");	
	
    By applyloc =By.xpath("//*[@id=\"site-content\"]//a/div[@class='button button--small']/span[text()='Apply']");
    
  //*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/div/span[1]/text()
    
  //*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/div/span[1]/span
    
    //html/body/main/div[1]/div/div[5]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/div/span[1]
    
    //html/body/main/div[1]/div/div[5]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/div/span[1]/text()
    
    
    
    
    public void selectAllColumns()  {
    	
    	
    	WebElement element = findElement(colselectloc);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    	
    	//findElement(colselectloc).click();
    	findElement(selectAllloc).click();
    	findElement(applyloc).click();
    		
    	
    	
		
	}
	
	
	public WebElement returnDataTable() {
						
		return findElement(tableloc);
	}
	
	public int returnOrphanPlanetsCount(String planettype) {
		
		int orphanplanetcount=0;
		
		String TYPE_FLAG_XPATH="//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr/td[3]";
		
		
		List<WebElement> flg_typ_list= driver.findElements(By.xpath(TYPE_FLAG_XPATH));
		
		for(WebElement flgtype :flg_typ_list) {
			
			
			if(flgtype.getText().equals(planettype))			
			{
				orphanplanetcount++;
				
			}
		}
				
		return orphanplanetcount;
	}
	

	////*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[24]
	
	
	
	
	public String  returnPlanetNameOrbitingHotStar(){
		
		float maxtemp=0;
		
		String STAR_TEMP_XPATH="//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr/td[21]";
		
		//html/body/main/div[1]/div/div[5]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[14]/td[5]
		
		//*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[24]
		
		//*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[22]
		//*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[21]
		
		//*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[644]/td[2]
		
		List<WebElement> hot_star_temp_list= driver.findElements(By.xpath(STAR_TEMP_XPATH));
		
		System.out.println("zzzzzzzzzzzzzzzz size of list"+ hot_star_temp_list.size());
		
                 for(WebElement startemp :hot_star_temp_list) {
			
                	 if(startemp.getText().equals("")) {
                		 continue;
                	 }
                	 
                	     float temp = Float.parseFloat(startemp.getText());
                	 
                	     if(temp>maxtemp) {
                	    	 
                	    	 maxtemp=temp;
                	     }                	              	 
                	 
		}
		
                 String maxtempstr= String.valueOf(maxtemp);
                 
                 String HOT_STAR_PLANET_XPATH ="//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr/td[21][text()=" +maxtempstr+
                		 
                		 "]/preceding-sibling::td[19]";	                            
                 
                 
		return findElement(By.xpath(HOT_STAR_PLANET_XPATH)).getText();
	}
	
	
	public String  returnTimeLineOfPlanetDiscoveryBySize() throws IOException{
		
		String  DISCOVERY_TIME_XPATH="//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr/td[16]";
		
		List<WebElement> discovery_time_list= driver.findElements(By.xpath(DISCOVERY_TIME_XPATH));
		
		Set<String> yearlist= new TreeSet<String>();
		
		for(WebElement discoverytime: discovery_time_list) {
			
		Boolean addstatus =yearlist.add(discoverytime.getText());
			if(addstatus.equals("false")) {
				System.out.println("Year"+discoverytime.getText()+"already exist in the set");
			}	
			
		}
		
		Map<String,Integer> smallplanetmap= new TreeMap<String,Integer>();
		Map<String,Integer> mediumplanetmap= new TreeMap<String,Integer>();
		Map<String,Integer> largeplanetmap= new TreeMap<String,Integer>();
		
		int rowsize = driver.findElements(By.xpath("//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr/td[1]")).size();
		int colsize =2;
		
		Object[][] datacontent = new Object[rowsize][colsize];
		
		for(int i =0; i<rowsize;i++) {
			float planetradi;
						
				datacontent[i][0]=findElement(By.xpath("//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr["+(i+1)+"]/td[16]")).getText();
				datacontent[i][1]= findElement(By.xpath("//table[@class='DataTable_Table-sc-rjxc30 gMUozE']/tbody/tr["+(i+1)+"]/td[5]")).getText();
								
				if(datacontent[i][0].equals("")) {
					continue;
				}
				
				if(datacontent[i][1].equals("")) {
					planetradi=0;
				}else {
					 planetradi= Float.parseFloat( datacontent[i][1].toString());
				}
				
				
				//*[@id="site-content"]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/table/tbody/tr[3]/td[5]
				              
								String year= datacontent[i][0].toString();
								
				
		                 if (planetradi<1) {
		                	 
		                	 if(smallplanetmap.containsKey(year)) {
		                		 smallplanetmap.put(year,smallplanetmap.get(year)+1 );
		                	 }else
		                	 {
		                		 smallplanetmap.put(year,1);
		                	 }	                	 
		                	 
		                 }
		                 else if(planetradi>1 & planetradi<2 ) {
		                	
		                	 if(mediumplanetmap.containsKey(year)) {
		                		 mediumplanetmap.put(year,mediumplanetmap.get(year)+1 );
		                	 }else
		                	 {
		                		 mediumplanetmap.put(year,1);
		                	 }	
		                	 
		                	 
		                	 
		                 }else if(planetradi>2 ) {
		                	 
		                	 if(largeplanetmap.containsKey(year)) {
		                		 largeplanetmap.put(year,largeplanetmap.get(year)+1 );
		                	 }else
		                	 {
		                		 largeplanetmap.put(year,1);
		                	 }	
		                	 		                	 
		                	 
		                 }
		
		
		}
			
		
		FileWriter file = new FileWriter(System.getProperty("user.dir")+ timeReportloc);
		
		String statement="";
		for(String year : yearlist) {
			
			int smallplanetcount=0;
			int mediumplanetcount=0;
			int largeplanetcount=0;
			
			try {
			smallplanetcount=smallplanetmap.get(year);
			}catch(NullPointerException e) {
				smallplanetcount=0;
				e.printStackTrace();
			}
			
			try {
				mediumplanetcount=mediumplanetmap.get(year);
				}catch(NullPointerException e) {
					mediumplanetcount=0;
					e.printStackTrace();
				}
			
			try {
				largeplanetcount=largeplanetmap.get(year);
				}catch(NullPointerException e) {
					largeplanetcount=0;
					e.printStackTrace();
				}
			
			
			
			
			 statement=statement +"in "+ year +"we discovered "+smallplanetcount+" small planets, "+mediumplanetcount+" medium planets, and "+largeplanetcount+" large planets."+"\n";
			System.out.println(statement);
						
			
		}
		file.write(statement);
		file.flush();
		file.close();
		
			
		
		
		return statement;
	}
	
	
	
	
	
	
}
