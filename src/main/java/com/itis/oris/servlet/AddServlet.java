package com.itis.oris.servlet;

import com.itis.oris.dao.BookDao;
import com.itis.oris.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/templates/add.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setGenre(req.getParameter("genre"));
        String pagesStr = req.getParameter("pages");
        book.setPages(pagesStr != null && !pagesStr.isEmpty() ? Integer.parseInt(pagesStr) : null);

        bookDao.save(book);
        resp.sendRedirect("/show");
    }
}