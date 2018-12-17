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
  	for (int i = 0; i<message.size(); i++)
  	{
  		len += message.get(i).length();
  	}
  	return len;
  }
  public byte [] convertToSendable()
  {
    String messageText = "";
  	for (int i = 0; i<lenOfMessage; i++)
    {
    	messageText+= message.get(i)+"\n";
    }
    
    String totalString = "MSG"+messageText+"**"+n+"**"+a;
    
    byte [] totalPayload = messageText.getBytes();
    return totalPayload;
  }
}
