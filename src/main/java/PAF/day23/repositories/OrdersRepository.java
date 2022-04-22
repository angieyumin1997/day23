package PAF.day23.repositories;

import org.springframework.stereotype.Repository;

import PAF.day23.models.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Repository
public class OrdersRepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public Optional<Order> getOrder(Integer id){
    
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_ORDER,id);
        Order order=new Order();
        if (result.next()){
            order = Order.create(result);
            
        }else{
            return Optional.empty();
        }

        return Optional.of(order);
    }

    
    public List<Order> getOrderDetails(Integer id){
        final List<Order> orders = new LinkedList<>();
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_ORDER_DETAILS,id);
        Order order=new Order();
        while (result.next()){
            order = Order.createOrderDetails(result);
            orders.add(order);
        }
        return orders;
    }
    
    public List<Order> getCostPrice(Integer id){
        final List<Order> orders = new LinkedList<>();
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_COST_PRICE,id);
        Order order=new Order();
        while (result.next()){
            order = Order.createCostPrice(result);
            orders.add(order);
        }
        return orders;
    }

}
