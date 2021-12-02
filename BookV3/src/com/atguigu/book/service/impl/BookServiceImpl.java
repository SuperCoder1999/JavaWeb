package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;
import com.atguigu.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

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

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //0.先创建一个 page空对象,之后用set方法,一点一点赋值
        // (其实也可以在page中创建一个全参构造器,当此方法中所有参数均获取,再用构造器创建)
        Page<Book> page = new Page<>();
        //2.设置每页显示的数量
        page.setPageSize(pageSize);
        //3.设置总记录数
        //3.1 获取总记录数
        int pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //4.设置总页码数
        //4.1求总页码数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0)
            pageTotal++;
        page.setPageTotal(pageTotal);

        //1.设置当前页码 -- Page中需要用到 pageTotal.所以将pageNo放在后面
        page.setPageNo(pageNo);

        //5.设置当前页数据
        //5.1计算当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }
}
