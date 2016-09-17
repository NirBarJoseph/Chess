package pak;



import java.util.Scanner;

public class Main {

    public static Board     board = new Board();
    public static boolean   turn = true; //true for black
    public static boolean   user_color = true; //true for AI
    public static boolean   game_mode = true; //true for AI vs. player
    public static byte      difficulty = 2;
    public static boolean   play = false; //false for settings

    public static void  main(String[] args){

        boolean no_one_won = true;
        Scanner scn = new Scanner(System.in);

        board.print();

        Utils.printf("Welcome to Chess\n");


        String user_input;

        while (no_one_won){

            if (!play){
                Utils.printf("Enter_Settings:\n");
                user_input = scn.nextLine();
                if (user_input.equalsIgnoreCase("quit")){
                    Utils.printf("Bye\n");
                    break;
                } else {
                    Flow.game_setting_function(user_input);
                }
            } else {
                //AI
                if (game_mode && !(user_color == turn)){
                    //TODO minimax
                    board.move_piece();
                    Utils.printf("AI Move:\n");
                    //TODO print move

                }

                //player
                Utils.printf("%s player - enter your move:\n", (turn) ? "White" : "Black");
                //get input from console
                user_input = scn.nextLine();
                if (user_input.equalsIgnoreCase("quit")){
                    Utils.printf("Bye\n");
                    break;
                } else {
                    switch (Flow.game_function(user_input)){
                        case -1: Utils.printf("Error in game functions");
                            System.exit(-1);
                            break;
                        case 0: Utils.printf("Player Move:\n");
                            break;
                        default: Utils.printf("Someone won!\n");
                            no_one_won = false;
                            break;
                    }
                }
            }

            board.print();


        }




    }






}
