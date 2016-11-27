package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import Users.User;


public class LoginService {

	public ContactBean contactBean;
	//存放"用户名：token"键值对
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	//存放"token ： User"键值对
	public static Map<String, User> loginUserMap = new HashMap<String, User>();


	public String login(String username, String password) throws SQLException{

//		//System.out.println(username+"------"+password);
//		String ret = new String();
//		String sql = " select userid,username,password from user where username = '" + username +"' and password = '"+ password + "'";
//		contactBean = new ContactBean();
//		ResultSet resultSet = contactBean.getResult(sql);
//		if(!resultSet.next()){
//			contactBean.CloseConnect();
//			return null;
//		}
//		contactBean.CloseConnect();
//
//		User user = null;
//		String token = tokenMap.get(username);
//		if(token==null){
//			user = new User();
//			user.setUsername(username);
//			user.setPassword(password);
//			System.out.println("new user login");
//		}else{
//			user = loginUserMap.get(token);
//			loginUserMap.remove(token);
//			System.out.println("refresh user ....");
//		}
//		//生成token
//		token = MD5Util.MD5(username+password+new Date().getTime());
//		loginUserMap.put(token, user);
//		tokenMap.put(username, token);
//		System.out.println("目前有:" + tokenMap.size() + "个用户");
//		return token;
		return null;
	}


}