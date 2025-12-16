

// package com.example.demo.model;

// public class Product {

//     private Long id;
//     private String title;
//     private String description;
//     private String photo;
//     private double price;
//     private Long categoryId;

//     // Constructor rỗng (BẮT BUỘC cho JDBC + Jackson)
//     public Product() {
//     }

//     // Constructor đầy đủ
//     public Product(Long id,
//                    String title,
//                    String description,
//                    String photo,
//                    double price,
//                    Long categoryId) {
//         this.id = id;
//         this.title = title;
//         this.description = description;
//         this.photo = photo;
//         this.price = price;
//         this.categoryId = categoryId;
//     }

//     // ===== Getter & Setter =====

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getPhoto() {
//         return photo;
//     }

//     public void setPhoto(String photo) {
//         this.photo = photo;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     public Long getCategoryId() {
//         return categoryId;
//     }

//     public void setCategoryId(Long categoryId) {
//         this.categoryId = categoryId;
//     }
// }


package com.example.demo.model;

public class Product {

    private Long id;
    private String title;
    private String description;
    private String photo;
    private double price;
    private Long categoryId;

    // 🔹 BẮT BUỘC: constructor rỗng (Jackson + JDBC)
    public Product() {
    }

    public Product(Long id, String title, String description,
                   String photo, double price, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.categoryId = categoryId;
    }

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
