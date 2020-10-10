package testdocs.infra.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import testdocs.domain.entities.User;
import testdocs.infra.JDBCPostgresConnection;

public class UserDao implements Dao<User> {
	private final static String INSERT_SQL = "insert into users (email, password_hash, name, office, description, avatar_uri) values (?, ?, ?, ?, ?, ?)";
	private final static String SELECT_BY_EMAIL_SQL = "select * from users where email = ?";
	private final static String SELECT_BY_ID_SQL = "select * from users where id = ?";
	private final static String SELECT_USERS_MEMBERS_BY_PROJECT_ID = "select distinct users.id, users.email, users.password_hash, users.name, users.office, users.description, users.avatar_uri from users left join project_members on project_members.user_id = users.id where project_members.project_id = ? UNION select users.id, users.email, users.password_hash, users.name, users.office, users.description, users.avatar_uri from users inner join projects on users.id = projects.owner_id where projects.id = ?;";
	private final static String SELECT_ALL_BY_IDS = "select * from users where id in (%s);";
	
	private Connection connection;
	
	private static UserDao instace;
	
	public static UserDao getInstace() throws ClassNotFoundException, SQLException {
		if(instace == null) {
			instace = new UserDao();
		}
		return instace;
	}

	public UserDao() throws ClassNotFoundException, SQLException {
		this.connection = JDBCPostgresConnection.getConnection();
	}

	@Override
	public User mapRow(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong(1);
		String email = resultSet.getString(2);
		String passwordHash = resultSet.getString(3);
		String name = resultSet.getString(4);
		String office = resultSet.getString(5);
		String description = resultSet.getString(6);
		String avatarUri = resultSet.getString(7);
		return new User(id, email, passwordHash, name, office, description, avatarUri);
	}
	
	public User save(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPasswordHash());
		statement.setString(3, user.getName());
		statement.setString(4, user.getOffice());
		statement.setString(5, user.getDescription());
		statement.setString(6, user.getAvatarUri());
		boolean successInOperation = statement.executeUpdate() > 0;
		if(successInOperation) {
			ResultSet generatedKeysResult = statement.getGeneratedKeys();
			generatedKeysResult.next();
			long userId = generatedKeysResult.getLong(1);
			return new User(userId, user.getEmail(), user.getPasswordHash(), user.getName(), user.getOffice(), user.getDescription(), user.getAvatarUri());
		}
		return null;
	}
	
	public User findByEmail(String email) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_SQL);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(!resultSet.next()) return null;
		return mapRow(resultSet);
	}
	
	public User findById(Long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(!resultSet.next()) return null;
		return mapRow(resultSet);
	}
	
	public List<User> findUsersByProjectId(long projectId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_MEMBERS_BY_PROJECT_ID);
		preparedStatement.setLong(1, projectId);
		preparedStatement.setLong(2, projectId);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<User> users = new ArrayList<User>();
		while(resultSet.next()) {
			users.add(mapRow(resultSet));
		}
		return users;
	}
	
	public List<User> findByIds(String[] ids) throws SQLException {
		String inQuery = String.join(", ", ids);
		PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_ALL_BY_IDS, inQuery));
		ResultSet resultSet = preparedStatement.executeQuery();
		List<User> users = new ArrayList<User>();
		while(resultSet.next()) {
			users.add(mapRow(resultSet));
		}
		return users;
	}
}
