package com.basejava.storege;

import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import com.basejava.sql.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

//    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
//        connectionFactory = new ConnectionFactory() {
//            @Override
//            public Connection getConnection() throws SQLException {
//                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//            }
//        };
//    }

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch(SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT FROM resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch(SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (! rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        } catch(SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List <Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
