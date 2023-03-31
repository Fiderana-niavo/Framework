package etu1917.framework.servlet;

import etu1917.framework.Utilitaire;
import etu1917.framework.MyClassLoader;
import etu1917.framework.Mapping;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.*;

public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> hashmap=new HashMap<String,Mapping>();

    public HashMap<String, Mapping> getHashmap() {
        return hashmap;
    }

    public void setHashmap(HashMap<String, Mapping> hashmap) {
        this.hashmap = hashmap;
    }

    public void init(){
        Utilitaire util=new Utilitaire();
        String path=this.getServletContext().getRealPath("")+"WEB-INF/classes";
        HashMap<String,Mapping> map=new HashMap<String,Mapping>();
        util.putAllIntoHashMap(path,map,this.getServletContext().getClassLoader());
        this.setHashmap(map);
    }
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        Utilitaire u = new Utilitaire();
        String path=this.getServletContext().getRealPath("")+"WEB-INF/classes/";
        try {
           
            out.println(hashMap.size());
        } catch (Exception e) {
            out.println(e.getMessage());
             e.printStackTrace();
        }
    }
}

