package org.mycompany.myname.realization;


import java.io.File;
import java.util.ArrayList;

public class Lists {
   ArrayList listOfDir = new ArrayList();
   ArrayList listOfFiles = new ArrayList();
    String path;
    String parentPath;
    public Lists(String path){
        this.path=path;
        fillLists();
    }
   public void fillLists(){
       File allFiles = new File(this.path);
       System.out.println(this.path);
       parentPath=allFiles.getParent();

       for (File el:allFiles.listFiles()){
           if (el.isDirectory())
               listOfDir.add(el);
           else
               listOfFiles.add(el);
       }

   }
   public ArrayList getDir(){
       return listOfDir;
   }
   public ArrayList getFiles(){
        for (Object el:listOfFiles){
            System.out.println(el);
        }
       return listOfFiles;
   }

   public String getCurDir(){
        return parentPath;
   }
}
