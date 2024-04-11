import java.io.PrintWriter;
import java.util.Arrays;

/** 
 * A single linked list structure with a first and last pointer of nodes with blocks as the data elements
 * 
 * @author Alexander Maret and Lucas Willett
*/

public class BlockChain {
  public static class Node{
    Block block;
    Node next;

    public Node(Block b, Node n){
      this.block = b;
      this.next = n;
    }
  }
  


  Node first;
  Node last;

  public BlockChain(int initial){
    Block b = new Block(0, initial, null);
    Node n = new Node(b, null);
    first = n;
    last = n;
  }

  public Block mine(int amount){
    Block prevBlock = last.block;
    return new Block(prevBlock.getNum() + 1, amount, prevBlock.getHash());
  }

  public int getSize(){
    return this.last.block.getNum() + 1;
  }

  public void append(Block blk) throws IllegalArgumentException{
    if(blk.getHash().isValid()){ //hash is valid
      if(Arrays.equals(blk.getHash().getData(), blk.calculateHash())){ //hash is appropriate for the contents
        if(blk.getPrevHash().equals(last.block.getHash())){ //previous hash is correct
          Node n = new Node(blk, null);
          this.last.next = n;
          this.last = n;
        }
      }
    }
  }

  public boolean removeLast(){
    if(getSize() == 1){
      return false;
    }
    else{
      int newIndex = getSize() - 2;
      this.last = first;
      for(int i = 0; i < newIndex; i++){
        last = last.next;
      }
      last.next = null;
      return true;
    }
  }

  public Hash getHash(){
    return this.last.block.getHash();
  }

  public boolean isValidBlockChain(){
    Node n = this.first;
    int sum = 0;
    for(int i = 0; i < getSize(); i++){
      sum += n.block.getAmount();
      if(sum < 0){
        return false;
      }
      if(!n.block.getHash().isValid()){
        return false;
      }
      n = n.next;
    }
    return true;
  }

  public void printBalances(PrintWriter pen){
    int sumA = 0;
    Node n = this.first;
    for(int i = 0; i < this.getSize(); i++){
      sumA += n.block.getAmount();
      n = n.next;
    }
    int sumB = this.first.block.getAmount() - sumA;
    pen.println("Alexis: " + sumA + ", Blake: " + sumB);
  }

  public String toString(){
    String str = "";
    Node n = this.first;
    for(int i = 0; i < this.getSize(); i++){
      str += n.block.toString() + "\n";
      n = n.next;
    }
    return str;
  }
}
