package etu1917.framework;
import java.util.HashMap;

public class  ModelView{
    String view;
    HashMap<String,Object> data;

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public ModelView(String view){
        data=new HashMap<String,Object>();
        setView(view);
    }

    public ModelView(){
        data=new HashMap<String,Object>();
    }

    public void addItem(String cle,Object value){
        this.data.put(cle,value);
    }

}