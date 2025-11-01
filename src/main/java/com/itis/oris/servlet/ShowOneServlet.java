package com.itis.oris.servlet;

import com.itis.oris.dao.BookDao;
import com.itis.oris.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/showone")
public class ShowOneServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("/show");
            return;
        }
        int id = Integer.parseInt(idParam);
        Book book = bookDao.findById(id);
        if (book == null) {
            resp.sendRedirect("/show");
            return;
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("/templates/showone.ftl").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Book book = new Book();
        book.setId(Integer.valueOf(req.getParameter("id")));
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setGenre(req.getParameter("genre"));
        String pagesStr = req.getParameter("pages");
        book.setPages(pagesStr != null && !pagesStr.isEmpty() ? Integer.parseInt(pagesStr) : null);

        bookDao.save(book);
        resp.sendRedirect("/show");
    }
}
