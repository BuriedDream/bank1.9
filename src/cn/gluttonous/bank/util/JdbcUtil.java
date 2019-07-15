package cn.gluttonous.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 1. 返回连接 2. 关闭
 *
 * @author xfm
 *
 */
public class JdbcUtil {

	// 连接参数
	private static String url = "jdbc:mysql://localhost:3306/bank";

	//private static String url = "jdbc:mysql:///jdbc_demo";
	private static String user = "root";
	private static String password = "";

	/**
	 * 返回连接对象
	 */
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭
	 */
	public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				// 快速异常捕获 Alt + shift + z
				rs.close();
				// 建议垃圾回收期回收资源
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
