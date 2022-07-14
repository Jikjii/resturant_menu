package jikjii.menu.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class ItemService {
    private final CrudRepository<Item, Long> repository;

    public ItemService(CrudRepository<Item, Long> repository) {
        this.repository = repository;

        this.repository.saveAll(defaultItems());
    }


    // static items being seeded
    private static List<Item> defaultItems() {
        return List.of(
                new Item(1L, "Burger", 599L, "Cheesy double stacked bacon burger", "https://images.pexels.com/photos/2983101/pexels-photo-2983101.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Item(2L, "Pizza", 299L, "Homemade cheese slice", "https://images.pexels.com/photos/845798/pexels-photo-845798.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Item(3L, "Coke", 199L, "Refreshing bottle of coke", "https://images.pexels.com/photos/2983100/pexels-photo-2983100.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        );
    }

    // CRUD functionality

    // finding all items under the Item object (menu item)
    public List<Item> findAll() {
        // creating a new array var that will be take in the parameters of the List items
        List<Item> list = new ArrayList<>();
        // finding all items
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    // finding item by Id
    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }

    // creating item by initializing a new object from our Item obj
    public Item create(Item item) {
        // to ensure the item ID remains unique
        // use the current timestamp

        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        // saving the new item in our repo
        return repository.save(copy);
    }

    public Optional<Item> update( Long id, Item newItem) {
        // Only update an item if it can be found first
        return repository.findById(id)
                .map(oldItem -> {
                    Item updated = oldItem.updateWith(newItem);
                    // new var updated equals the old item with the new items properties
                    // is then saved into our rep
                    return repository.save(updated);
                });
    }

    // delete an item
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
