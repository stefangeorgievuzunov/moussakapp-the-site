package web;

import db.User;
import services.DataManagementService;
import services.impl.db.DataManagementServiceImpl;

import javax.inject.Inject;
import javax.persistence.criteria.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Inject
    DataManagementService dataManagementService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getRequestDispatcher("/html/home.jsp").forward(request,response);
         dataManagementService.select(new DataManagementServiceImpl.Specification<>(User.class,Long.class){
             @Override
             protected Selection<? extends Long> select(Root<User> root, CriteriaBuilder builder) {
                 return builder.count(root);
             }

             @Override
             protected Predicate where(Root<User> root, CriteriaBuilder builder) {
                 return null;
             }
         });
    }
}
