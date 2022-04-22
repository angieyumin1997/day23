package PAF.day23.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PAF.day23.models.Order;
import PAF.day23.repositories.OrdersRepository;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/order/total")
public class OrdersRestController {

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping(path="/{order_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerDetails(@PathVariable(name="order_id",required=true) Integer id){

        JsonObjectBuilder builder = Json.createObjectBuilder();
        Optional<Order> opt = ordersRepository.getOrder(id);
        if (opt.isEmpty())
            return ResponseEntity.status(404)
                .body(
                    builder.add("error", "not found: %s".formatted(id))
                        .build().toString()
                );
        Order order = opt.get();
        builder.add("ID",order.getId())
            .add("customer_id",order.getCustomer_id())
            .add("order_date",order.getOrder_date().toString());
        
        List<Order> orders = new LinkedList<>();
        orders = ordersRepository.getOrderDetails(id);
        Double sum =0.0;
        Double totalDiscount =0.0;
        

        for(Order o:orders){
            Double totalPrice = (o.getQuantity()) * (o.getUnit_price());
            sum += totalPrice;
            Double discount = (o.getQuantity()) * (o.getDiscount());
            totalDiscount += discount;
        }

        builder.add("total_price",sum);
        builder.add("discount",totalDiscount);

        List<Order> ordersCostPrice = new LinkedList<>();
        ordersCostPrice = ordersRepository.getCostPrice(id);
        Double totalCostPrice = 0.0;

        for(Order o:ordersCostPrice){
            Double costPrice = (o.getQuantity()) * (o.getStandard_cost());
            totalCostPrice += costPrice;
        }

        builder.add("total_cost_price",totalCostPrice);

        return ResponseEntity.ok(builder.build().toString());
    }

}
