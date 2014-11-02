/*
In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

High Card: Highest value card.
One Pair: Two cards of the same value.
Two Pairs: Two different pairs.
Three of a Kind: Three cards of the same value.
Straight: All cards are consecutive values.
Flush: All cards of the same suit.
Full House: Three of a kind and a pair.
Four of a Kind: Four cards of the same value.
Straight Flush: All cards are consecutive values of same suit.
Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.

Consider the following five hands dealt to two players:

Hand	 	Player 1	 	Player 2	 	Winner
1	 	5H 5C 6S 7S KD
Pair of Fives
 	2C 3S 8S 8D TD
Pair of Eights
 	Player 2
2	 	5D 8C 9S JS AC
Highest card Ace
 	2C 5C 7D 8S QH
Highest card Queen
 	Player 1
3	 	2D 9C AS AH AC
Three Aces
 	3D 6D 7D TD QD
Flush with Diamonds
 	Player 2
4	 	4D 6S 9H QH QC
Pair of Queens
Highest card Nine
 	3D 6D 7H QD QS
Pair of Queens
Highest card Seven
 	Player 1
5	 	2H 2D 4C 4D 4S
Full House
With Three Fours
 	3C 3D 3S 9S 9D
Full House
with Three Threes
 	Player 1
The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.

How many hands does Player 1 win?
*/
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/* Models an hand of a player */
class Hand implements Comparable<Hand> {
  private int value[];
  private String cards[];
  private int cardsSize;
  private boolean evaluated;
  
  private static int value(char c) {
    if(c >= '2' && c <= '9')
      return c - '2' + 1;
    if(c == 'T')
      return 9;
    if(c == 'J')
      return 10;
    if(c == 'Q')
      return 11;
    if(c == 'K')
      return 12;
    if(c == 'A')
      return 13;
    return 0;
  }
  
  private void highCard() {
    int max = 0;
    for(int i = 0; i < 5; ++i) {
      if(value(cards[i].charAt(0)) > max) max = value(cards[i].charAt(0));
    }
    value[0] = max;
  }
  
  private void onePair() {
    int max = 0;
    for(int i = 0; i < 5; ++i) {
      int first = value(cards[i].charAt(0));
      boolean found = false;
      for(int j = i+1; !found && j < 5; ++j)
        found = first == value(cards[j].charAt(0));
      if(found && first > max)
        max = first;
    }
    value[1] = max;
  }
  
  private void twoPair() {
    int firstPair = 0;
    boolean firstFound = false;
    for(int i = 0; !firstFound &&  i < 5; ++i) {
      int first = value(cards[i].charAt(0));
      boolean found = false;
      for(int j = i+1; !found && j < 5; ++j)
        found = first == value(cards[j].charAt(0));
      if(found) {
        firstPair = first;
        firstFound = true;
      }
    }
    if(firstFound) {
      int secondPair = 0;
      boolean secondFound = false;
      for(int i = 0; !secondFound && i < 5; ++i) {
        int first = value(cards[i].charAt(0));
        if(first != firstPair) {
          boolean found = false;
          for(int j = i+1; !found && j < 5; ++j)
            found = first == value(cards[j].charAt(0));
          if(found) {
            secondFound = true;
            secondPair = first;
          }
        }
      }
      if(secondFound) {
        value[2] = secondPair > firstPair ? secondPair : firstPair;
      } else {
        value[2] = 0;
      }
    } else {
      value[2] = 0;
    }
  }
  
  private void threeOfAKind() {
    int value = 0;
    boolean stop = false;
    for(int i = 0; !stop && i < 5; ++i) {
      int first = value(cards[i].charAt(0));
      boolean found = false;
      int j;
      for(j = i+1; !found && j < 5; ++j)
        found = first == value(cards[j].charAt(0));
      if(found) {
        j--;
        found = false;
        for(int k = j+1; !found && k < 5; ++k) {
          found = first == value(cards[k].charAt(0));
        }
        if(found) {
          value = first;
          stop = true;  
        }
          
      }
    }
    this.value[3] = value;
  }
  
  private void straight() {
    boolean stillStraight = true;
    for(int i=cards.length-1; stillStraight &&  i > 0; --i){
      stillStraight = value(cards[i].charAt(0)) - value(cards[i-1].charAt(0)) == 1;
    }
    if(stillStraight){
      value[4] = value(cards[cards.length-1].charAt(0));
    } else {
      value[4] = 0;
    }
  }
  
  private void flush() {
    boolean stillFlush = true;
    char suit = cards[0].charAt(1);
    for(int i = 1; stillFlush && i < cards.length; ++i)
      stillFlush = cards[i].charAt(1) == suit;
    if(stillFlush){
      value[5] = 1;
    } else {
      value[5] = 0;
    }
  }
  
  private void fullHouse() {
    if(value[3] != 0) {
      // there's a three of a kind look for a pair
      int three = value[3];
      int i = 0;
      while(value(cards[i].charAt(0)) == three) {
        i++;
      }
      // i points to the first card different from the three
      int first = value(cards[i].charAt(0));
      boolean found = false;
      for(int j = i+1;!found && j < 5; ++j)
        found = value(cards[j].charAt(0)) == first;
      if(found) {
        //there's a full House with the value of the three
        value[6] = three;
      } else {
        value[6] = 0;
      }
    } else {
      value[6] = 0;
    }
  }
  
  private void fourOfAKind() {
    // it's either the first or the second card helding the
    // value of the hypothetical four of a kind
    int value = 0;
    if(cards[0].charAt(0) == cards[1].charAt(0))
      value = value(cards[0].charAt(0));
    else
      value = value(cards[1].charAt(1));
    // count number of cards with the correct value
    int counter = 0;
    for(int i = 0; i < 5; ++i){
      if(value(cards[i].charAt(1)) == value) {
        counter++;
      }
    }
    if(counter == 4){
      this.value[7] = value;
    } else {
      this.value[7] = 0;
    }
  }
  
  private void straightFlush() {
    if(value[4] != 0 && value[5] != 0)
      //there's a straight and a flush
      value[8] = value[4];
    else 
      value[8] = 0;
  }
  
  private void royalFlush() {
    if(value[8] != 0 && value[0] == 13)
      // there's a straight flush and highest value is Ace
      value[9] = 1;
    else
      value[9] = 0;
  }
  
  private void execEvaluation() {
    Arrays.sort(cards, new Comparator<String>() {
      public int compare(String a, String b) {
        return value(a.charAt(0)) - value(b.charAt(0));
      }
    });
    highCard();
    onePair();
    twoPair();
    threeOfAKind();
    straight();
    flush();
    fullHouse();
    fourOfAKind();
    straightFlush();
    royalFlush();
    evaluated = true;
  }
  
  public Hand() {
    value = new int[10];
    cards = new String[5];
    cardsSize = 0;
    evaluated = false;
  }
  
  public void insertCard(String s) {
    if(cardsSize < 5) {
      if(s.length() > 2){
        s = "" + 'B' + s.charAt(s.length()-1);
      }
      cards[cardsSize] = s;  
      cardsSize++;
      if(cardsSize == 5) execEvaluation();
    }
  }
  
  
  public int compareTo(Hand h) {
    for(int i = value.length-1; i >= 0; --i)
      if(value[i] > h.value[i])
        return 1;
      else if(value[i] < h.value[i])
        return -1;
    // everything is equals... find out the first difference
    for(int i = cards.length -1; i >= 0; --i)
      if(cards[i].charAt(0) != h.cards[i].charAt(0))
        return value(cards[i].charAt(0)) - value(h.cards[i].charAt(0));
    // everything is equals
    return 0;
  }
  
  public String toString() {
    String res = "", sep = "";
    for(int i = 0; i < 5; ++i){
      res = res + sep + cards[i];
      sep = " ";
    }
    sep = "\n[ ";
    for(int i = 9; i >= 0; --i) {
      res = res + sep + value[i];
      sep = " , ";
    }
    return res + " ]";
  }
  
  public static void main(String argv[]) {
    Hand h1 = new Hand(), h2 = new Hand();
    String s1 = "2H 2D 4C 4D 4S";
    String s2 = "3C 3D 3S 9S 9D";
    String cards1[] = s1.split(" ");
    String cards2[] = s2.split(" ");
    for(int i = 0; i < cards1.length; ++i) {
      h1.insertCard(cards1[i]);
      h2.insertCard(cards2[i]);
    }
    System.out.println(h1 + "\n" + h2);
    System.out.println(h1.compareTo(h2));
  }
}

class Problem54 {
  private static final String NAME = "p054_poker.txt";
  public static void main(String argv[]) {
    try {
			BufferedReader in = new BufferedReader(new FileReader(NAME));
			String laststringread = in.readLine();
			int firstwin = 0;
			while(laststringread != null) {
			  String cards[] = laststringread.split(" ");
			  Hand h1 = new Hand();
			  Hand h2 = new Hand();
			  for(int i = 0; i < 5; ++i)
			    h1.insertCard(cards[i]);
			  for(int i = 5; i < 10; ++i) 
			    h2.insertCard(cards[i]);
			  //System.out.println(h1 + "\n" + h2);
			  //System.out.println(h1.compareTo(h2));
			  if(h1.compareTo(h2) > 0)
			    firstwin++;
				laststringread = in.readLine();
			}
			System.out.println(firstwin);
		} catch (Exception e) {
			throw new RuntimeException("Problem reading from file "+NAME+ "\n" + e);
		}
  }
}
