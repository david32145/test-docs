package testdocs.infra.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao<T> {
	T mapRow(ResultSet resultSet) throws SQLException ;
}
