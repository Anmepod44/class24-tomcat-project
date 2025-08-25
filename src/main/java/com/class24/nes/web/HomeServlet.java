package com.class24.nes.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Simple controller that keeps Java separate from HTML/CSS.
 * Visiting /home will redirect users to the static landing page (/index.html).
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Preserve clean separation: forward to static HTML
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
