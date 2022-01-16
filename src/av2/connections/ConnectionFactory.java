package av2.connections;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private final String URL ="jdbc:mysql://localhost/avaliacao2?useTimezone=true&serverTimezone=UTC";

	private final String ID ="root";

	private final String PASSWORD ="1234";

	public DataSource dataSource;

	
	public ConnectionFactory () {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

		comboPooledDataSource.setJdbcUrl(URL);
		comboPooledDataSource.setUser(ID);
		comboPooledDataSource.setPassword(PASSWORD);
		
		comboPooledDataSource.setMaxPoolSize(1000);

		dataSource = comboPooledDataSource;

	}


	public Connection getConnection () throws SQLException {

		return dataSource.getConnection();

	}


}
