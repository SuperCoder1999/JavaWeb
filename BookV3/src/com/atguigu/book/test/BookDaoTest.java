package com.atguigu.book.test;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;




public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        int rows = bookDao.addBook(new Book(null,"国哥为什么这么帅！", "191125", new
                BigDecimal(9999),1100000,0,null));
        System.out.println(rows);
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    public void updateBook() {
        int rows = bookDao.updateBook(new Book(21,"大家都可以这么帅！", "国哥", new BigDecimal(9999),1100000,0,null
        ));
        System.out.println(rows);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book book :
                bookDao.queryBooks()) {
            System.out.println(book);
        }
    }
}