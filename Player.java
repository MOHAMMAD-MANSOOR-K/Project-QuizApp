import java.util.*;

class Player {
    String name;
    int prizeMoney = 0;
    boolean skipUsed = false;
    boolean lifelineUsed = false;

    Player(String name) {
        this.name = name;
    }
}

class Game {
    Scanner sc = new Scanner(System.in);

    String[] questions = {
        "what is the capital of India?",
        "which planet is known as the Red Planet?",
        "who is our prime minister?",
        "who is father of the Nation?",
        "who lift the world cup in 2011?",
        "who is the captain of 2011 worldcup Indian team?",
        "what is the nick name of virat kohli?",
        "who starts the Being human Brand in India?",
        "which ipl teams are having most IPL titles?",
        "what is national bird of India?"
    };

    String[][] options = {
        {"1. New Delhi", "2. Agra", "3. Bengaluru", "4. Mumbai"},
        {"1. Venus", "2. Mars", "3. Jupiter", "4. Saturn"},
        {"1. Narendra modi", "2. Nehru", "3. Rahul gandhi", "4. Ysr"},
        {"1. Mahatma Gandhi", "2. Nehru", "3. sonia gandhi", "4.Narendra modi"},
        {"1. Newzeland", "2. Australia", "3. England", "4. India"},
        {"1. Rohit sharma", "2. ViratKohli", "3. MS Dhoni", "4. Kapildev"},
        {"1. chikku", "2. Nehru", "3. Runmachine", "4. Chokli"},
        {"1. Salim Khan", "2. Chiranjeevi", "3. Salman khan", "4. Alia Batt"},
        {"1. CSK&RCB", "2. CSK&MI", "3. DELHI&MI", "4. RCB&LSG"},
        {"1. Peacock", "2. Tiger", "3. kiwi", "4. eagle"}
    };

    int[] answers = {1, 2, 1, 1, 4, 3, 1, 3, 2, 1};
    int[] rewards = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};

    void start(Player p) {
        System.out.println("\nWelcome " + p.name + " to the Quiz Game!");
        System.out.println("-----------------------------Rules:-------------------------------------");
        System.out.println("|1. Each correct answer adds money.                                     |");
        System.out.println("|2. You can skip ONE question.                                          |");
        System.out.println("|3. You have 30 seconds to answer each question.                        |");
        System.out.println("|4. You can use only one 50-50 lifeline.                                |");
        System.out.println("|5. If you give an incorrect answer, it will quit the game.             |");
        System.out.println("|6. You can quit anytime and keep your prize money.                     |");
        System.out.println("|Are you Ready? (yes/no)                                                |");
        System.out.println("-------------------------All the Best------------------------------------");

        String ready = sc.next();
        if (!ready.equalsIgnoreCase("yes")) {
            System.out.println("Game exited.");
            return;
        }

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            displayOptions(i);
            int choice = getAnswerInput();

            if (choice == -1) {
                System.out.println("Invalid input. Final prize: " + p.prizeMoney);
                return;
            }

            if (choice >= 1 && choice <= 4) {
                if (choice == answers[i]) {
                    p.prizeMoney += rewards[i];
                    System.out.println("Correct! Total prize: " + p.prizeMoney);
                } else {
                    System.out.println("Wrong! Final prize: " + p.prizeMoney);
                    return;
                }
            } else if (choice == 5) {
                System.out.println("Choose Lifeline:");
                if (!p.lifelineUsed) {
                    System.out.println("1. 50-50 Lifeline");
                } else {
                    System.out.println("1. 50-50 Lifeline (Used)");
                }

                if (!p.skipUsed) {
                    System.out.println("2. Skip Question");
                } else {
                    System.out.println("2. Skip Question (Used)");
                }

                int lifelineChoice = getAnswerInput();
                if (lifelineChoice == 1) {
                    if (!p.lifelineUsed) {
                        p.lifelineUsed = true;
                        useFiftyFifty(i);
                        i--; // re-ask the question
                    } else {
                        System.out.println("50-50 already used.");
                        i--;
                    }
                } else if (lifelineChoice == 2) {
                    if (!p.skipUsed) {
                        p.skipUsed = true;
                        System.out.println("Question skipped.");
                        continue; // skip to next question
                    } else {
                        System.out.println("Skip already used.");
                        i--;
                    }
                } else {
                    System.out.println("Invalid lifeline choice.");
                    i--;
                }
            } else if (choice == 6) {
                System.out.println("You chose to quit. Final prize: " + p.prizeMoney);
                return;
            } else {
                System.out.println("Invalid choice. Try again.");
                i--;
            }
        }

        System.out.println("Congratulations " + p.name + "! You won ₹" + p.prizeMoney);
    }

    void displayOptions(int qIndex) {
        for (int i = 0; i < 4; i++) {
            System.out.println(options[qIndex][i]);
        }
        System.out.println("5. Use Lifeline");
        System.out.println("6. Quit the Game");
    }

    void useFiftyFifty(int questionIndex) {
        int correct = answers[questionIndex];
        List<Integer> wrongs = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            if (i != correct) wrongs.add(i);
        }
        Collections.shuffle(wrongs);
        int wrongToKeep = wrongs.get(0);

        System.out.println("Remaining options:");
        System.out.println(options[questionIndex][correct - 1]);
        System.out.println(options[questionIndex][wrongToKeep - 1]);
    }

    int getAnswerInput() {
        try {
            System.out.print("You have 30 seconds. Enter your choice: ");
            return sc.nextInt();
        } catch (Exception e) {
            sc.next(); // consume invalid input
            return -1;
        }
    }
}


