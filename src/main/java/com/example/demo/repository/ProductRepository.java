
// package com.example.demo.repository;

// import com.example.demo.model.Product;
// import org.springframework.stereotype.Repository;

// import javax.sql.DataSource;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// @Repository
// public class ProductRepository {

//     private final DataSource dataSource;

//     public ProductRepository(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     // CREATE
//     public void save(Product product) {
//         String sql = """
//             INSERT INTO product(title, description, photo, price, category_id)
//             VALUES (?, ?, ?, ?, ?)
//         """;

//         try (
//             Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)
//         ) {
//             ps.setString(1, product.getTitle());
//             ps.setString(2, product.getDescription());
//             ps.setString(3, product.getPhoto());
//             ps.setDouble(4, product.getPrice());
//             ps.setLong(5, product.getCategoryId());
//             ps.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // READ - get all
//     public List<Product> findAll() {
//         List<Product> list = new ArrayList<>();
//         String sql = "SELECT * FROM product";

//         try (
//             Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()
//         ) {
//             while (rs.next()) {
//                 Product p = new Product(
//                     rs.getLong("id"),
//                     rs.getString("title"),
//                     rs.getString("description"),
//                     rs.getString("photo"),
//                     rs.getDouble("price"),
//                     rs.getLong("category_id")
//                 );
//                 list.add(p);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return list;
//     }

//     // READ - get by id
//     public Product findById(Long id) {
//         String sql = "SELECT * FROM product WHERE id = ?";
//         Product product = null;

//         try (
//             Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)
//         ) {
//             ps.setLong(1, id);
//             ResultSet rs = ps.executeQuery();

//             if (rs.next()) {
//                 product = new Product(
//                     rs.getLong("id"),
//                     rs.getString("title"),
//                     rs.getString("description"),
//                     rs.getString("photo"),
//                     rs.getDouble("price"),
//                     rs.getLong("category_id")
//                 );
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return product;
//     }

//     // UPDATE
//     public void update(Long id, Product product) {
//         String sql = """
//             UPDATE product
//             SET title=?, description=?, photo=?, price=?, category_id=?
//             WHERE id=?
//         """;

//         try (
//             Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)
//         ) {
//             ps.setString(1, product.getTitle());
//             ps.setString(2, product.getDescription());
//             ps.setString(3, product.getPhoto());
//             ps.setDouble(4, product.getPrice());
//             ps.setLong(5, product.getCategoryId());
//             ps.setLong(6, id);
//             ps.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // DELETE
//     public void delete(Long id) {
//         String sql = "DELETE FROM product WHERE id=?";

//         try (
//             Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)
//         ) {
//             ps.setLong(1, id);
//             ps.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }

package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ================= CREATE =================
    public void save(Product product) {
        String sql = """
            INSERT INTO product (title, description, photo, price, category_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getPhoto());
            ps.setDouble(4, product.getPrice());
            ps.setLong(5, product.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= READ ALL =================
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // ================= READ BY ID =================
    public Product findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // ================= UPDATE =================
    public void update(Long id, Product product) {
        String sql = """
            UPDATE product
            SET title = ?, description = ?, photo = ?, price = ?, category_id = ?
            WHERE id = ?
        """;

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getPhoto());
            ps.setDouble(4, product.getPrice());
            ps.setLong(5, product.getCategoryId());
            ps.setLong(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= DELETE =================
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 🔹 MAP ResultSet → Product (GIẢM LẶP CODE)
    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("photo"),
            rs.getDouble("price"),
            rs.getLong("category_id")
        );
    }
}
