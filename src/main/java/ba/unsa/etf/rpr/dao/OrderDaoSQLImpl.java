package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoSQLImpl extends AbstractDao<Order> implements OrderDao{

    public OrderDaoSQLImpl(){
        super("Orders");
    }

    @Override
    public Order rowToObject(ResultSet rs) throws OrderException {
        Order order=new Order();
        try {
            order.setId(rs.getInt("id"));
            order.setIdUser(rs.getInt("idUser"));
            order.setDateOfOrder(rs.getDate("dateOfOrder"));
            order.setPrice(rs.getDouble("price"));
            return order;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public List<Order> getByDateRange(Date startingDate, Date endingDate) throws OrderException {
        try{
            PreparedStatement statement=getConnection().prepareStatement("SELECT * FROM Order WHERE dateOfOrder BETWEEN ? AND ?");
            statement.setObject(1,startingDate);
            statement.setObject(2,endingDate);
            ResultSet queryResult = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while(queryResult.next()){
                Order object = rowToObject(queryResult);
                orders.add(object);
            }
            queryResult.close();
            return orders;
        }catch (SQLException e){
            throw new OrderException(e.getMessage());
        }
    }
}
