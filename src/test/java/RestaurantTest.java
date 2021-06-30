import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantTest {
   
	Restaurant restaurant;

	
	@BeforeEach
	public void setUp() {
		LocalTime openingTime = LocalTime.parse("10:30:00");
		LocalTime closingTime = LocalTime.parse("22:00:00");

		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
	}	
	
	
	
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime timeBetweenOpeningAndClosingOfRestaurant = LocalTime.parse("18:00:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(timeBetweenOpeningAndClosingOfRestaurant);
    	assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime timeOutsideOpeningAndClosingTime = LocalTime.parse("23:00:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(timeOutsideOpeningAndClosingTime);
    	assertFalse(spyRestaurant.isRestaurantOpen());
    }
    
    
    
    //>>>>>>>>>>>>>>>>>>>>TOTAL COST of ORDER --New Feature TDD--<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    @Test
    public void totalCostOfOrder_should_return_388_if_items_choosen_from_menu_are_sweetcorn_and_vegetable_lasagne() {
    	List<Item> itemsChoosenFromMenu = new ArrayList<>();
    	itemsChoosenFromMenu.add(new Item("Sweet corn soup", 119));
    	itemsChoosenFromMenu.add(new Item("Vegetable lasagne", 269));
    	assertEquals(388, restaurant.totalCostOfOrder(itemsChoosenFromMenu));
    }
    
    
    @Test
    public void totalCostOfOrder_should_return_269_if_items_choosen_from_menu_is_vegetable_lasagne() {
    	List<Item> itemsChoosenFromMenu = new ArrayList<>();
    	itemsChoosenFromMenu.add(new Item("Vegetable lasagne", 269));
    	assertEquals(269, restaurant.totalCostOfOrder(itemsChoosenFromMenu));
    }
    
    
    @Test
    public void totalCostOfOrder_should_return_0_if_no_items_are_choosen() {
    	List<Item> itemsChoosenFromMenu = new ArrayList<>();
    	assertEquals(0, restaurant.totalCostOfOrder(itemsChoosenFromMenu));
    }
    
    //<<<<<<<<<<<<<<<<<<<<<TOTAL COST of ORDER  --New Feature TDD-->>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void adding_item_to_menu_should_increase_menu_size_by_1() {
	   int initialMenuSize = restaurant.getMenu().size();
	   restaurant.addToMenu("Sizzling brownie", 319);
	   assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("Vegetable lasagne");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_that_does_not_exist_should_throw_exception() {
		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
	}
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	@Test
	public void calling_display_details_for_printing_output_on_conolse() {
		restaurant.displayDetails();
	}
}