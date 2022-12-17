package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.Date;
import java.util.List;

public interface OrderDao extends Dao<Order>{

    /**
     * Returns all orders within date range
     * @param startingDate
     * @param endingDate
     * @return lis of Orders
     * @throws OrderException
     */

    List<Order> getByDateRange(Date startingDate, Date endingDate) throws OrderException;
}
