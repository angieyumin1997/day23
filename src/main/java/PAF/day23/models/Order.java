package PAF.day23.models;

import java.util.Date;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Order {

    private Integer id;
    private Integer customer_id;
    private Date order_date;
    private Double quantity;
    private Double unit_price;
    private Double discount;
    private Double standard_cost;

    public Double getStandard_cost() {
        return standard_cost;
    }
    public void setStandard_cost(Double standard_cost) {
        this.standard_cost = standard_cost;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public Double getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    
    public static Order create(SqlRowSet rs){

        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomer_id(rs.getInt("customer_id"));
        order.setOrder_date(rs.getDate("cast(order_date as date)"));
    
        return order;
    } 

    public static Order createOrderDetails(SqlRowSet rs){

        Order order = new Order();
        order.setQuantity(rs.getDouble("quantity"));
        order.setUnit_price(rs.getDouble("unit_price"));
        order.setDiscount(rs.getDouble("discount"));
        
        return order;
    }

    public static Order createCostPrice(SqlRowSet rs){

        Order order = new Order();
        order.setQuantity(rs.getDouble("quantity"));
        order.setStandard_cost(rs.getDouble("standard_cost"));
    
        return order;
    }
}
