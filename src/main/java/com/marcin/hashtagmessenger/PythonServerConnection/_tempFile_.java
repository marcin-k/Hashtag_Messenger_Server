package com.marcin.hashtagmessenger.PythonServerConnection;

import java.io.*;
import java.net.*;

public class _tempFile_ {

    private String testWord(String word){

        String strToReturn = "";

        try{
            Socket socket=new Socket("localhost",2004);

            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            DataInputStream din=new DataInputStream(socket.getInputStream());


            dout.writeUTF("116,104,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
            dout.flush();

            System.out.println("send 2nd mess");
            String str = din.readUTF();//in.readLine();

            System.out.println(str);


            dout.close();
            din.close();
            socket.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return strToReturn;
    }
//----------------------------------------------------------------------------------------------------------------------
    private static String convertToVector(String input){
        input = input.toLowerCase();
        String output = "[";

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
        output+="],";
        return output;
}

}
