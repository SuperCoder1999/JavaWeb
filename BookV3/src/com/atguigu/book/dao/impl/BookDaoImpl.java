package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book values(null, ?, ?, ?, ?, ?, ?)";
        int rows = update(sql, book.getName(), book.getAuthor(),
                book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return rows;
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id=?";
        int rows = update(sql, id);
        return rows;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        int rows = update(sql, book.getName(), book.getAuthor(),
                book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return rows;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where `id`=?";
        Book book = queryForOne(sql, Book.class, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        List<Book> list = queryForMany(sql, Book.class);
        return list;
    }
}
