package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDaoSQLImpl extends AbstractDao<Order> implements OrderDao{

    public OrderDaoSQLImpl(){
        super("Order");
    }

    @Override
    public Order rowToObject(ResultSet rs) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getByDateRange(Date startingDate, Date endingDate) throws OrderException {
        return null;
    }
}
