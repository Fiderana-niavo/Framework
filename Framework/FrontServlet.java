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
import jakarta.servlet.annotation.*;


@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> hashmap=new HashMap<String,Mapping>();
    HashMap<String,Object> singleton=new HashMap<String,Object>();

    public HashMap<String, Object> getSingleton() {
        return singleton;
    }

    public void setSingleton(HashMap<String, Object> singleton) {
        this.singleton = singleton;
    }

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
        ClassLoader cl=this.getServletContext().getClassLoader();
        util.setSingleton(singleton, path, cl);
    }

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                //ModelView view=(ModelView)cl.loadClass(m.getClassName()).getMethod(m.getMethod()).invoke(obj);
                ModelView view=u.getModelView(request, obj, m);
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
    }*/

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
                    Object obj=u.getObject(singleton, m.getClassName(), m, cl);
                    String test=u.setFieldOfClass(request,obj,response);
                    Method me=u.getRightMethod(obj.getClass(), m.getMethod());
                    ModelView view=u.getModelView(request, obj, m);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        Utilitaire u = new Utilitaire();

        try {
            String postUrl=u.getPostUrl(url);
            if(u.verifyMap(postUrl,hashmap)==true){
            Mapping m=(Mapping)hashmap.get(postUrl);
            ClassLoader cl=this.getServletContext().getClassLoader();
            Object obj=u.getObject(singleton, m.getClassName(), m, cl);
            String test=u.setFieldOfClass(request,obj,response);
            Method me=u.getRightMethod(obj.getClass(), m.getMethod());
            ModelView view=u.getModelView(request, obj, m);
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


