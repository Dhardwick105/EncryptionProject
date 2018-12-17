public class RSAdecrypt
{

	public RSAdecrypt(Message msg)
	{
		//constructor needs to be here
	}

	/**
   * This takes the encrypted list of submessages and returns the decrypted message. I've got a version that uses normal ints instead of biginteger
   * but since RSA uses massive numbers idk how we would use just ints. let me (Elliot) know if I should change or move this somewhere else. This method
   * has been tested and does work for the example given in the book of QUOTHTHERAVENNEVERMORE. Spaces are 26, a-z is 00-26.
   * @param nums the list of submessages
   * @param power the variable b in the textbook
   * @param modulo the variable n in the textbook
   * @return the deciphered message
   */
	public String decryptMessage(List<String> nums, BigInteger power, BigInteger modulo){
		BigInteger a;
		BigInteger b;
      //message is the final decrypted message
		String message = "";
      //subIntMessage is the sub message in integer form
		String subIntMessage = "";
      //fullIntMessage is all subIntMessages combined
		String fullIntMessage = "";
      //goes through all submessages in nums
		for(int i=0; i<nums.size(); i++){
          //gets first sub message block
			a = new BigInteger(nums.get(i));
			b = a;
          //Puts the sub message block to the power power
			for(BigInteger j= BigInteger.ONE; j.compareTo(power) < 0; j = j.add(BigInteger.ONE)){
				a = (a.multiply(b)).mod(modulo);
			}
          //Puts the BitInteger into a string
			subIntMessage = a.toString();
          //Adds back any zeroes that were left on converting from string
			for(int k=0; k<nums.get(i).length() - String.valueOf(a).length(); k++){
				subIntMessage = "0" + subIntMessage;
			}
          //Adds on the submessage to the full message of integers
			fullIntMessage = fullIntMessage + subIntMessage;
		}
      //Changes the integer message into the alphabet
		for(int l=0; l<fullIntMessage.length()/2; l++){
          //for normal characters
			if(!fullIntMessage.substring(l*2,l*2+2).equals("26")){
				message=message + Character.toString((char) (Integer.parseInt(fullIntMessage.substring(l*2,l*2+2))+65));
              //for spaces
			}else{
				message=message + " ";
			}
		}
		return message;
	}  
}