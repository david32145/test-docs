package testdocs.infra.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import testdocs.domain.entities.Project;
import testdocs.domain.entities.User;
import testdocs.infra.JDBCPostgresConnection;

public class ProjectDao implements Dao<Project> {
	private static final String SELECT_ALL_BY_USER_ID_SQL = "select distinct projects.id, projects.name, projects.description, projects.github_uri, users.name, projects.image_uri from projects inner join users on projects.owner_id = users.id where users.id = ?";
	private final static String SELECT_BY_ID = "select * from projects where id = ?";
	private final static String INSERT_SQL = "insert into projects (name, description, github_uri, owner_id, image_uri) values (?, ?, ?, ?, ?)";
	private final static String UPDATE_SQL = "update projects set name = ?, description = ?, github_uri = ?, image_uri = ? where id = ?";
	private final static String INSERT_MEMBERS_SQL = "insert into project_members (project_id, user_id) values (?, ?)";
	private final static String SELECT_MEMBER_SQL = "select * from project_members where project_id = ? and user_id = ?";
	private final static String DELETE_MEMBER_SQL = "delete from project_members where project_id = ? and user_id = ?";

	private Connection connection;

	private static ProjectDao instace;

	public static ProjectDao getInstace() throws ClassNotFoundException, SQLException {
		if (instace == null) {
			instace = new ProjectDao();
		}
		return instace;
	}

	public ProjectDao() throws ClassNotFoundException, SQLException {
		this.connection = JDBCPostgresConnection.getConnection();
	}

	public Project mapAllAttributes(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong(1);
		String name = resultSet.getString(2);
		String description = resultSet.getString(3);
		String githubUri = resultSet.getString(4);
		long ownerId = resultSet.getLong(5);
		String imageUri = resultSet.getString(6);
		return new Project(id, name, description, githubUri, ownerId, imageUri);
	}

	@Override
	public Project mapRow(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong(1);
		String name = resultSet.getString(2);
		String description = resultSet.getString(3);
		String githubUri = resultSet.getString(4);
		String onwerName = resultSet.getString(5);
		String imageUri = resultSet.getString(6);
		return new Project(id, name, description, githubUri, User.fromName(onwerName), imageUri);
	}

	public Project save(Project project) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, project.getName());
		statement.setString(2, project.getDescription());
		statement.setString(3, project.getGithubUri());
		statement.setLong(4, project.getOwnerId());
		statement.setString(5, project.getImageUri());
		boolean success = statement.executeUpdate() > 0;
		if (success) {
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			long id = resultSet.getLong(1);
			return new Project(id, project.getName(), project.getDescription(), project.getGithubUri(),
					project.getOwnerId(), project.getImageUri());
		}
		return null;
	}

	public Project update(Project project) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
		statement.setString(1, project.getName());
		statement.setString(2, project.getDescription());
		statement.setString(3, project.getGithubUri());
		statement.setString(4, project.getImageUri());
		statement.setLong(5, project.getId());
		boolean success = statement.executeUpdate() > 0;
		if (success) {
			return project;
		}
		return null;
	}

	public Project findById(long id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setLong(1, id);
		ResultSet resultSet = statement.executeQuery();
		if (!resultSet.next())
			return null;
		return mapAllAttributes(resultSet);
	}

	public List<Project> findAllByUserId(long userId) throws SQLException {
		List<Project> projects = new ArrayList<Project>();
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID_SQL);
		statement.setLong(1, userId);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			projects.add(mapRow(resultSet));
		}
		return projects;
	}

	public boolean addMember(long projectId, long userId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_MEMBERS_SQL);
		statement.setLong(1, projectId);
		statement.setLong(2, userId);
		return statement.executeUpdate() > 0;
	}

	public boolean deleteMember(long projectId, long userId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_MEMBER_SQL);
		statement.setLong(1, projectId);
		statement.setLong(2, userId);
		return statement.executeUpdate() > 0;
	}

	public boolean memberExists(long projectId, long userId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SELECT_MEMBER_SQL);
		statement.setLong(1, projectId);
		statement.setLong(2, userId);
		return statement.executeQuery().next();
	}
}
