package etc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Mysql Statement vs PreparedStatement 속도 벤치마크
 * @author Kyunghun Jeon
 */
public class StatementPerformanceTest {
	
	// TODO : URI 정보
	static final String URI = "someURI";
	static final String ID = null;
	static final String PW = null;
	static final String DB_URI = URI + "?" + "user=" + ID + "&password=" + PW;
	
	static final int TIMES = 100; 
	
	@BeforeClass
	public static void setupBeforeClass() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");	
	}
	
	@Test
	public void StatementTest() {
		
		long startTime = System.currentTimeMillis();
		System.out.println("STATEMENT TEST START");
		
		for(int i=0; i<TIMES; i++) {
			runStatement(i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Time : " + ((endTime-startTime)));
	}
	public void runStatement(int i) {

		Connection c = null;
		Statement stmt = null;
		
		String sqlPrefix = "INSERT INTO SIMPLE_TABLE (NAME, INT_VAL) VALUES";
		String sql = sqlPrefix + "(" + "'name' , " + i + ")";
		
		try {
			c = DriverManager.getConnection(DB_URI);
			stmt = c.createStatement();
			int suc =  stmt.executeUpdate(sql);
			System.out.print(i + " ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		    if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		    if (c != null) try { c.close(); } catch(SQLException ex) {}
		}
		
	}
	
	@Test
	public void preparedStatementWithoutReuseTest() {
		
		long startTime = System.currentTimeMillis();
		System.out.println("PSTMT TEST START");
		
		for(int i=0; i<TIMES; i++) {
			runPreparedStatementWithoutReuseTest(i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Time : " + ((endTime-startTime)));
		
	}
	
	public void runPreparedStatementWithoutReuseTest(int i) {
		
		Connection c = null;
		PreparedStatement pstmt = null;
		
		String sqlPrefix = "INSERT INTO SIMPLE_TABLE (NAME, INT_VAL) VALUES";
		String psql = sqlPrefix + "(? , ?)";
		
		try {
			c = DriverManager.getConnection(DB_URI);
			
			pstmt = c.prepareStatement(psql);
			pstmt.setString(1, "pname");
			pstmt.setInt(2, i);
			
			pstmt.executeUpdate();
			
			System.out.print(i + " ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		    if (c != null) try { c.close(); } catch(SQLException ex) {}
		}
		
	}
	
	@Test
	public void preparedStatementWithReuseTest() {
		
		long startTime = System.currentTimeMillis();
		System.out.println("PSTMT_REUSE TEST START");
		
		
		Connection c = null;
		PreparedStatement pstmt = null;
		
		String sqlPrefix = "INSERT INTO SIMPLE_TABLE (NAME, INT_VAL) VALUES";
		String psql = sqlPrefix + "(? , ?)";
		
		try {
			c = DriverManager.getConnection(DB_URI);
			
			pstmt = c.prepareStatement(psql);
			
			for(int i=0; i<TIMES; i++) {
				pstmt.setString(1, "pname");
				pstmt.setInt(2, i);
				
				pstmt.executeUpdate();
				
				System.out.print(i + " ");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		    if (c != null) try { c.close(); } catch(SQLException ex) {}
		}
		

		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Time : " + ((endTime-startTime)));
		
		
	}
	
	@Test
	public void statementWithReuseTest() {
		
		long startTime = System.currentTimeMillis();
		System.out.println("STMT_REUSE TEST START");
		
		
		Connection c = null;
		Statement stmt = null;
		
		String sqlPrefix = "INSERT INTO SIMPLE_TABLE (NAME, INT_VAL) VALUES";
		
		
		try {
			c = DriverManager.getConnection(DB_URI);
			stmt = c.createStatement();
			
			for(int i=0; i<TIMES; i++) {
				String sql = sqlPrefix + "(" + "'stmt_reuse' , " + i + ")";
				stmt.executeUpdate(sql);
				System.out.print(i + " ");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
			if (c != null) try { c.close(); } catch(SQLException ex) {}
		}
		
		
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Time : " + ((endTime-startTime)));
		
		
	}

}
