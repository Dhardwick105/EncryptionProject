import java.net.Socket;
/**
	Goal of this class is to open up a connection with the decryptor, and send an encoded message with some specified a and n
*/
public class Encryptor{

	Socket connToDecryptor;
		
	public static void main(string [] args)
	{
		if (args.len != 5)
		{
			System.out.println("Usage Encryptor <message> <n> <a> <ip> <port>");
		}
		
		String message = args[0];
		int n = Integer.parseInt(args[1]);
		int a = Integer.parseInt(args[2]);
		
		connectToDecryptor(args[3],args[4]);
		
		RSAencryptor messageEncryptor = new RSAencryptor(message,n,a);
		
		Message sendableMessage = messageEncryptor.getMessage();
		
		sendToDecryptor(sendableMessage);
	}
	
	public void connectToDecryptor(String IP, String port)
	{
		try{
			Socket connToDecryptorTemp = new Socket(IP, Integer.parseInt(port));
			connToDecryptor = connToDecryptorTemp;
		}
		catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendToDecryptor(Message sendable){
	
		byte [] decodedMsg = sendable.convertToSendable();
		int len = sendable.getLen();
		OutputStream output;
		
		try {
				output = connToDecryptor.getOutputStream();// get the outputStream to send through
				output.write(decodedMsg,0,len);//send
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		connToDecryptor.close();
	}
}