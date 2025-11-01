package com.itis.oris.service;

import com.itis.oris.dao.BookDao;
import com.itis.oris.model.Book;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookDao bookDao;

    public BookService() {
        this.bookDao = new BookDao();
    }

    public void getAll(HttpServletRequest request){
        List<Book> books = null;
        try {
            books = bookDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("books", books);
    }

    public void findById(HttpServletRequest request, HttpServletResponse response){
        String requestId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(requestId);
            try {
                Optional<Book> optionalBook = bookDao.findById(id);
                if (optionalBook.isPresent()) {
                    Book book = optionalBook.get();
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/showone.ftlh").forward(request, response);
                } else {
                    request.setAttribute("errormessage", "Книга с таким ID не существует");
                    request.getRequestDispatcher("/home.ftlh").forward(request, response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            request.setAttribute("errormessage", "Введите данные корректно");
            try {
                request.getRequestDispatcher("/home.ftlh").forward(request, response);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response){
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String pagesStr = request.getParameter("pages");

        Book book = new Book();
        setBook(book, title, author, genre, pagesStr);

        try {
            bookDao.save(book);
            response.sendRedirect("/Control_war_exploded/show");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        try {
            Optional<Book> optionalBook = bookDao.findById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String genre = request.getParameter("genre");
                String pagesStr = request.getParameter("pages");

                setBook(book, title, author, genre, pagesStr);

                bookDao.update(book);
                request.setAttribute("book", book);
                request.getRequestDispatcher("/showone.ftlh").forward(request, response);
            } else {
                request.setAttribute("errormessage", "Книга с таким ID не существует");
                request.getRequestDispatcher("/home.ftlh").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setBook(Book book, String title, String author, String genre, String pagesStr){
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        if (pagesStr != null && !pagesStr.isEmpty()) {
            book.setPages(Integer.parseInt(pagesStr));
        }
    }
}
