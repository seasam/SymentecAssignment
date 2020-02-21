package com.qa.jsonUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.JSONArray;


public class JsonUtil {

	public static FileWriter file;
	
	public static String jsonloc ="/src/main/java/com" + "/qa/jsonUtil/expoplanets.json";
	
	public static void arrayToJsonConvert(String str) throws IOException, ParseException {
		
		String stringToParse= "{ \"expoplanets\" : "+str+ "}" ;
		
		file = new FileWriter(System.getProperty("user.dir")+ jsonloc);
		
		JSONParser parser = new JSONParser();
		
		JSONObject jsonobj = (JSONObject) parser. parse(stringToParse);
				
		file.write(jsonobj.toJSONString());
		
		file.flush();
		file.close();
		
	}	
	
	
	public static String jsonFileReader() throws IOException {
				
		String data = ""; 
		 try 
	        {
	    data = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ jsonloc))); 
	    System.out.println("DATAAAAA reateder "+data);
		
		     
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return data; 
				
				
	}
	
	
	public static JSONArray jsonIterator(String jsonstr) {
		
				
		org.json.JSONObject outerObject = new org.json.JSONObject(jsonstr);
		//org.json.JSONObject innerObject = outerObject.getJSONObject("expoplanets");		
		 JSONArray jsonArray = outerObject.getJSONArray("expoplanets");
				 
		 
		System.out.println("JSON ARRAY:"+jsonArray);
		
		return jsonArray;
		
	}
	
	
	
	
	 private static void parseExpoPlanetObject(JSONObject planet) 
	    {
	        //Get employee object within list
	        JSONObject planetObject = (JSONObject) planet.get("employee");
	         
	        //Get employee first name
	        String firstName = (String) planetObject.get("firstName");    
	        System.out.println(firstName);
	         
	        //Get employee last name
	        String lastName = (String) planetObject.get("lastName");  
	        System.out.println(lastName);
	         
	        //Get employee website name
	        String website = (String) planetObject.get("website");    
	        System.out.println(website);
	    }
		
	
	
	
	public static void main(String args[]) throws IOException, ParseException {
		
		arrayToJsonConvert("[{\"aaa\":\"aaa\"},{\"bbb\":\"bbb\"}]");
		
	
		jsonIterator(jsonFileReader());
	}
	
			
	
	
}
