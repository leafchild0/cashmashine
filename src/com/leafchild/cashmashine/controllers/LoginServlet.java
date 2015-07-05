package com.leafchild.cashmashine.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by: leaf
 * Project: cashmashine
 * Date: 7/5/15
 * Time: 4:11 PM
 */
public class LoginServlet extends HttpServlet {

        public void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
                boolean isCardExist = false;
                String redirectPage;
                String cardNumber = String.valueOf(request.getParameter("cardNumber"));
                if (!cardNumber.isEmpty()) {
                        //TODO: Make a DB Calls and check if card exists

                }

                //If card is there
                if (isCardExist) {
                        redirectPage = "pinCode.jsp";
                } else {
                        redirectPage = "error.jsp";
                }
                response.sendRedirect(redirectPage);
        }

}
