/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class UsuarioDAO {
    /*
    private final Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
       
    public String size() {
        return this.usuarios.size() + "";
    }
    
    public void insertUsuario(Usuario usuario) {
        this.usuarios.put(usuario.toString(), usuario);
    }
    
    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario.toString());
    }
    */
    private final Connection connection;
    private ResultSet resultSet;
    
    public UsuarioDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
