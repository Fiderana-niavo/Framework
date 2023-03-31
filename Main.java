package main;
import etu1917.framework.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import jakarta.*;
public class Main{
    public static void main(String[] args){
         Utilitaire u=new Utilitaire();
        // String[] all=u.getAllFile("/opt/apache-tomcat-10.0.27/webapps/MyFramework/WEB-INF/classes");
        // for(String a:all){
        //     System.out.println(a);
        // }
        // String p="etu1917/framework";
        // File file=new File(p);
        //ClassLoader c;
        // Class c=u.getClass();
        // System.out.print("ok");

        // try{
        //     System.out.println(file.getParent());
        // }catch(Exception e){}

        String a="hello.haha";
        // for(int i=0;i<a.toCharArray().length;i++){
        //     if(String.valueOf(a.toCharArray()[i]).equals(".")){
        //     System.out.print("yesss");
        // }
        // }

        String f="/opt/apache-tomcat-10.0.27/webapps/MyFramework/WEB-INF/classes";
        HashMap<String,Mapping> myHashMap=new HashMap<String,Mapping>();
            System.out.println(myHashMap.size());
        try{
            //u.putAllIntoHashMap(f,myHashMap);
            System.out.println(myHashMap.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
}