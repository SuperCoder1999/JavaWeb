package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;

import java.util.List;

public interface BookService {

    //这几个 增删改 的操作 都没有返回信息???
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);
}
