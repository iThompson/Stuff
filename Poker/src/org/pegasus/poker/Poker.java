package org.pegasus.poker;

import java.util.Scanner;
import java.util.regex.Pattern;
 
/**
 * Deal a game of poker
 * @author Istvan
 * @version 1
 */
public class Poker
{
        public static void main(String[] args) throws InterruptedException
        {
                printText("WOULD YOU LIKE TO PLAY A GAME?\n");
                Thread.sleep(50);
               
                int score = 0;
               
                printText("COINS INSERTED - 5 ROUNDS & POINTS PURCHASED\n");
                Thread.sleep(1000);
                score += 5;
                int rounds = 5;
               
                while(score > 0 && rounds > 0)
                {
                        //hack to 'clear console'
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        printText("STARTING ROUND\n\n");
                        score += round() - 1;
                        rounds--;
                       
                }
               
                if(rounds == 0) printText("OUT OF ROUNDS\n");
                if(score <= 0) printText("SCORE BELOW 0\n");
               
                printText("GAME OVER. \nFINAL SCORE: " + score);
               
                /*TEST SCORING*/
                /*int[] pairTest = {1, 3, 21, 3, 9};
                System.out.println("Pair (5s): " + scoreHand(pairTest) + "\n");
               
                int[] doubleTest = {1, 3, 31, 9, 9};
                System.out.println("Pair (jacks): " + scoreHand(doubleTest) + "\n");
               
                int[] twoPairs = {1, 23, 23, 9, 9};
                System.out.println("Two Pairs: " + scoreHand(twoPairs) + "\n");
               
                int[] trippleTest = {1, 43, 9, 9, 9};
                System.out.println("Three of a kind: " + scoreHand(trippleTest) + "\n");
               
                int[] straight = {25, 0, 1, 3, 2};
                System.out.println("Straight (ace as 1): " + scoreHand(straight) + "\n");
               
                int[] straightHigh = {12, 24, 23, 22, 21};
                System.out.println("Straight (ace high): " + scoreHand(straightHigh) + "\n");
               
                int[] flush = {1, 3, 5, 11, 4};
                System.out.println("Flush: " + scoreHand(flush) + "\n");
               
                int[] fullHouse = {31, 27, 27, 27, 31};
                System.out.println("Full House: " + scoreHand(fullHouse) + "\n");
               
                int[] quadTest = {1, 23, 23, 23, 23};
                System.out.println("Four of a kind: " + scoreHand(quadTest) + "\n");
               
                int[] straightFlush = {1, 3, 5, 2, 4};
                System.out.println("Straight flush: " + scoreHand(straightFlush) + "\n");
               
                int[] royalFlush = {12, 11, 10, 9, 8};
                System.out.println("Royal flush: " + scoreHand(royalFlush) + "\n");*/
        }
       
        /**
         * Video-game-esque printing
         * @param text Text to print
         * @throws InterruptedException
         */
        private static void printText(String text) throws InterruptedException
        {
                for(int i = 0; i < text.length(); i++)
                {
                        System.out.print(text.charAt(i));
                        Thread.sleep(100);
                }
        }
       
        /**
         * Play a round
         * @return Score bonus
         * @throws InterruptedException
         */
        public static int round() throws InterruptedException
        {
                Cards cards = new Cards();
                cards.shuffle();
               
                int[] hand = cards.firstFiveCards();
                quickSort(hand); //to be nice
                cards.displayCards(hand);
               
                System.out.println("\nPLEASE ENTER A NUMBER (1-5) FOR CARDS YOU WOULD LIKE TO REPLACE SEPERATED BY SPACES.");
                System.out.println("PRESS ENTER TO CONTINUE");
                Scanner sc = new Scanner(System.in);
                Pattern delimiters = Pattern.compile(System.getProperty("line.separator")+"|\\s");
                sc.useDelimiter(delimiters);
               
                int nextInt;
                while(sc.hasNextInt())
                {
                        nextInt = sc.nextInt();
                        if(nextInt >= 0 && nextInt <= 5) hand[nextInt - 1] = cards.getTopCard();
                }
               
                //sc.close(); -- this closes System.in - method ending is good enough
               
                System.out.println("YOUR FINAL HAND:");
                cards.displayCards(hand);
                int score = scoreHand(hand);
               
                Thread.sleep(1000);
               
                return score;
        }
       
        /**
         * Format printing of scores
         * @param hand Hand type
         * @param score Score bonus
         * @throws InterruptedException
         */
        public static void printScore(String hand, int score) throws InterruptedException
        {
                printText("SCORE: " + hand + " - AWARDED: " + score + "\n");
        }
       
        /**
         * Score a hand
         * @param hand Cards to score
         * @return Point value of hand
         * @throws InterruptedException
         */
        public static int scoreHand(int[] hand) throws InterruptedException
        {       
                int[] valScore = scoreValues(hand);
                int suitScore = scoreSuit(hand);
                int points = 0;
               
                if(valScore[4] == 2 && suitScore > 0)
                {
                        points = 250; //royal flush
                        printScore("ROYAL FLUSH", 250);
                }
                else
                {
                        if(valScore[4] == 1 && suitScore > 0){
                                points = 50; //straight flush
                                printScore("STRAIGHT FLUSH", 50);
                        }
                        else
                        {
                                if(valScore[3] > 0)
                                {
                                        points = 25; //four of a kind
                                        printScore("FOUR OF A KIND", 25);
                                }
                                else
                                {
                                        if(valScore[0] > 0 && valScore[2] > 0)
                                        {
                                                points = 6; //full house
                                                printScore("FULL HOUSE", 6);
                                        }
                                        else
                                        {
                                                if(suitScore > 0)
                                                {
                                                        points = 5; //flush
                                                        printScore("FLUSH", 5);
                                                }
                                                else
                                                {
                                                        if(valScore[4] > 0)
                                                        {
                                                                points = 4; //straight
                                                                printScore("STRAIGHT", 4);
                                                        }
                                                        else
                                                        {
                                                                if(valScore[2] > 0)
                                                                {
                                                                        points = 3; //three of a kind
                                                                        printScore("THREE OF A KIND", 3);
                                                                }
                                                                else
                                                                {
                                                                        if(valScore[0] == 2)
                                                                        {
                                                                                points = 2; //two pair
                                                                                printScore("TWO PAIRS", 2);
                                                                        }
                                                                        else
                                                                        {
                                                                                if(valScore[1] > 0)
                                                                                {
                                                                                        points = 1; //pair of jacks or better
                                                                                        printScore("PAIR OF JACKS OR BETTER", 1);
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
                if(points == 0) printScore("NO VALUE", 0);
               
                return points;
        }
       
        /**
         * Helper to scoring - score using a value sort
         * @param hand Hand to score
         * @return Array defining scoring componenets of hand
         */
        private static int[] scoreValues(int[] hand)
        {
                int[] values = new int[hand.length];
                boolean aceFlag = false;
                for(int i = 0; i < hand.length; i++)
                {
                        values[i] = hand[i] % 13;
                        if(hand[i] % 13 == 12) aceFlag = true;
                }
               
                quickSort(values);
               
                int pairs = 0;
                int pairsJack = 0;
                int triplet = 0;
                int quad = 0;
                int straight = 0;
                int cnt = 1;
                int cntR = 1;
               
                for(int i = 0; i < values.length - 1; i++)
                {
                        //pairings
                        if((values[i] % 13) == (values[i + 1] % 13)) cnt++;
                        if (((values[i] % 13) != (values[i + 1] % 13) && cnt > 1) || i == values.length - 2)
                        {
                                if(cnt == 2) pairs++;
                                if(cnt == 2 && values[i] >= 9) pairsJack++;
                                else if(cnt == 3) triplet++;
                                else if(cnt == 4) quad++;
                                cnt = 1;
                        }
                       
                        //runs
                        if(values[i] % 13 == 0 && aceFlag) cntR++;
                       
                        if((values[i] % 13) == (values[i + 1] % 13) - 1) cntR++;
                        if (cntR == 5 && i == values.length - 2)
                        {
                                if(values[i + 1] == 12) straight++; //royal
                                straight++;
                        }
                }
                return new int[]{pairs, pairsJack, triplet, quad, straight};
        }
       
        /**
         * Score for flushes
         * @param hand Hand to score
         * @return flush (1 or 0)
         */
        private static int scoreSuit(int[] hand)
        {
                quickSort(hand);
               
                int flush = 0;
                int cnt = 1;
                for(int i = 0; i < hand.length - 1; i++)
                {
                        if(((hand[i] - (hand[i] % 13))/13) == ((hand[i + 1] - (hand[i + 1] % 13))/13)) cnt++;
                        if(i == hand.length - 2 && cnt == 5) flush++;
                }
               
                return flush;
        }
       
        /*MY OWN QUICKSORT ALGORITHM IMPLEMENTATION*/
       
        /**
         * Top level of quicksort
         * @param array Array to sort
         */
        public static void quickSort(int[] array)
        {
                quickSort(array, 0, array.length - 1);
        }
       
        /**
         * Sort a partition
         * @param array Array to sort
         * @param start Start of partition
         * @param end End of partition
         */
        public static void quickSort(int[] array, int start, int end)
        {
                //left and right partition indexes
                int lPartIndex = start;
                int rPartIndex = end;
               
                if(end - start >= 1)
                {
                        //at least two elements in array
                        int pivot = array[start]; //start from the left
                       
                        while(rPartIndex > lPartIndex)
                        {
                                while(array[lPartIndex] <= pivot && lPartIndex <= end && rPartIndex > lPartIndex)
                                        lPartIndex++; //left partition - find first element greater than pivot
                                while(array[rPartIndex] > pivot && rPartIndex >= start && rPartIndex >= lPartIndex)
                                        rPartIndex--; //right partition - first element not larger than pivot
                               
                                //if the partitions have not converged, swap
                                if(rPartIndex > lPartIndex) swap(array, lPartIndex, rPartIndex);
                        }
                        swap(array, start, rPartIndex);
                       
                        quickSort(array, start, rPartIndex - 1); //sort left partition
                        quickSort(array, rPartIndex + 1, end); //sort right partition
                }
                else return; //sort one element? that was easy.
        }
       
        /**
         * Swap values in array
         * @param array Array to swap within
         * @param index1 Index of first value
         * @param index2 Index of second value
         */
        public static void swap(int[] array, int index1, int index2)
        {
                int tmp = array[index1];
                array[index1] = array[index2];
                array[index2] = tmp;
        }
}