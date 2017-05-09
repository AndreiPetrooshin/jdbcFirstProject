package examples.DAO;


import examples.model.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by draqo on 08.05.2017.
 */
public interface BookDao  {

    boolean addBook(Book book);

    boolean deleteBook(int id);

    boolean updateBook(int id,Book book);

    Book getBook(int id);

    List<Book> getAll();



}
