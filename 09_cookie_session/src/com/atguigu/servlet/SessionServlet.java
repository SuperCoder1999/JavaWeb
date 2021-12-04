package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    protected void createOrGetSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建 Session
        HttpSession session = request.getSession();

        //2.获取 Session 的ID
        String id = session.getId();

        //3.判断Session是否是新的
        Boolean isNew = session.isNew();

        response.getWriter().write("得到的Session，它的id是：" + id + " <br /> ");
        response.getWriter().write("这个Session是否是新创建的：" + isNew + " <br /> ");
    }

    // 向 Session域中 存储数据
    protected void setAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建或获取 Session
        HttpSession session = request.getSession();

        //2.存储数据
        session.setAttribute("key1", "value1");

        response.getWriter().write("已经往Session 中保存了数据");
    }

    // 从 Session域中 获取数据
    protected void getAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 Session
        HttpSession session = request.getSession();

        //2. 获取 Session域中的数据
        Object attribute = session.getAttribute("key1");

        response.getWriter().write("从Session 中获取出key1 的数据是：" + attribute);
    }

    //Session 的默认 存活时间(可以在Tomcat或工程中的 web.xml中配置)
    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建或获取 Session
        HttpSession session = request.getSession();

        //查看 session的存活时间
        int maxInactiveInterval = session.getMaxInactiveInterval();
        response.getWriter().write("当前会话的Session的超时时长为：" + maxInactiveInterval + " 秒 ");
    }

    // 手动设置 访问 life3 的浏览器对应的 session 存活时间为 3 秒
    protected void life3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建或获取 Session
        HttpSession session = request.getSession();
        //2.设置 Session 的存活时间
        session.setMaxInactiveInterval(3);
        //3.回写信息
        response.getWriter().write("当前Session已经设置为3秒后超时");
    }
    // 立刻删除 当前 Session
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建或获取 当前Session
        HttpSession session = req.getSession();
        //2.设置为 立刻删除
        session.invalidate();
        //回写信息
        resp.getWriter().write("Session已经设置为超时（无效）");
    }
}
