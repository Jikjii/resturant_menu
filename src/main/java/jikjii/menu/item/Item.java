package jikjii.menu.item;

import org.springframework.data.annotation.Id;

public class Item {
    // initialize properties
    private final Long id;
    private final String name;
    private final Long price;
    private final String description;
    private final String image;

    // add properties to Item object
    public Item(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    // get properties
    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }

    public String getImage(){
        return image;
    }
    // update properties inside item object
    public Item updateWith(Item item) {
        return new Item(
                this.id,
                this.name,
                this.price,
                this.description,
                this.image
        );
    }





}
