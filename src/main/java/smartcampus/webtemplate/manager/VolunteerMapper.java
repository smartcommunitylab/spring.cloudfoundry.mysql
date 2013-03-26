package smartcampus.webtemplate.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VolunteerMapper implements RowMapper<Volunteer> {
	@Override
	public Volunteer mapRow(ResultSet arg0, int arg1) throws SQLException {
		Volunteer v = new Volunteer();
		v.setId("" + arg0.getInt(1));
		v.setFullname(arg0.getString(2));
		return v;
	}
}
