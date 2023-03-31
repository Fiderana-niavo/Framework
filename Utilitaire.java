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
        // Annotation a = emp.getClass().getAnnotation(AnnotationMethod.class);
        // AnnotationMethod annotation = (AnnotationMethod) a;
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
}
