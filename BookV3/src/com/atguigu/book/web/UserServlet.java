package com.atguigu.book.web;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.impl.UserServiceImpl;
import com.atguigu.book.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    //这个对数据库操作的类,几乎在每个方法中都要创建,所以提起出来
    UserServiceImpl userService = new UserServiceImpl();

    //注销功能
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.删除 session
        req.getSession().invalidate();
        //2. 重定向到(好像 请求转发也行)  web下面的index.jsp
        // resp.sendRedirect(req.getContextPath());
        req.getRequestDispatcher("/").forward(req, resp);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1）获取用户名密码
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
//        2）判断用户是否存在
        //UserServiceImpl userService = new UserServiceImpl();
        User loginUser = userService.login(userName, password);
        if (loginUser == null) {
            System.out.println("账号或密码错误:" + userName + "\t" + password);
            //请求转移到 登陆页面
            //        3）如果登陆失败--->>>> 返回用户名或者密码错误信息
            request.setAttribute("msg", "用户名或者密码错误");
            request.setAttribute("username", userName);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        //        4）如果登录成功--->>>> 返回登陆成功信息
        System.out.println("登录成功");
        System.out.println(request.getContextPath());
        //保存 用户 到 Session域 中,这样,在每个页面 的 login_success_menu中都可以获取 用户名信息
        request.getSession().setAttribute("user", loginUser);
        request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
    }


    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //利用验证码 防止 通过同一个页面多次提交(因为延迟\或 返回之前页面后 再次提交)
        //1.获取 kaptcha 中在 session中 保存的 客户端当前验证码 对应的id参数.
        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //2.当前客户端提交的所有信息都进入了服务器.此时 删除 这个 id参数对应的 键值对数据.
        // 如果客户端再次提交这个id参数,就找不到 键值对.此时,可以判断之前的页面被多次提交了.
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //2.获取 页面提交的 验证码
        String code = request.getParameter("code");

        // 1、获取请求的参数
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //优化:利用BeanUtils 将多个请求参数,注入到JavaBean中
        User user = WebUtils.copyParaToBean(request.getParameterMap(), new User());
        System.out.println(user);

        //我觉得 确认密码 不必要再检验了.这个应该由js检验的
//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token== null || !token.equalsIgnoreCase(code)) {
            //请求转发到 regist.jsp
            if (token == null)
                System.out.println(token + "验证码重复利用:" + code);
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }

        System.out.println("3、检查 用户名是否可用");
//        3、检查 用户名是否可用
        //UserServiceImpl userService = new UserServiceImpl();
        if (!userService.existsUsername(userName)) {
            //请求转发到 regist.jsp
            System.out.println("用户名已经存在:" + userName);
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            request.setAttribute("msg", "用户名已经被使用");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        System.out.println("添加用户");
        userService.registerUser(new User(userName, password, email));
        //请求转发到 regist_success.jsp
        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
    }
}
