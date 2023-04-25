package etu1917.framework;

import java.net.*;
import java.util.*;
import java.io.File;
import java.lang.reflect.*;
import jakarta.servlet.http.*;


public class Utilitaire {

    public int getIndiceSlash(String url) {
        char[] urlToArray = url.toCharArray();
        for (int i = 0; i < urlToArray.length; i++) {
            if (String.valueOf(urlToArray[i]).equals("/")) {
                return i;
            }
        }
        return -1;
    }
    public String manalaPointClass(String a) {
        char[] aToArray =a.toCharArray();
        for (int i = aToArray.length-1; i >=0 ; i--) {
            if ((String.valueOf(aToArray[i])).equals(".")) {
                return String.valueOf(aToArray,0,i);
            }
        }
        return null;
    }

    public String getPostUrl(String urlFile)throws Exception {
        URL url = new URL(urlFile);
        String directory = String.valueOf(url.getFile());
        String[] urlTable = directory.trim().split("/");
        String valiny = "";
        if (urlTable.length > 1) {
            valiny = urlTable[2];

        }
        for (int i = 3; i < urlTable.length; i++) {
            valiny=valiny+"/"+urlTable[i];
        }
        return valiny;
    }

    public String[] getInstanceString(Object[] obj) {
        String[] str = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            str[i] = (String) obj[i];
        }
        return str;
    }

    public void concatVector(String directory,Vector v){
        File fl=new File(directory);
        File[] files=fl.listFiles();
        for (File f:files){
            v.add(directory+"/"+f.getName());
        }
    }

    public String changeSlashToPoint(String a){
            for(int i=0;i<a.toCharArray().length;i++){
                if(String.valueOf(a.toCharArray()[i]).equals("/")){
                    a=String.valueOf(a.toCharArray(),0,i)+"."+String.valueOf(a.toCharArray(),i+1,(a.toCharArray().length-1)-(i+1));
                }
            }
            return a;
    }


    public void concatVectorToFirst(String directory,Vector v){
        File fl=new File(directory);
        File[] files=fl.listFiles();
        for (File f:files){
            v.add(directory+f.getName());
        }
    }

    public File getFile(String directory){
        File f=new File(directory);
        return f;
    }

    public String getRealName(String name){
        String[] str=name.split("/");
        int index=-1;
        String realName="";
        for(int i=0;i<str.length;i++){
            if(str[i].equalsIgnoreCase("classes")){
                index=i;
            }
        }
        realName=str[index+1];
        for(int j=index+2;j<str.length;j++){
            realName=realName+"."+str[j];
        }
        return realName;
    }

    public String[] getAllFile(String directory){
        Vector valiny=new Vector();
        Vector v=new Vector();
        concatVectorToFirst(directory,v);
        for(int i=0;i<v.toArray().length;i++){
            if((getFile((String)v.toArray()[i])).isDirectory()==true){
                concatVector((String)v.toArray()[i],v);
            }
            else{
                valiny.add(manalaPointClass(getRealName(String.valueOf((String)v.toArray()[i])))  );
            }
        }
        return getInstanceString(valiny.toArray());
    }

    public void putIntoHashMap(String file,HashMap<String,Mapping> myHashMap,ClassLoader cl){
        try{
            Method[] myMethods=cl.loadClass(file).getDeclaredMethods();
            for(Method m:myMethods){
                if(m.getDeclaredAnnotation(AnnotationMethod.class)!=null){
                    Mapping map=new Mapping(file,m.getName());
                    myHashMap.put(m.getDeclaredAnnotation(AnnotationMethod.class).value(),map);
                }
            }
        }catch(Exception e){}
        
    }

    public void putAllIntoHashMap(String directory,HashMap<String,Mapping> myHashMap,ClassLoader cl) {
        String[] all=getAllFile(directory);
        System.out.println(all.length);
        for(int i=0;i<all.length;i++){
            putIntoHashMap(all[i],myHashMap,cl);
            System.out.println(myHashMap.size());
        }
    }

    public boolean verifyMap(String url,HashMap<String,Mapping> myHashMap) throws Exception{
        if(myHashMap.get(url)==null){
            throw new Exception("Page introuvable");
        }
        return true;
    }

    public void setAttribute(HttpServletRequest request,HashMap map)throws Exception{
        Object[] key=map.keySet().toArray();
        for(int i=0;i<map.keySet().size();i++){
            request.setAttribute((String)key[i],map.get((String)key[i]));
        }
    }

    public String intoCapital(String type){ //transfomer la premiere lettre en majuscule
        String firstChar=String.valueOf(type.charAt(0));
        String finalString=firstChar.toUpperCase()+String.valueOf(type.toCharArray(),1,type.toCharArray().length-1);
        return finalString;
    }

    public void setFieldOfClass(HttpServletRequest request,Object obj)throws Exception{
        Class c=obj.getClass();
        Field[] field=c.getDeclaredFields();
        for(int i=0;i<field.length;i++){
            if(request.getParameter(field[i].getName())!=null){
                field[i].setAccessible(true);
                //field[i].set(obj,request.getParameter(field[i].getName()));
                Class cl=field[i].getType();
                if(cl==int.class){
                   // c.getDeclaredMethod("set"+intoCapital(field[i].getName()),int.class).invoke(obj,Integer.valueOf(request.getParameter(field[i].getName())));
                    field[i].set(obj,Integer.parseInt(request.getParameter(field[i].getName())));
                }
                else if(cl==Integer.class){
                    field[i].set(obj,Integer.valueOf(request.getParameter(field[i].getName())));
                }
                else if(cl==Boolean.class){
                    field[i].set(obj,Boolean.valueOf(request.getParameter(field[i].getName())));
                }
                else if(cl==boolean.class){
                    field[i].set(obj,Boolean.parseBoolean(request.getParameter(field[i].getName())));
                }
                else if(cl==Float.class){
                    field[i].set(obj,Float.valueOf(request.getParameter(field[i].getName())));
                }
                else if(cl==float.class){
                    field[i].set(obj,Float.parseFloat(request.getParameter(field[i].getName())));
                }
                else if(cl==double.class){
                    field[i].set(obj,Double.parseDouble(request.getParameter(field[i].getName())));
                }
                else if(cl==Double.class){
                    field[i].set(obj,Double.valueOf(request.getParameter(field[i].getName())));
                }
                else if(cl==Date.class){
                    field[i].set(obj,Date.valueOf(request.getParameter(field[i].getName())));
                }
                else if(cl==String.class){
                    field[i].set(obj,String.valueOf(request.getParameter(field[i].getName())));
                   // c.getDeclaredMethod("set"+intoCapital(field[i].getName()),cl).invoke(obj,String.valueOf(request.getParameter(field[i].getName())));
                }
            }
        }
    }
   
}
