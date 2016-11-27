package utils;

import java.sql.*;
import java.util.*;


public class ContactBean {

	private static final String DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String URLSTR = "jdbc:mysql://192.168.139.129:3306/mynetsec_news";
	private static final String USER = "root";
	private static final String PWD = "root";
	//数据库连接对象
	private Connection connection = null;
	//创建PreparedStatement对象
	private PreparedStatement preparedStatement = null;
	//创建CallableStatement对象
	private CallableStatement callableStatement = null;
	//创建结果集对象
	private ResultSet resultSet = null;
	//初始化连接

	static {
		try {
			//加载数据库驱动程序
			Class.forName(DRIVER);
		}catch (ClassNotFoundException e){
			System.out.println("加载驱动错误");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 建立数据库连接
	 * @return 数据库连接
	 */
	public Connection getConnection(){

		try {
			connection = DriverManager.getConnection(URLSTR, USER, PWD);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return connection;
	}

	/**
	 * insert update delete SQL语句的执行的统一方法
	 * @param sql SQL语句
	 * @param params 参数数组，若没有参数则为null
	 * @return 受影响的行数
	 *  example:
	 * insert into t_student values (?,?,?)
	 * pstmt.setString(1, "123");
	 * pstmt.setString(2, "小红");
	 * pstmt.setObject(3, "女"); 如果不知道占位符是什么类型的数据就用setObject，jdbc会自动为您转成合适的类型
	 */
	public int executeUpdate(String sql, Object[] params){
		//受影响行数
		int affectedLine = 0;
		try{
			//获取连接
			connection = this.getConnection();
			//调用sql
			preparedStatement = connection.prepareStatement(sql);
			//参数赋值
			if(params != null){
				for (int i = 0; i < params.length; i++){
					preparedStatement.setObject(i + 1, params[i]);
				}
			}
			//执行
			affectedLine = preparedStatement.executeUpdate();

		}catch (SQLException e){
			System.out.println(e.getMessage());
		}finally {
			closeAll();
		}

		return affectedLine;
	}

	/**
	 * SQL 查询将查询结果直接放入ResultSet中
	 * @param sql SQL语句
	 * @param params 参数数组，若没有参数则为null
	 * @return 结果集
	 */
	private ResultSet executeQueryRs(String sql, Object[] params){
		try {
			//获取连接
			connection = this.getConnection();
			//调用sql
			preparedStatement = connection.prepareStatement(sql);
			//参数赋值
			if(params != null){
				for (int i = 0; i < params.length; i++){
					preparedStatement.setObject(i + 1, params[i]);
				}
			}
			//执行
			resultSet = preparedStatement.executeQuery();

		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		return resultSet;
	}

	/**
	 * SQL 查询将查询结果：一行一列
	 * @param sql SQL语句
	 * @param params 参数数组，若没有参数则为null
	 * @return 结果集
	 */

	public Object executeQuerySingle(String sql, Object[] params) {
		Object object = null;
		try {
			// 获得连接
			connection = this.getConnection();

			// 调用SQL
			preparedStatement = connection.prepareStatement(sql);

			// 参数赋值
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i + 1, params[i]);
				}
			}

			// 执行
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				object = resultSet.getObject(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeAll();
		}

		return object;
	}
	/**
	 * 获取结果集，并存放在List中
	 * @return List
	 */
	public List<Object> excuteQuery(String sql, Object[] params){

		ResultSet rs = executeQueryRs(sql, params);
		if(rs == null){
			return null;
		}
		//创建resultSetMetaData对象
		ResultSetMetaData rsmd = null;

		//结果集列数
		int columnCount = 0;
		try{
			rsmd = rs.getMetaData();
			//获取结果集列数
			columnCount = rsmd.getColumnCount();
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		//创建List
		List<Object> list = new ArrayList<>();
		try{
			while (rs.next()){
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++){
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}finally {
			closeAll();
		}

		return list;
	}



	//关闭所有资源
	private void closeAll(){

		//关闭结果集对象
		if(resultSet != null){
			try{
				resultSet.close();
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
		}

		//关闭PreparedStatement对象
		if(preparedStatement != null){
			try{
				preparedStatement.close();
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
		}
		//关闭CallableStatement对象
		if(callableStatement != null){
			try{
				callableStatement.close();
			}catch (SQLException e){
				System.out.print(e.getMessage());
			}
		}

		//关闭connection连接对象
		if(connection != null){
			try {
				connection.close();
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
		}
	}

}