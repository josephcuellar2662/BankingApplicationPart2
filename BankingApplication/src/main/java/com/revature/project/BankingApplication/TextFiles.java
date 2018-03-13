package com.revature.project.BankingApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TextFiles {
	
	public static <T> void serialize(T obj, String directoryName, String username){
    	File directory = new File(directoryName);
    	if(!directory.exists()){
    		directory.mkdirs();
    	}
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream( directoryName + "/" + username +  ".txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(obj);
            out.close();
            file.close();
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
	}

	public static <T> T deserialize(String directory, String filename){
		T obj = null;
		// Deserialization
		try
		{   
			// Reading the object from a file
			FileInputStream file = new FileInputStream(directory + "/" + filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			obj = (T) in.readObject();

			in.close();
			file.close();
		}

		catch(IOException ex)
		{
			return null;
		}

		catch(ClassNotFoundException ex)
		{
			return null;
		}

		return obj;
	}
    
    public static String fileExist(String username, String directory){
    	File[] files = new File("/Users/josephcuellar/Desktop/Revature/Project/RevatureProjects/BankingApplication/" + directory).listFiles();
        for (File file : files) {
        	if(file.getName().contains(username)){
        		return file.getName();
        	}
        }
        return null;
    }
    
    public static String getFileName(String username, String accountType){
    	File[] files = new File("/Users/josephcuellar/Desktop/Revature/Project/RevatureProjects/BankingApplication/Accounts").listFiles();
        for (File file : files) {
        	if(file.getName().contains(username) && file.getName().contains(accountType)){
        		return file.getName();
        	}
        }
        return null;
    }
    
    public static boolean deleteFile(String username){
    	File[] files = new File("/Users/josephcuellar/Desktop/Revature/Project/RevatureProjects/BankingApplication/Accounts").listFiles();
        for (File file : files) {
        	if(file.getName().contains(username)){
        		file.delete();
        		return true;
        	}
        }
        return false;
    }
    
    public static void printAllFiles(){
    	File[] files = new File("/Users/josephcuellar/Desktop/Revature/Project/RevatureProjects/BankingApplication/Accounts").listFiles();
        Account acc = null;
    	for (int i = 0; i<files.length; i++) {
        	if(!files[i].getName().contains("admin")){
        		String fileName = files[i].getName();
            	try
            	{   
            		// Reading the object from a file
            		FileInputStream f = new FileInputStream("Accounts" + "/" + fileName);
            		ObjectInputStream in = new ObjectInputStream(f);

            		// Method for deserialization of object
            		acc = (Account)in.readObject();
            		System.out.println(acc);
            		in.close();
            		f.close();
            	}

            	catch(IOException ex)
            	{
            		
            	}
            	catch(ClassNotFoundException ex)
            	{
            
            	}
        	}
        }
    } 
}
