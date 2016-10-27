package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by owlowl on 27.10.16.
 */
public class dbConnectionTest {
	
	@Test
	public void testDBConnection() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=Europe/Moscow");
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select group_id,group_name,group_header,group_footer from group_list");
			Groups groups = new Groups();
			while (rs.next()) {
				groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
						.withFooter(rs.getString("group_footer")).withHeader(rs.getString("group_header")));
			}
			
			System.out.println(groups);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			handleSQLException(ex);
			
		} finally {
			try {
				if(null!=rs){rs.close();}
				if(null!=st){st.close();}
				if(null!=conn){conn.close();}
			} catch (SQLException e) {
				handleSQLException(e);
			}
			
		}
	}
	
	private void handleSQLException(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		System.out.println("SQLState: " + ex.getSQLState());
		System.out.println("VendorError: " + ex.getErrorCode());
	}
	
}
