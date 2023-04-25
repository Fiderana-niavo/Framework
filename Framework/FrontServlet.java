package etu1917.framework.servlet;

import etu1917.framework.Utilitaire;
import etu1917.framework.Mapping;
import etu1917.framework.ModelView;

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
        String path=this.getServletContext().getRealPath("")+"WEB-INF/classes/";
        HashMap<String,Mapping> map=new HashMap<String,Mapping>();
        util.putAllIntoHashMap(path,map,this.getServletContext().getClassLoader());
        this.setHashmap(map);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            String url = request.getRequestURL().toString();
            Utilitaire u = new Utilitaire();
            try {
                String postUrl=u.getPostUrl(url);
                if(u.verifyMap(postUrl,hashmap)==true){
                    Mapping m=(Mapping)hashmap.get(postUrl);
                    ClassLoader cl=this.getServletContext().getClassLoader();
                    Object obj=cl.loadClass(m.getClassName()).newInstance();
                  
                    u.setFieldOfClass(request,obj);
                    String str=String.valueOf(obj.getClass().getDeclaredMethod(m.getMethod()).invoke(cl.loadClass(m.getClassName()).newInstance()));
                    request.setAttribute("str",str);
                    RequestDispatcher dispatch=request.getRequestDispatcher("test.jsp");
                    dispatch.forward(request,response);
                }
                    out.println(this.hashmap.size());
                } catch (Exception e) {
                    out.println(e.getMessage());
                  
                     e.printStackTrace();
                }
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        Utilitaire u = new Utilitaire();
        try {
            String postUrl=u.getPostUrl(url);
            if(u.verifyMap(postUrl,hashmap)==true){
            Mapping m=(Mapping)hashmap.get(postUrl);
            ClassLoader cl=this.getServletContext().getClassLoader();
            Object obj=cl.loadClass(m.getClassName()).newInstance();
            u.setFieldOfClass(request,obj);
            ModelView view=(ModelView)cl.loadClass(m.getClassName()).getMethod(m.getMethod()).invoke(obj);
            String viewStr=view.getView();
                HashMap h=view.getData();
                u.setAttribute(request, h);
                request.setAttribute("key",h.keySet());
                RequestDispatcher dispatch=request.getRequestDispatcher(viewStr);
                dispatch.forward(request,response);
                //response.sendRedirect(viewStr);
            }
            out.println(this.hashmap.size());
        } catch (Exception e) {
            out.println(e.getMessage());
          
             e.printStackTrace();
        }
    }
}

