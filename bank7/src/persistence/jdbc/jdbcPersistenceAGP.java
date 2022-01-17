package persistence.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PersistenceAGP;

public class jdbcPersistenceAGP implements PersistenceAGP{


	public int QuerySite(String keyWord) {
		
		String selectAddressQuery = "SELECT * FROM Site WHERE idSite = ?";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);
			preparedStatement.setString(1, keyWord);
			ResultSet result = preparedStatement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	

	}

	
}
