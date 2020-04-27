package be.LaPireTeam.wishlist.DAO;

import java.sql.Connection;

import be.LaPireTeam.wishlist.Group;

public class DAOFactory {

    public static GroupDAO getGroupDAO(){
        return new GroupDAO();
    }

    public static ListDAO getListDAO(){
        return new ListDAO();
    }

    public static ProductDAO getProductDAO(){
        return new ProductDAO();
    }

    public static UserDAO getUserDAO(){
        return new UserDAO();
    }
}
