package org.pegasus.poker;

/***Cards CLASS***/

import java.util.ArrayList;

/**
* Represent/handle a deck of cards
* @author Istvan
* @version 1
*/
public class Cards
{
       private String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
       private static String[] faces = {"Jack", "Queen", "King", "Ace"};
      
       private ArrayList<Integer> cards = new ArrayList<Integer>(52);
      
       /*ACCESS TO THE ABOVE PIVS SHOULD BE DONE THROUGH THE FOLLOWING METHODS.
        * NO GETTERS OR SETTERS PROVIDED.*/
      
       /**
        * Construct Cards
        * Fill cards
        */
       public Cards()
       {
               for(int i = 0; i < 52; i++) cards.add(i);
       }
      
       /**
        * Return a string representation of a card by ID
        * @param card Card ID between 0 and 51 inclusive
        * @return Card name
        */
       public static String[] getCard(int card)
       {
               int value = card % 13;
               String valStr = ((value < 9 ? value+2 : faces[value-9]) + "");
               String suit = ((card - value)/13 + "");
               String[] ret = {valStr, suit};
               return ret;
       }
      
       /**
        * Generate a permutation
        * @return Permutation
        */
	public void shuffle()
       {
              
               ArrayList<Integer> localNum = (ArrayList<Integer>) cards.clone();
               cards.clear();
              
               for(int i = 0; i < localNum.size(); i++){
                       cards.add((int) localNum.remove(
                               (int)(Math.random() * localNum.size())));
               }
       }
      
       /**
        * Get top 5 cards
        * @return top 5 cards
        */
       public int[] firstFiveCards()
       {
               int[] ret = new int[5];
              
               for(int i = 0; i < 5; i++) ret[i] = cards.remove(0);
               return ret;
       }
      
       /**
        * Get top card
        * @return top card
        */
       public int getTopCard()
       {
               return cards.remove(0);
       }
      
       /*DISPLAY*/
      
       /**
        * Display array of cards
        * @param cards cards (0-51) to display
        */
       public void displayCards(int[] cards)
       {
               String[] buffer = new String[9];
               for(int i = 0; i < buffer.length; i++) buffer[i] = "";
              
               for(int card : cards)
               {
                       String[] cardTxt = generateCard(card);
                      
                       for(int i = 0; i < cardTxt.length; i++)
                               buffer[i] += cardTxt[i];
               }
              
               for(String line : buffer) System.out.println(line);
       }
      
       /**
        * Create ASCII art cards
        * @param cardNum Card number to print (0-51)
        * @return Array of lines for the card
        */
       public String[] generateCard(int cardNum)
       {
               String[][] suits = new String[4][5];
               suits[0] = new String[] {"_ _","/ ^ \\","\\   /","\\ /","`"}; //heart
               suits[1] = new String[] {" ", " /\\", " <  >", " \\/", " "}; //diamond
               suits[2] = new String[] {",", "/ \\", "(_ _)", "/_\\", " "}; //spade
               suits[3] = new String[] {"_", "(_)", "(_)_)", "/_\\", " "}; //club
              
               String spaces = "          ";
              
               String[] card = getCard(cardNum);
               int suit = Integer.parseInt(card[1]);
              
               String[] ret = new String[9];
              
               ret[0] = ("/         \\");
              
               ret[1] = ("| " + card[0] + spaces.substring(0, 8-card[0].length()) + "|");
              
               ret[2] = ("|"+ spaces.substring(0, (9 - suits[suit][0].length())/2)+suits[suit][0]
                               + spaces.substring(0, (9 - suits[suit][0].length())/2)+"|");
              
               ret[3] = ("|" + spaces.substring(0, (9 - suits[suit][1].length())/2)+suits[suit][1]
                               + spaces.substring(0, (9 - suits[suit][1].length())/2)+"|");
              
               ret[4] = ("|" + spaces.substring(0, (9 - suits[suit][2].length())/2)+suits[suit][2]
                               + spaces.substring(0, (9 - suits[suit][2].length())/2)+"|");
              
               ret[5] = ("|" + spaces.substring(0, (9 - suits[suit][3].length())/2)+suits[suit][3]
                               + spaces.substring(0, (9 - suits[suit][3].length())/2)+"|");
              
               ret[6] = ("|" + spaces.substring(0, (9 - suits[suit][4].length())/2)+suits[suit][4]
                               + spaces.substring(0, (9 - suits[suit][4].length())/2)+"|");
              
               ret[7] = ("|" + spaces.substring(0, 8-card[0].length()) + card[0] +" |");
              
               ret[8] = ("\\_________/");
              
               return ret;
       }
}