package com.epam.rd.autocode.startegy.cards;

import java.util.*; 


public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int playersCount) {
                Map<String, List<Card>> stacks = new LinkedHashMap<>();

                // Initialize each player's hand
                for (int i = 0; i < playersCount; i++) {
                    stacks.put("Player " + (i + 1), new ArrayList<>());
                }

                // Deal two private cards to each player, one at a time
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < playersCount; j++) {
                        stacks.get("Player " + (j + 1)).add(deck.dealCard());
                    }
                }

                // Deal five community cards
                stacks.put("Community", dealCards(deck, 5));

                // Put the remaining cards in the "Remaining" stack
                stacks.put("Remaining", deck.restCards());

                return stacks;
            }

            private List<Card> dealCards(Deck deck, int count) {
                List<Card> cards = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    cards.add(deck.dealCard());
                }
                return cards;
            }
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int playersCount) {
                Map<String, List<Card>> stacks = new LinkedHashMap<>();

                // Initialize each player's hand
                for (int i = 0; i < playersCount; i++) {
                    stacks.put("Player " + (i + 1), new ArrayList<>());
                }

                // Deal five private cards to each player, one at a time
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < playersCount; j++) {
                        stacks.get("Player " + (j + 1)).add(deck.dealCard());
                    }
                }

                // Put the remaining cards in the "Remaining" stack
                stacks.put("Remaining", deck.restCards());

                return stacks;
            }
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int playersCount) {
                Map<String, List<Card>> stacks = new LinkedHashMap<>();

                // Initialize each player's hand
                for (int i = 0; i < playersCount; i++) {
                    stacks.put("Player " + (i + 1), new ArrayList<>());
                }

                // Deal 13 private cards to each player, one at a time
                for (int i = 0; i < 13; i++) {
                    for (int j = 0; j < playersCount; j++) {
                        stacks.get("Player " + (j + 1)).add(deck.dealCard());
                    }
                }

                return stacks;
            }
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int playersCount) {
                Map<String, List<Card>> stacks = new LinkedHashMap<>();

                // Initialize each player's hand
                for (int i = 0; i < playersCount; i++) {
                    stacks.put("Player " + (i + 1), new ArrayList<>());
                }

                // Deal 6 private cards to each player, one at a time
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < playersCount; j++) {
                        stacks.get("Player " + (j + 1)).add(deck.dealCard());
                    }
                }

                // Reveal the top card of the remaining deck as the trump card
                Card trumpCard = deck.dealCard();
                stacks.put("Trump card", Arrays.asList(trumpCard));

                // Put the remaining cards in the "Remaining" stack
                stacks.put("Remaining", deck.restCards());

                return stacks;
            }
        };
    }

}

 
