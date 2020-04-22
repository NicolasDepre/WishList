package be.LaPireTeam.wishlist.DAO;

import java.sql.Connection;

import be.LaPireTeam.wishlist.List;

public class ListDAO extends DAO<List> {

    public ListDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(List obj) {
        return false;
    }

    @Override
    public boolean delete(List obj) {
        return false;
    }

    @Override
    public boolean update(List obj) {
        return false;
    }

    @Override
    public List find(int id) {
        return null;
    }
}
