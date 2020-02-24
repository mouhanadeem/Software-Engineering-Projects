package com.project.punjabresturant.Model;

public class Items {
    private String name;
    private String image;
    private String price;
    private String Description;

    public Items(String name, String image, String price, String description) {
        this.name = name;
        this.image = image;
        this.price = price;
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return Description;
    }

    public Items() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
