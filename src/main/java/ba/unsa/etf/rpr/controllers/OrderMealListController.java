package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Meal;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * JavaFX Controller for window displaying meals in the order list
 */
public class OrderMealListController extends AbstractController{
    @FXML
    public ListView orderListView;
    private List<Meal> orderList;
    public OrderMealListController(List<Meal> orderList){
        this.orderList=orderList;
    }

    /**
     * Populates a ListView with order list
     */
    @FXML
    public void initialize(){
        for(Meal meal : orderList)
            addMealToCartListView(orderListView,meal);
    }
}
