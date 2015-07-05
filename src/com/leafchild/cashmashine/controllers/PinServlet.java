package com.leafchild.cashmashine.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by: leaf
 * Project: cashmashinedemo
 * Date: 7/5/15
 * Time: 9:08 PM
 */
public class PinServlet extends HttpServlet {

        int triesQuanity = 0;

        public void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
                boolean ifPincodeMatched = false;
                String redirectPage;
                String userPincode = String.valueOf(request.getParameter("pincode"));

                if (!userPincode.isEmpty()) {
                        //TODO: Make a DB Calls and a pincode
                        //If it's not matching - increase the tries and send to the error page
                        //If they are the same, redirect to the Operations page

                }
                //If card is there
                if (ifPincodeMatched) {
                        redirectPage = "operations.jsp";
                } else {
                        redirectPage = "error.jsp";
                }
                triesQuanity += 1;
                response.sendRedirect(redirectPage);
        }
}
