import java.util.*;
public class Message{
  String n;
  String a;
  ArrayList<String> messageText;
  int lenOfMessage;
  
  public Message(String nVal, String aVal, ArrayList<String> message)
  {
    n = nVal;
    a = aVal;
    messageText = message;
    lenOfMessage = getLen();
  }
  
  public String getN()
  {
  	return n;
  }
  public String getA()
  {
  	return a;
  }
  
  public int getLen()
  {
  	int len = 0;
  	for (int i = 0; i<messageText.size(); i++)
  	{
  		len += messageText.get(i).length();
  	}
  	return len;
  }
  public byte [] convertToSendable()
  {
    String messageAsText = "";
  	for (int i = 0; i<lenOfMessage; i++)
    {
    	messageAsText+= messageText.get(i)+"\n";
    }
    
    String totalString = "MSG"+messageAsText+"**"+n+"**"+a;
    
    byte [] totalPayload = messageAsText.getBytes();
    return totalPayload;
  }
}
