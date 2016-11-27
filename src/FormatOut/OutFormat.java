package FormatOut;
import java.sql.ResultSet;
import java.util.List;

public interface OutFormat {
	public String OutString(ResultSet result);
	public String OutString(List<Object> list);
}
