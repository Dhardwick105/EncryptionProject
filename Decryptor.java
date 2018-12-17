import java.net.*;
import java.io.*;
import java.util.*;

public class Decryptor{

	Socket encrypterConn;
	public Decryptor(int port) throws IOException  //create a server socket 
    {
      ServerSocket servSock = new ServerSocket(port);
      encrypterConn = servSock.accept();
      byte [] message = receiveMessage();
      
      
      System.out.println(new String(message));
      //parseMessage(byte []);
    }
  
  	public byte [] receiveMessage()
    {
    
      byte [] totalMessage = new byte [0];
    
      int buffSize = 100;
      
      int bytesRead = 100;
      while(bytesRead == buffSize)
      {
        try {
          	byte[] incomingMessage = new byte[buffSize];//byte array to receive data in
          	InputStream input = encrypterConn.getInputStream();//retrieve input stream
            bytesRead = input.read(incomingMessage);//store incoming byte into byte array
            
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );//concatenate byte arrays
			outputStream.write(totalMessage);
			outputStream.write(incomingMessage);
            totalMessage = outputStream.toByteArray();
          }catch (SocketException e)
          
          {
              break;
          } 
          catch (IOException e) {
              e.printStackTrace();
              System.exit(1);
              break;
          }    
      }
    
    	return totalMessage;
    } 
    
    public Message parseMessage(byte [] payload)
    {
    
    	String s = new String(payload);
    	s = s.substring(3);
      	int indexOfNextObject = s.indexOf("\n");
      	ArrayList<String> payloadString = new ArrayList<String>();
      	while(indexOfNextObject != -1) 
      	{
        	payloadString.add(s.substring(0,indexOfNextObject));
			System.out.println(s.substring(0,indexOfNextObject));
       	 	s = s.substring(indexOfNextObject+1);
       		indexOfNextObject = s.indexOf("\n");
      	}
      
      	s = s.substring(2);
      	int indexOfNEnd = s.indexOf("**");
      	String n = s.substring(0,indexOfNEnd);
      	s = s.substring(indexOfNEnd+2);
      	String a = s;
      	
      	return new Message(n,a,payloadString);
      	
    }
    	
    public static void main (String [] args) throws IOException
    {
    	if (args.length != 1)
    	{
    		System.out.println("Usage Decryptor.java <port>");
    	}
    	
    	Decryptor dec = new Decryptor(Integer.parseInt(args[0]));
    }
}