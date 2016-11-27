package FormatOut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class StringOutFormat implements OutFormat {

	@Override
	public String OutString(ResultSet result) {
		// TODO Auto-generated method stub
		try {
			String ret = new String();
			
			while(result.next()){
				ret = result.getString("news_content");
			}
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String OutString(List<Object> list) {
		String news_content = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(list != null){
			map = (HashMap<String, Object>)list.get(0);
			for (String key : map.keySet()) {
				news_content = map.get(key).toString();
			}
		}
		return news_content;
	}
}
