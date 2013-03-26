package smartcampus.webtemplate.manager;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Manager {

	@Autowired
	JdbcTemplate jdbc;

	Logger logger = Logger.getLogger(Manager.class);

	/*
	 * 
	 * create a sample db mysql hackathondb
	 * 
	 * create a user hackathon with password hackathon and grant all permission
	 * to the database hackathondb
	 */
	@PostConstruct
	@SuppressWarnings("unused")
	private void initEnv() throws SQLException {

		try {
			jdbc.execute("CREATE TABLE volunteer ( idvolunteer int(11) NOT NULL AUTO_INCREMENT,"
					+ "fullname varchar(45) DEFAULT NULL, PRIMARY KEY (idvolunteer) )"
					+ " ENGINE=InnoDB DEFAULT CHARSET=latin1");
			logger.info("table created");
		} catch (DataAccessException e) {
			logger.info("table already exists");
		}

		if (jdbc.queryForInt("select count(fullname) from volunteer") == 0) {

			jdbc.execute("INSERT into volunteer (fullname) VALUES('volontario1')");
			jdbc.execute("INSERT into volunteer (fullname) VALUES('volontario2')");
			jdbc.execute("INSERT into volunteer (fullname) VALUES('volontario3')");
		}

	}

	public List<Volunteer> getVolunteers() throws SQLException {
		return jdbc.query("select * from volunteer", new VolunteerMapper());

	}

}