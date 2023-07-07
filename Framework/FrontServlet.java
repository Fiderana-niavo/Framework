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
            out.println(postUrl+"OKOK");
            if(u.verifyMap(postUrl,hashmap)==true){
            Mapping m=(Mapping)hashmap.get(postUrl);
            ClassLoader cl=this.getServletContext().getClassLoader();
            Object obj=cl.loadClass(m.getClassName()).newInstance();
           // String[] test=u.setFieldOfClass(request,obj);
            //out.println(test);
            //ModelView view=(ModelView)cl.loadClass(m.getClassName()).getMethod(m.getMethod()).invoke(obj);
            Method me=u.getRightMethod(obj.getClass(), m.getMethod());
            Object[] objs=u.getAllParameters(request, me);
            for(int j=0;j<objs.length;j++){
                out.println(objs[j]);
            }
                out.println(u.getModelView(request, obj, m));
            ModelView view=u.getModelView(request, obj, m);
            out.println("OKOK1"+view.getView());
            String viewStr=view.getView();
            out.println("OKOK3");
                HashMap h=view.getData();
            out.println("OKO41");
                u.setAttribute(request, h);
            out.println("OKOK2");
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
    out.println(postUrl+"OKOK");
    if(u.verifyMap(postUrl,hashmap)==true){
    Mapping m=(Mapping)hashmap.get(postUrl);

    ClassLoader cl=this.getServletContext().getClassLoader();
    Object obj=cl.loadClass(m.getClassName()).newInstance();
    String test=u.setFieldOfClass(request,obj,response);
    out.print("eto3");
   // for (int i=0;i<test.length;i++){
    out.println(test+"yess");

    //}
    //ModelView view=(ModelView)cl.loadClass(m.getClassName()).getMethod(m.getMethod()).invoke(obj);
    Method me=u.getRightMethod(obj.getClass(), m.getMethod());
    Object[] objs=u.getAllParameters(request, me);
    for(int j=0;j<objs.length;j++){
        out.println(objs[j]);
    }
        out.println(u.getModelView(request, obj, m));
    ModelView view=u.getModelView(request, obj, m);
    out.println("OKOK1"+view.getView());
    String viewStr=view.getView();
    out.println("OKOK3");
        HashMap h=view.getData();
    out.println("OKO41");
        u.setAttribute(request, h);
    out.println("OKOK2");
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


