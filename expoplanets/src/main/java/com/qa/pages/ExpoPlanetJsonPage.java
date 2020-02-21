package com.qa.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExpoPlanetJsonPage extends TestBase {

	public static String timeReportjsonloc ="/src/main/java/com" + "/qa/pages/TimeReportStmtsjson.txt";
	By jsonloc = By.xpath("/html/body/pre");
	
		
	
	public String returnJsonContent() {
		
		String jstr= findElement(jsonloc).getText();
				
		return jstr;
	}
	
	
public int returnOrphanPlanetsCount(String planettype,String jsonstr) {
		
		int orphanplanetcount=0;	
				
		
		org.json.JSONObject outerObject = new org.json.JSONObject(jsonstr);
		//org.json.JSONObject innerObject = outerObject.getJSONObject("expoplanets");		
		 JSONArray jsonArray = outerObject.getJSONArray("expoplanets");
				 
		 
		System.out.println("JSON ARRAY:"+jsonArray);
		
		for(int i = 0; i < jsonArray .length(); i++)
		{
		   org.json.JSONObject object = jsonArray.getJSONObject(i);
		   
		   String typflag = object.get("TypeFlag").toString();
		 
		   if(typflag.equals(planettype))
		   {
			   orphanplanetcount=orphanplanetcount+1;
		   }
		   
		}
			
		
		return orphanplanetcount;	
	
			
		
	}
	

public String  returnPlanetNameOrbitingHotStar(String jsonstr){
	
	float maxtemp=0;
	String planetname="";
	org.json.JSONObject outerObject = new org.json.JSONObject(jsonstr);
	//org.json.JSONObject innerObject = outerObject.getJSONObject("expoplanets");		
	 JSONArray jsonArray = outerObject.getJSONArray("expoplanets");
	 System.out.println("JSON ARRAY LENGTH  :"+jsonArray.length());
		for(int i = 0; i < jsonArray .length(); i++)
		{
		   org.json.JSONObject object = jsonArray.getJSONObject(i);
		   
		   String startemp = object.get("HostStarTempK").toString();
		     System.out.println("HOT STAR TEMP  :"+startemp);
		     
		     if(startemp.equals("")) {
		    	 continue;
		     }
		     
		     float temprature = Float.valueOf(startemp);
		   if(maxtemp<temprature) {
			   maxtemp=temprature;
			    planetname = object.get("PlanetIdentifier").toString();  
		}              
                         
	
}
		return planetname;
}

public String  returnTimeLineOfPlanetDiscoveryBySize(String jsonstr) throws IOException{
	
	org.json.JSONObject outerObject = new org.json.JSONObject(jsonstr);
	//org.json.JSONObject innerObject = outerObject.getJSONObject("expoplanets");		
	 JSONArray jsonArray = outerObject.getJSONArray("expoplanets");
	  
	 
	 System.out.println("========================================================================");
	 System.out.println("TOtal number of Planet details On list :" + jsonArray.length());
	 
	 int rowsize= jsonArray.length();
	 int colsize=2;
	 

		Set<String> yearlist= new TreeSet<String>();
		Map<String,Integer> smallplanetmap= new TreeMap<String,Integer>();
		Map<String,Integer> mediumplanetmap= new TreeMap<String,Integer>();
		Map<String,Integer> largeplanetmap= new TreeMap<String,Integer>();
		
	 
	 
	 
		for(int i = 0; i < jsonArray .length(); i++)
		{
		   org.json.JSONObject object = jsonArray.getJSONObject(i);
		   
		   String year = object.get("DiscoveryYear").toString();
		   String radi = object.get("RadiusJpt").toString();
		   
		  
		   
		   if(year.equals("")) {
		    	 continue;
		     }
		   if(radi.equals("")) {
		    	 continue;
		     }
		   
		   float planetradi= Float.parseFloat(radi );
		   		   
		   Boolean addstatus =yearlist.add(year);
			if(addstatus.equals("false")) {
				System.out.println("Year"+year+"already exist in the set");
			}	
		   
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
	 
	 
		FileWriter file = new FileWriter(System.getProperty("user.dir")+ timeReportjsonloc);
		
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
			
						
			
			
			 statement=statement +"In "+ year +" we discovered "+smallplanetcount +" small planets, "+mediumplanetcount+" medium planets, and "+largeplanetcount+" large planets."+"\n";
			System.out.println(statement);
						
			
		}
		file.write(statement);
		file.flush();
		file.close();
		
			
		
		
		return statement;
	 
}
	 
	 
	 



	
	
		
	
}
