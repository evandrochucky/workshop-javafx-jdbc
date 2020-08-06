package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertData {

	public static void insertSeller(Connection conn, String name, String email, String birthDate, Double baseSalary, int department) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		PreparedStatement st = null;
		ResultSet rs = null;
				
		try{
	 
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, departmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)", 
					+ Statement.RETURN_GENERATED_KEYS);
			
//			st.setString(1, "Maria Silva");
//			st.setString(2, "maria@gmail.com");
//			st.setDate(3, new java.sql.Date(sdf.parse("10/10/1980").getTime()));
//			st.setDouble(4, 4252.0);
//			st.setInt(5, 2);
			
			st.setString(1, name);
			st.setString(2, email);
			st.setDate(3, new java.sql.Date(sdf.parse(birthDate).getTime()));
			st.setDouble(4, baseSalary);
			st.setInt(5, department);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done. Seller inserted! Rows Affected: " + rowsAffected);
		
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No Rows Affected!");
			}
	
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	public static void insertDepartment(Connection conn, String depName1, String depName2) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
				
		try{
			
//			st = conn.prepareStatement(
//					"INSERT INTO department (Name) VALUES ('Collection'), ('Marketing')",
//					+ Statement.RETURN_GENERATED_KEYS);

			st = conn.prepareStatement(
					"INSERT INTO department (Name) VALUES ('" + depName1 + "'), ('" + depName2 + "')",
					+ Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done. Department inserted! Rows Affected: " + rowsAffected);
		
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No Rows Affected!");
			}
	
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
}
