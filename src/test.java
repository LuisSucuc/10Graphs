// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class test 
{
	public static void main(String[] args) throws Exception 
	{
		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(new FileReader("test.txt"));
		
		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;
		
		
		// DICT
		Map address = ((Map)jo.get("address"));
		
		// DICT ELEMENTS
		Iterator<Map.Entry> itr1 = address.entrySet().iterator();
		while (itr1.hasNext()) {
			Map.Entry pair = itr1.next();
			JSONObject any = new JSONObject((Map) pair.getValue());
                        System.out.println(any.get("type"));
                        System.out.println(any.get("number"));
		}
		
		// LIST
		JSONArray ja = (JSONArray) jo.get("phoneNumbers");
		
		// LIST ELEMTNS
		Iterator itr2 = ja.iterator();
		
		while (itr2.hasNext()) 
		{
			itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				//System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
}
