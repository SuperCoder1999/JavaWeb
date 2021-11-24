package com.atguigu.book.service.impl;

import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
