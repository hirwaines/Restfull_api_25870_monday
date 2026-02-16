package auca.ac.rw.question3_Restaurant.controller;

import auca.ac.rw.question3_Restaurant.modal.MenuItem;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private List<MenuItem> menu = new ArrayList<>();
    private long id = 1;

    public MenuController() {
        menu.add(createItem("Margherita Pizza", "Classic pizza", 12.99, "PIZZA", true));
        menu.add(createItem("Pepperoni Pizza", "Pepperoni pizza", 14.99, "PIZZA", true));
        menu.add(createItem("Caesar Salad", "Fresh salad", 8.99, "SALAD", true));
        menu.add(createItem("Greek Salad", "Greek salad", 9.99, "SALAD", true));
        menu.add(createItem("Cheeseburger", "Beef burger", 11.99, "BURGER", true));
        menu.add(createItem("Chicken Burger", "Chicken burger", 10.99, "BURGER", true));
        menu.add(createItem("Spaghetti Carbonara", "Pasta with bacon", 13.99, "PASTA", true));
        menu.add(createItem("Penne Arrabbiata", "Spicy pasta", 12.99, "PASTA", true));
        menu.add(createItem("Tiramisu", "Coffee dessert", 6.99, "DESSERT", true));
    }

    private MenuItem createItem(String name, String desc, double price, String cat, boolean avail) {
        MenuItem item = new MenuItem();
        item.setId(id++);
        item.setName(name);
        item.setDescription(desc);
        item.setPrice(price);
        item.setCategory(cat);
        item.setAvailable(avail);
        return item;
    }

    @GetMapping
    public List<MenuItem> getAll(@RequestParam(required = false) Boolean available) {
        if (available != null && available) {
            List<MenuItem> result = new ArrayList<>();
            for (MenuItem item : menu) {
                if (item.isAvailable()) result.add(item);
            }
            return result;
        }
        return menu;
    }

    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable long id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) result.add(item);
        }
        return result;
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailable() {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.isAvailable()) result.add(item);
        }
        return result;
    }

    @GetMapping("/search")
    public List<MenuItem> search(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) result.add(item);
        }
        return result;
    }

    @PostMapping
    public MenuItem add(@RequestBody MenuItem item) {
        item.setId(id++);
        menu.add(item);
        return item;
    }

    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable long id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                item.setAvailable(!item.isAvailable());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return menu.removeIf(item -> item.getId() == id);
    }
}
