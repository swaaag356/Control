package com.itis.oris.servlet;

import com.itis.oris.dao.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/show")
public class ShowAllServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("books", bookDao.findAll());
        req.getRequestDispatcher("/templates/showall.ftl").forward(req, resp);
    }
}