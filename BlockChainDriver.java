import java.util.Scanner;
import java.io.PrintWriter;

/** 
 * A class that interacts with the user taking inputs over and over and running commands on the blockchain 
 * 
 * @author Alexander Maret and Lucas Willett
*/

public class BlockChainDriver {
  public static void main(String[] args){
    BlockChain bc = new BlockChain(Integer.parseInt(args[0]));
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println(bc.toString());
    pen.print("Command? ");
    pen.flush();
    Scanner eyes = new Scanner(System.in);
    String input = eyes.nextLine();
    int amount;
    while(!input.equals("quit")){
      if(input.equals("mine")){
        pen.print("Amount transferred? ");
        pen.flush();
        amount = Integer.parseInt(eyes.nextLine());
        Block b = bc.mine(amount);
        pen.println("amount = " + b.getAmount() + ", nonce = " + b.getNonce());
      }

      if(input.equals("append")){
        pen.print("Amount transferred? ");
        pen.flush();
        amount = Integer.parseInt(eyes.nextLine());
        int nonce;
        pen.print("Nonce? ");
        pen.flush();
        nonce = Integer.parseInt(eyes.nextLine());
        Block b = new Block(bc.getSize(), amount, bc.getHash(), nonce);
        bc.append(b);
      }

      if(input.equals("remove")){
        bc.removeLast();
      }

      if(input.equals("check")){
        if(bc.isValidBlockChain()){
          pen.println("Chain is valid!");
        }
        else{
          pen.println("Chain is invalid!");
        }
      }


      if(input.equals("report")){
        bc.printBalances(pen);
      }

      if(input.equals("help")){
        pen.println("Valid commands:");
        pen.println("\tmine: discovers the nonce for a given transaction");
        pen.println("\tappend: appends a new block onto the end of the chain");
        pen.println("\tremove: removes the last block from the end of the chain");
        pen.println("\tcheck: checks that the block chain is valid");
        pen.println("\treport: reports the balances of Alexis and Blake");
        pen.println("\thelp: prints this list of commands");
        pen.println("\tquit: quits the program");
      }

      pen.println();
      pen.println(bc.toString());
      pen.print("Command? ");
      pen.flush();
      input = eyes.nextLine();
    }
    eyes.close();
  }
}
