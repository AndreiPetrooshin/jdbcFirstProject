package examples.DAO;

import examples.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookDAOimpl implements BookDao {

    private String name = "root";
    private String password = "root";
    private String URL = "jdbc:mysql://localhost:3306/bookmanager";


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("OOPS!!");
        }
    }

    public boolean addBook(Book book) {

        try (Connection connection = DriverManager.getConnection(URL, name, password)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO books(name, genre, price) VALUES (?,?,?)");
            statement.setString(1, book.getName());
            statement.setString(2, book.getGenre());
            statement.setInt(3, book.getPrice());
            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBook(int id) {
        try (Connection connection = DriverManager.getConnection(URL, name, password)) {
            PreparedStatement statement = connection.prepareStatement("DELETE  FROM books WHERE id=(?)");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBook(int id,Book book) {
        try (Connection connection = DriverManager.getConnection(URL, name, password)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE books SET name=(?), genre=(?), price=(?) WHERE id=(?)");
            statement.setString(1, book.getName());
            statement.setString(2, book.getGenre());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Book getBook(int id) {
        Book book = new Book();
        try(Connection connection = DriverManager.getConnection(URL,name,password);
            Statement statement = connection.createStatement())

        {
           ResultSet set= statement.executeQuery("SELECT * FROM books WHERE id=" + id);
               set.next();
               book.setId(set.getInt("id"));
               book.setName(set.getString("name"));
               book.setGenre(set.getString("genre"));
               book.setPrice(set.getInt("price"));

           return book;
        }

        catch (SQLException e){
            e.printStackTrace();
            return book;
        }
    }

    public List<Book> getAll() {
        List<Book> result = new ArrayList<Book>();
        try (Connection connection = DriverManager.getConnection(URL, name, password);
             Statement statement = connection.createStatement()) {

            ResultSet set = statement.executeQuery("SELECT * FROM books");
            while (set.next()) {
                Book book = new Book();
                book.setId(set.getInt("id"));
                book.setName(set.getString("name"));
                book.setGenre(set.getString("genre"));
                book.setPrice(set.getInt("price"));
                result.add(book);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
