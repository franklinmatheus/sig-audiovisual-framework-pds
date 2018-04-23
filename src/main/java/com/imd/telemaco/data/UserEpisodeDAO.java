package com.imd.telemaco.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.UserEpisode;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserEpisodeDAO implements DAO<UserEpisode>{
	private static UserEpisodeDAO userEpisodeDAO = null;
    
    /**
     * @return userEpisodeDAO 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     */
    public static synchronized UserEpisodeDAO getInstance() throws DatabaseException {
        if(userEpisodeDAO == null)
        	userEpisodeDAO = new UserEpisodeDAO();
        return userEpisodeDAO;
    }
	
	
	@Override
	public void insert(UserEpisode userEpisode) throws DatabaseException, CloseConnectionException {
		String sql = "INSERT INTO telemaco.userEpisode (idfkuser, idfkepisode) VALUES (?, ?)";
		Connection connection = (Connection) ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stm = (PreparedStatement) connection.prepareStatement(sql);
			stm.setInt(1, userEpisode.getIdUser());
			stm.setInt(2, userEpisode.getIdEpisode());
			
			stm.execute();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
		}
	}

	@Override
	public UserEpisode select(int id) throws DatabaseException, CloseConnectionException {
		String sql = "SELECT * FROM telemaco.userEpisode WHERE id='" + id + "'";
		Connection connection = (Connection) ConnectionFactory.getConnection();
		UserEpisode userEpsiode = null;
		
		try {
			Statement stm = (Statement) connection.createStatement();
			ResultSet result = stm.executeQuery(sql);
			
			if(result.next()) {
				int idUser = result.getInt("idfkuser");
				int idEpisode = result.getInt("idfkepisode");
				
				userEpsiode = new UserEpisode(idUser, idEpisode);
			} 
			
			return userEpsiode;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
		}
	}

	@Override
	public void delete(UserEpisode userEpisode) throws DatabaseException, CloseConnectionException {
		String sql = "DELETE FROM telemaco.userEpisode WHERE idfkuser='" + userEpisode.getIdUser() + "' AND idfkepisode='" + userEpisode.getIdEpisode() + "'";
		Connection connection = (Connection) ConnectionFactory.getConnection();
		
        try {
            Statement statement = (Statement) connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            throw new DatabaseException();
        } finally {
            try { 
                connection.close();
            } catch(SQLException e) {
                throw new CloseConnectionException();
            }
        }
		
	}

	@Override
	public void update(UserEpisode object) throws DatabaseException, CloseConnectionException {
		// Não vai ser necessário a implementação		
	}
	
}
