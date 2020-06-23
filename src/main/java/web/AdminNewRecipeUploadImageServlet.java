package web;

import exceptions.InvalidDataException;
import org.apache.commons.io.FilenameUtils;
import org.jboss.weld.util.bytecode.ClassFileUtils;
import services.UploadImageService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@MultipartConfig
@WebServlet("/admin/new/recipe/upload/image")
public class AdminNewRecipeUploadImageServlet extends HttpServlet {
    @Inject
    private UploadImageService uploadImageService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (uploadImageService.isCorrect(request.getPart("uploadedFile"))) {
                System.out.println("HELLO FCKIN WORLD");
            }
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
    }
}
