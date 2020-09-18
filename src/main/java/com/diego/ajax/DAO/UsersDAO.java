/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.ajax.DAO;

import com.diego.ajax.Model.Users;
import com.diego.ajax.Utils.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class UsersDAO {

    public void create(Users user) throws ClassNotFoundException, SQLException {
        Connection conn = Conn.getConexao();

        PreparedStatement ps = conn.prepareStatement("insert into users (name, password, accesslevel) values (?, ?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getAccess_level());
        ps.execute();

        conn.close();

    }

    public List<Users> listAll() throws ClassNotFoundException, SQLException {
        Connection conn = Conn.getConexao();

        PreparedStatement ps = conn.prepareStatement("select * from users");
        ResultSet rs = ps.executeQuery();

        List<Users> list = new ArrayList<>();
        while (rs.next()) {
            Users user = new Users();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            list.add(user);
        }
        conn.close();
        return list;
    }
    
    public void delete(Users user) throws ClassNotFoundException, SQLException {
        Connection conn = Conn.getConexao();

        PreparedStatement ps = conn.prepareStatement("delete from users where id = ?");
        ps.setInt(1, user.getId());
        ps.execute();

        conn.close();
    }
}
