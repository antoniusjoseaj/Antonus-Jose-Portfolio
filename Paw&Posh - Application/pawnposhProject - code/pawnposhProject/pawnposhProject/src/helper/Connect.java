package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "pawnsposh";
	private final String HOST = "localhost:3310";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	
		private Connection con;
		private Statement st;
		public ResultSet rs;
		public java.sql.ResultSetMetaData rsm;
		
		private static Connect connect;
		
		public static Connect getInstance() {
			if (connect == null) {
				return new Connect();
			}
			return connect;
		}
		
		private Connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(CONNECTION,USERNAME,PASSWORD);
				st= con.createStatement();
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		
		public ResultSet execQuery(String query) {
			try {
				rs =st.executeQuery(query);
				rsm = rs.getMetaData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
		
		public void execUpdate(String query) {
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
		    return con;
		}
	}
	
	//DESIGN PATTERN -> SINGLETON:MASTIIN CONNECTION KE DB CM 1 KALI
	
//	private static Connect instance = null;
//	private Connection con;
//	
//	//buat ngambil dan cek koneksi udh ada blm
//	public static Connect getInstance() {
//		if (instance == null) {
//			instance = new Connect();
//			//kl ada bkl bikin koneksi baru
//		}
//		return instance;
//		//kl udh bakal return
//	}
//	
//	private Connect() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			 con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}