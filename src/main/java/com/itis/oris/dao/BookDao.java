package com.itis.oris.dao;


import com.itis.oris.model.Book;
import com.itis.oris.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY id";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPages(rs.getInt("pages"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return books;
    }

    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setGenre(rs.getString("genre"));
                    book.setPages(rs.getInt("pages"));
                    return book;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, genre, pages) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setInt(4, book.getPages());
            ps.executeUpdate();

            ResultSet rs = ps.getResultSet();
            if (rs.next()) book.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, genre = ?, pages = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setInt(4, book.getPages());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}