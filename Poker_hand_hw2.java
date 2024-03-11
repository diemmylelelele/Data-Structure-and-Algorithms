/* Homework 2
 * Name: Le Thi Diem My
 * Student ID: 220050
 */
//package com.gradescope.cs201; 
import java.util.Arrays;
public class Poker_hand_hw2 {
    private String[] cards;
    public Poker_hand_hw2(String cards[]) {
        if (cards.length != 5) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < cards.length; i++) {
            if (!cards[i].matches("^(10|[2-9JQKA])[HDSC]$")) {
                throw new IllegalArgumentException();
            }
        }
        this.cards = cards;
    }

    public int get_category() {
        if (StraightFlush()) {
            return 9;
        } else if (FourOfAKind()) {
            return 8;
        } else if (FullHouse()) {
            return 7;
        } else if (Flush()) {
            return 6;
        } else if (Straight()) {
            return 5;
        } else if (ThreeOfAKind()) {
            return 4;
        } else if (TwoPair()) {
            return 3;
        } else if (OnePair()) {
            return 2;
        } else {
            return 1;
        }
    }

    private boolean OnePair() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return (ranks[0] == ranks[1] || ranks[1] == ranks[2] || ranks[2] == ranks[3] || ranks[3] == ranks[4]);
    }

    private boolean TwoPair() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return ((ranks[0] == ranks[1] && ranks[2] == ranks[3]) ||
                (ranks[0] == ranks[1] && ranks[3] == ranks[4]) ||
                (ranks[1] == ranks[2] && ranks[3] == ranks[4]));
    }

    private boolean ThreeOfAKind() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return (ranks[0] == ranks[2] || ranks[1] == ranks[3] || ranks[2] == ranks[4]);
    }

    private boolean Straight() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return ((ranks[4] - ranks[0] == 4) || (ranks[0] == 14 && ranks[1] == 2 && ranks[2] == 3 && ranks[3] == 4 && ranks[4] == 5));
    }

    private boolean Flush() {
        char suit = cards[0].charAt(cards[0].length() - 1);
        for (String card : cards) {
            if (card.charAt(card.length() - 1) != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean FullHouse() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return ((ranks[0] == ranks[2] && ranks[3] == ranks[4]) || (ranks[0] == ranks[1] && ranks[2] == ranks[4]));
    }

    private boolean FourOfAKind() {
        int[] ranks = getRanks();
        Arrays.sort(ranks);
        return ((ranks[0] == ranks[3]) || (ranks[4] == ranks[1]));
    }

    private boolean StraightFlush() {
        return (Flush() && Straight());
    }
    public int compare_to(Poker_hand_hw2 another_hand){
        int first_category=this.get_category();
        int second_category=another_hand.get_category();
        if(first_category>second_category){
            return 1;
        }else if( second_category>first_category){
            return -1;
        }else{
            // Compare highest rank of Straight Flush and Straight category
            if(first_category==9|| second_category==5){
                int hand1_highest_rank=getHighestCardRank();
                int hand2_highest_rank=another_hand.getHighestCardRank();
                return Integer.compare(hand1_highest_rank, hand2_highest_rank);    
            }else if(first_category==6|| first_category==1){
                // Compare rank of Flush and High card category
                int []ranks_1 =getRanks();
                int []ranks_2=another_hand.getRanks();
                Arrays.sort(ranks_1);
                Arrays.sort(ranks_2);
                for(int i=ranks_1.length-1;i>=0; i--){
                    if(ranks_1[i]>ranks_2[i]){
                        return 1;
                    }else if(ranks_1[i]<ranks_2[i]){
                        return -1;
                    }

                }return 0;

            }else if(first_category==2|| first_category==3){
                int hand1_pair_value=getPairValue();
                int hand2_pair_value=another_hand.getPairValue();
                // Compare pair value of each hands
                if(hand1_pair_value<hand2_pair_value){
                    return -1;
                }else if(hand1_pair_value>hand2_pair_value){
                    return 1;
                }else{
                    int []ranks_1 =getRanks();
                    int []ranks_2=another_hand.getRanks();
                    Arrays.sort(ranks_1);
                    Arrays.sort(ranks_2);
                    for(int i=ranks_1.length-1;i>=0; i--){
                        if(ranks_1[i]>ranks_2[i]){
                            return 1;
                        }else if(ranks_1[i]<ranks_2[i]){
                            return -1;
                        }

                    }return 0;                

                }
                
            }else if( first_category==4){
                // Compare ranking of three of a kind category
                int hand1_triplet_value=getTripletValue();
                int hand2_triplet_value=another_hand.getTripletValue();
                if(hand1_triplet_value<hand2_triplet_value){
                    return -1;
                }else if(hand2_triplet_value<hand1_triplet_value){
                    return 1;
                }else{
                    int[] ranks_1=getRanks();
                    int[] ranks_2=another_hand.getRanks();
                    for(int i=ranks_1.length-1;i>=0; i--){
                        if(ranks_1[i]>ranks_2[i]){
                            return 1;
                        }else if(ranks_1[i]<ranks_2[i]){
                            return -1;
                        }
                    }return 0;                          
                }
                
            }else if(first_category==8){
                // compare rank of four of a kind category
                int hand1_pair_four=getPairFour();
                int hand2_pair_four=another_hand.getPairFour();
                if(hand1_pair_four<hand2_pair_four){
                    return -1;
                }else if(hand2_pair_four<hand1_pair_four){
                    return 1;
                }else{
                    int[] ranks_1=getRanks();
                    int[] ranks_2=another_hand.getRanks();
                    for(int i=ranks_1.length-1;i>=0; i--){
                        if(ranks_1[i]>ranks_2[i]){
                            return 1;
                        }else if(ranks_1[i]<ranks_2[i]){
                            return -1;
                        }
                    }return 0;                          
                }
                                
            }else {
                // Compare rank of Full house category
                int hand1_triplet_value=getTripletValue();
                int hand2_triplet_value=another_hand.getTripletValue();
                if(hand1_triplet_value>hand2_triplet_value){
                    return 1;
                }else if(hand1_triplet_value<hand2_triplet_value){
                    return -1;
                }else{
                    // Compare rank of two pair
                    int[] ranks_1=getRanks();
                    int[] ranks_2=another_hand.getRanks();
                    for(int i=ranks_1.length-1;i>=0; i--){
                        if(ranks_1[i]>ranks_2[i]){
                            return 1;
                        }else if(ranks_1[i]<ranks_2[i]){
                            return -1;
                        }
                    }return 0;                          

                }         
            }

        }
    }
    private int getPairFour(){
        int[] ranks=getRanks();
        Arrays.sort(ranks);
        if(ranks[0]==ranks[3]){
            return ranks[0];
        }else{
            return ranks[3];
        }
    }
    private int getTripletValue(){
        int [] ranks=getRanks();
        Arrays.sort(ranks);
        if(ranks[0]==ranks[2]){
            return ranks[0];
        }else{
            return ranks[2];
        }
    }
    private int getPairValue(){
        int [] ranks=getRanks();
        Arrays.sort(ranks);
        if(ranks[0]==ranks[1]){
            return ranks[0];
        }else if(ranks[1]==ranks[2]){
            return ranks[1];
        }else if(ranks[2]==ranks[3]){
            return ranks[2];
        }else{
            return ranks[3];
        }
    }
    // Get the highest rank of Straiht Flush and Straight category
    private int getHighestCardRank(){
        int [] ranks=getRanks();
        Arrays.sort(ranks);
        return ranks[4];
    }
    private int[] getRanks() {
        int[] ranks = new int[5];
        for (int i = 0; i < 5; i++) {
            String card = cards[i].substring(0, cards[i].length() - 1);
            switch (card) {
                case "A":
                    ranks[i] = 14;
                    break;
                case "K":
                    ranks[i] = 13;
                    break;
                case "Q":
                    ranks[i] = 12;
                    break;
                case "J":
                    ranks[i] = 11;
                    break;
                case "10":
                    ranks[i] = 10;
                    break;
                default:
                    ranks[i] = Integer.parseInt(card);
            }
        }
        return ranks;
    }    
    public static void main(String[] args) {
        String[] cards ={"5S", "5C", "3S", "3D", "QC"};
        Poker_hand_hw2 hand_1 = new Poker_hand_hw2(cards);
        String[] another_cards = {"5S", "5C", "4D", "4H", "10H"};
        Poker_hand_hw2 hand_2 = new Poker_hand_hw2(another_cards);
        System.out.println(hand_1.compare_to(hand_2));      
    }
}     