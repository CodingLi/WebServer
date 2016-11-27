package FormatOut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonOutFormat implements OutFormat {

	public String OutString( ResultSet result) {
		// TODO Auto-generated method stub
		try {
			JSONArray jsonArray = new JSONArray();

			while(result.next()){

				JSONObject jsonObjectTemp = new JSONObject();
				jsonObjectTemp.put("news_id", result.getString("news_id"));
				jsonObjectTemp.put("newstype_id", result.getString("newstype_id"));
				jsonObjectTemp.put("title", result.getString("title"));
				jsonObjectTemp.put("digest", result.getString("digest"));
				jsonObjectTemp.put("source", result.getString("source"));
				jsonObjectTemp.put("ptime", result.getString("ptime"));
				jsonObjectTemp.put("insert_time", result.getString("insert_time"));
				jsonObjectTemp.put("image_link", result.getString("image_link"));

				jsonArray.add(jsonObjectTemp);
			}

			return jsonArray.toString();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public String OutString(List<Object> list){

		JSONArray jsonArray = new JSONArray();

		JSONObject jsonObjectTemp = new JSONObject();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(list != null){
			for (int i = 0; i < list.size(); i++){
				map.clear();
				map = (HashMap<String, Object>)list.get(i);
				for (Map.Entry<String, Object> entry : map.entrySet()){
					jsonObjectTemp.put(entry.getKey(), entry.getValue().toString());
				}
				jsonArray.add(jsonObjectTemp);
			}
		}
		return jsonArray.toString();
	}

}
