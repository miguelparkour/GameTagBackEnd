package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonData {

	protected static Object getProperty(JsonObject obj, String... fields) {
		if(obj == null) return null;
		
		JsonElement temp = obj.get(fields[0]);
		
		if(temp == null) {
			return null;
		}else if(temp.isJsonPrimitive()) {
			return temp.getAsString();
		}else if(temp.isJsonObject()){
			return fields.length == 2 ? 
					getProperty(temp.getAsJsonObject(), fields[1]) : 
						getProperty(temp.getAsJsonObject(), fields[1], fields[2]);
		}else if(temp.isJsonArray()){
			Iterator<JsonElement> ite = temp.getAsJsonArray().iterator();
			List<String> tempList = new ArrayList<String>();
			while (ite.hasNext()) {
				temp = (JsonElement) ite.next();
				temp = temp.getAsJsonObject().get(fields[1]);
				if (temp.isJsonPrimitive()) {
					tempList.add(temp.getAsString());
				} else if(temp.isJsonObject()){
					tempList.add(temp.getAsJsonObject().get(fields[2]).getAsString());
				}
			}
			return tempList;
		}
		return null; // todos los casos controlados, no debeía llegar a aquí
	}
}
