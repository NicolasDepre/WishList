package be.LaPireTeam.wishlist.DAO;

import java.sql.Connection;

import be.LaPireTeam.wishlist.Product;

public class ProductDAO extends DAO<Product> {

   public ProductDAO(Connection conn){
       super(conn);
   }

    @Override
    public boolean create(Product obj) {
        return false;
    }

    @Override
    public boolean delete(Product obj) {
        return false;
    }

    @Override
    public boolean update(Product obj) {
        return false;
    }

    @Override
    public Product find(int id) {
        return null;
    }
}
