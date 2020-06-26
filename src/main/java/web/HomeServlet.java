package web;

import db.User;
import services.DbService;
import services.impl.db.DbServiceImpl;

import javax.inject.Inject;
import javax.persistence.criteria.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Inject
    DbService dbService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Long> data = dbService.createQuery(new DbServiceImpl.Query<User, Long>(User.class, Long.class) {
            @Override
            protected Selection<? extends Long> select() {
                return builder().count(root());
            }
            @Override
            protected Predicate where() {
                return null;
            }
        });

        List<User> users= dbService.createQuery(new DbServiceImpl.Query<User, User>(User.class,User.class) {
            @Override
            protected Selection<? extends User> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                return null;
            }
        });

        System.out.println("USERS SIZE :" + users.size());

        request.setAttribute("data", data.get(0));
        request.getRequestDispatcher("/html/home.jsp").forward(request, response);
    }
}
