package be.LaPireTeam.wishlist.DAO;

import java.sql.Connection;

import be.LaPireTeam.wishlist.Group;

public class DAOFactory {

    protected final static Connection conn = null;

    public static GroupDAO getGroupDAO(){
        return new GroupDAO(conn);
    }

    public static ListDAO getListDAO(){
        return new ListDAO(conn);
    }

    public static ProductDAO getProductDAO(){
        return new ProductDAO(conn);
    }

    public static UserDAO getUserDAO(){
        return new UserDAO(conn);
    }
}
