package com.marcin.hashtagmessenger.pythonServerConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connector {
    private static Connector ourInstance = new Connector();

    public static Connector getInstance() {
        return ourInstance;
    }

    private Connector() {
    }



    public String checkWord(String word){

        String strToReturn = "";

        try{
            Socket socket=new Socket("localhost",2004);
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            DataInputStream din=new DataInputStream(socket.getInputStream());
            dout.writeUTF(convertToVector(word));
            dout.flush();
            strToReturn = din.readUTF();//in.readLine();
            dout.close();
            din.close();
            socket.close();
        }

        catch(Exception e){
            System.out.println("Python server not available");
            return "exception";
        }

        return strToReturn;
    }
//------------------------------------ Converts string to vector -------------------------------------------------------
    private static String convertToVector(String input){
        input = input.toLowerCase();
        String output = "";

        for (int i = 0; i < 20; i++){
            try{
                int number = (int)input.charAt(i);
                output+= ""+number+",";
            }
            catch(StringIndexOutOfBoundsException e) {
                output += "0,";
            }
        }
        output = output.substring(0,output.length()-1);
        output+="";
        return output;
    }
}
