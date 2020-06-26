package web.ajax;

import exceptions.InvalidDataException;
import org.apache.commons.io.FileUtils;
import services.JSONParserService;
import services.UploadImageService;
import web.models.response.AjaxResponse;

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
    @Inject
    private JSONParserService json;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AjaxResponse ajaxResponse = new AjaxResponse();

        try {
            if (request.getPart("uploadedFile") != null && uploadImageService.isCorrect(request.getPart("uploadedFile"))) {
                ajaxResponse.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e instanceof InvalidDataException) {
                ajaxResponse.setError(e.getMessage());
            } else {
                ajaxResponse.setError("Нещо се обърка.. :(");
            }
        }

        response.setContentType("application/json; charset=utf-8");
        json.write(response.getWriter(), ajaxResponse);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
    }
}
