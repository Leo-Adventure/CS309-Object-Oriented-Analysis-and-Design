package CommandPattern;

import java.util.ArrayList;

public class Invoker {
    private ArrayList<Order> orderList = new ArrayList<>();
    public void addOrder(Order order){
        orderList.add(order);
    }
    public void placeOrder(){
        for(Order ord: orderList){
            ord.execute();
        }
        orderList.clear();
    }
}
