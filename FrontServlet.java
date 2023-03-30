package etu1917.framework.servlet;

import etu1917.framework.Utilitaire;
import etu1917.framework.Mapping;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.*;
public class FrontServlet extends HttpServlet {
     HashMap<String,Mapping> hashmap=new HashMap<String,Mapping>();

    public HashMap<String, Mapping> getHashmap() {
        return hashmap;
    }

    public void setHashmap(HashMap<String, Mapping> hashmap) {
        this.hashmap = hashmap;
    }
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        Utilitaire u = new Utilitaire();
        try {
            out.println(u.getPostUrl(url));

        } catch (Exception e) {
        }
    }
}

