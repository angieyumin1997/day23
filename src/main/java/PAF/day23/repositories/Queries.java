package PAF.day23.repositories;

public interface Queries {
    public static final String SQL_SELECT_ORDER = 
    "select id,customer_id,cast(order_date as date) from orders where id=?";

    public static final String SQL_SELECT_ORDER_DETAILS = 
    "select quantity,unit_price,discount from order_details where order_id=?";

    public static final String SQL_SELECT_COST_PRICE =
    "select o.quantity,o.order_id,p.standard_cost from order_details as o join products as p on o.product_id = p.id where o.order_id=?";
}
