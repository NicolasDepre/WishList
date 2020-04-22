package be.LaPireTeam.wishlist.DAO;

import java.sql.Connection;

import be.LaPireTeam.wishlist.Group;

public class GroupDAO extends DAO<Group> {

    public GroupDAO(Connection conn){
        super(conn);
    }
    @Override
    public boolean create(Group obj) {
        return false;
    }

    @Override
    public boolean delete(Group obj) {
        return false;
    }

    @Override
    public boolean update(Group obj) {
        return false;
    }

    @Override
    public Group find(int id) {
        return null;
    }
}
