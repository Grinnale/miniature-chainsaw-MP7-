import java.util.Arrays;
import java.util.HexFormat;

/** 
 * A simple class that is the hash of the Blocks, which are summaries of all the data stored in the block
 * 
 * @author Alexander Maret and Lucas Willett
*/

public class Hash {

  byte[] data;

  public Hash(byte[] data){
    this.data = data;
  }

  public byte[] getData(){
    return this.data;
  }

  public boolean isValid(){
    if(toString().substring(0, 6).equals("000000")) {
      return true;
    } else {
      return false;
    }
  }

  public String toString(){
    /*
    String str = "";
    for(int i = 0; i < this.data.length; i++){
      String temp = String.valueOf(Byte.toUnsignedInt(this.data[i]));
      str = str + String.format(temp);
    }
    return str;
    */
    HexFormat hf = HexFormat.of();
    return hf.formatHex(this.data);
  }

  public boolean equals(Object other){
    if(other instanceof Hash){
      Hash o = (Hash) other;
      if(Arrays.equals(this.data, o.getData())){
        return true;
      }
    }
    return false;
  }
}
