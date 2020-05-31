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
import java.util.List;

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

         Long data=dataManagementService.selectSingleResult(new DataManagementServiceImpl.Specification<User,Long>(User.class,Long.class){
             @Override
             protected Selection<? extends Long> select(Root<User> root, CriteriaBuilder builder) {
                 CriteriaQuery<Long> criteriaQuery=builder.createQuery(Long.class);

                 return builder.count(criteriaQuery.from(User.class));
//                 return builder.count(root);
             }

             @Override
             protected Predicate where(Root<User> root, CriteriaBuilder builder) {
                 return builder.equal(root.get("firstName"),"Stefan");
             }
         });

         request.setAttribute("data",data);
    }
}
