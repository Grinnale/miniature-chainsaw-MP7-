import java.nio.ByteBuffer;
import java.security.MessageDigest;

/** 
 * A class that stores the individual blocks of a blockchain, which is number of order, amount, previous hash, this hash and the nonce value.
 * 
 * @author Alexander Maret and Lucas Willett
*/

public class Block {
  int index;
  int amount;
  Hash prev;
  long nonce;
  Hash hash;

  public Block(int num, int amount, Hash prevHash){
    this.index = num;
    this.amount = amount;
    this.prev = prevHash;
    this.nonce = 0;
    this.hash = findValidHash();
  }

  public Block(int num, int amount, Hash prevHash, long nonce){
    this.index = num;
    this.amount = amount;
    this.prev = prevHash;
    this.nonce = nonce;
    this.hash = new Hash(calculateHash());
  }

  public int getNum() {
    return this.index;
  }

  public int getAmount() {
    return this.amount;
  }

  public long getNonce(){
    return this.nonce;
  }

  public Hash getPrevHash() {
    return this.prev;
  }

  public Hash getHash() {
    return this.hash;
  }

  public String toString() {
    return "Block " + this.index + " (Amount: " + this.amount + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prev + ", hash: " + this.hash + ")";
  }

  public Hash findValidHash(){
    Hash h = new Hash(calculateHash());
    while(!h.isValid()){
      this.nonce++;
      h = new Hash(calculateHash());
    }
    return h;
  }

  public byte[] calculateHash() {
    try {
      MessageDigest md = MessageDigest.getInstance("sha-256");


      byte[] ibytes = ByteBuffer.allocate(Integer.BYTES).putInt(index).array();
      md.update(ibytes);
      byte[] jbytes = ByteBuffer.allocate(Integer.BYTES).putInt(amount).array();
      md.update(jbytes);
      if(prev != null){
        byte[] kbytes = prev.getData();
        md.update(kbytes);
      }
      byte[] lbytes = ByteBuffer.allocate(Long.BYTES).putLong(nonce).array();
      md.update(lbytes);


      byte[] hash = md.digest();
      return hash;
    } catch (Exception e) {
      return null;
    }
  } // calculateHash(String)
}
