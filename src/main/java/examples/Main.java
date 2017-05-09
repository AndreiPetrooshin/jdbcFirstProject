package examples;

import examples.DAO.BookDao;
import examples.DAO.JdbcBookDAOimpl;
import examples.model.Book;

public class Main {
    public static void main(String[] args) {
        BookDao dao = new JdbcBookDAOimpl();
        for (Book bk:dao.getAll()){
            System.out.println(bk);
        }
        System.out.println();
        System.out.println("GETTING BOOK BY ID: ");
        System.out.println(dao.getBook(3));


        System.out.println();
        System.out.println("ADD AND UPDATE BOOK: ");
        dao.addBook(new Book("Fei Winx", "Fantasy", 50));
        dao.updateBook(2,new Book("BATMAN", "COMICS", 70));
        for (Book bk:dao.getAll()){
            System.out.println(bk);
        }


        System.out.println();
        System.out.println("DELETING BOOK: ");
        dao.deleteBook(9);

        for (Book bk:dao.getAll()){
            System.out.println(bk);
        }
    }

}
