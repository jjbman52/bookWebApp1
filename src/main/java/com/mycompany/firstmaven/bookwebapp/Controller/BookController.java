/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Controller;

import com.mycompany.firstmaven.bookwebapp.Model.Book;
import com.mycompany.firstmaven.bookwebapp.Model.BookService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jordanrehbein
 */
@WebServlet(name = "BookController", urlPatterns = {"/bc"})
public class BookController extends HttpServlet {
    @EJB
    private BookService bookService;

    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String LIST_ACTION = "list";
    public static final String ADD_ACTION = "add";
    public static final String SAVE_ACTION = "save";
    public static final String EDIT_DELETE_ACTION = "editDelete";
    public static final String DELETE_ACTION = "delete";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String destination = "/bookList.jsp";

        try {
            String action = request.getParameter(ACTION);
            String id = request.getParameter(ID);
            
            List<Book> bookList = null;
            Book book = new Book();

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                destination = "/bookList.jsp";
                refreshList(bookService, request);
            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                destination = "/bookAdd.jsp";
                refreshList(bookService, request);
            } else if (action.equalsIgnoreCase(SAVE_ACTION)) {
                String title = request.getParameter("title").trim();
                String isbn = request.getParameter("isbn").trim();
                String authorId = request.getParameter("authorId").trim();
                if (id == null) {
                    destination = "/bookAdd.jsp";
                    bookService.addNewBook(title, isbn, authorId);
                    refreshList(bookService, request);
                } else {
                    destination = "/bookList.jsp";
                    bookService.updateBook(id, title, isbn, authorId);
                    refreshList(bookService, request);
                }
            } else if (action.equalsIgnoreCase(EDIT_DELETE_ACTION)) {
                destination = "/bookEdit.jsp";
                book = bookService.getBook(id);
                request.setAttribute("book", book);
            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                destination = "/authorList.jsp";
                bookService.removeBookById(id);
                refreshList(bookService, request);
            }

        } catch (Exception e) {
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshList(BookService authorService, HttpServletRequest request) throws Exception {
        List<Book> bookList;
        bookList = authorService.findAll();
        request.setAttribute("bookList", bookList);
    }

    @Override
    public void init() throws ServletException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
