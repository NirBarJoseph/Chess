package pak;



/**
 * Created by user on 02-Jul-16.
 */
public class Flow {

    public static boolean game_settings_function(String user_input) {

        String[] args = user_input.split(" ");
        String cmd = args[0];

        if (cmd.equalsIgnoreCase("set"))
            return settings_set(args);
        if (cmd.equalsIgnoreCase("rm"))
            return settings_remove(args[1]);
        if (cmd.equalsIgnoreCase("difficulty"))
            return settings_change_difficulty(args[1]);
        if (cmd.equalsIgnoreCase("game_mode"))
            return settings_game_mode(args[1]);
        if (cmd.equalsIgnoreCase("user_color"))
            return settings_user_color(args[1]);
        if (cmd.equalsIgnoreCase("load"))
            return settings_load(args[1]);
        if (cmd.equalsIgnoreCase("next_player"))
            return settings_next_player(args[1]);
        if (cmd.equalsIgnoreCase("print"))
            Main.board.print();
        if (cmd.equalsIgnoreCase("clear"))
            Main.board.clear();
            Utils.printf("Board cleared\n");
        if (cmd.equalsIgnoreCase("start"))
            if (Main.board.is_legel_start())
                Main.play = true;

        Utils.printf("Invalid command");
        return true;
    }

    private static boolean settings_next_player(String arg) {
        if (arg.equalsIgnoreCase("white")) Main.turn = false;
        else Main.turn = true;
        Utils.printf("Next player is %s\n", arg);
        return true;
    }

    private static boolean settings_load(String arg) {
        return true;
    }

    private static boolean settings_user_color(String arg) {
        if( ! Main.game_mode){
            Utils.printf("Illegal command");
            return false;
        } else {
            if (arg.equalsIgnoreCase("white")) Main.user_color = false;
            else Main.user_color = true;
            Utils.printf("User color was set to %s\n", arg);
            return true;
        }

    }

    private static boolean settingss_game_mode(String arg) {
        try{
            int num = Integer.parseInt(arg);
            if(num == 1){
                Main.game_mode = true;
                Utils.printf("Player VS. AI mode");
                return true;
            } else if(num == 2){
                Main.game_mode = false;
                Utils.printf("2 player mode");
                return true;
            }
            Utils.printf("Wrong game mode input");
            return false;
        } catch (Exception e){
            Utils.printf("Wrong game mode input");
            return false;
        }
    }

    private static boolean settingss_change_difficulty(String arg) {
        if (Integer.parseInt(arg) > 5 || Integer.parseInt(arg) <= 0){
            Utils.printf("Invalid difficulty");
            return false;
        }
        Main.difficulty = Byte.parseByte(arg);
        Utils.printf("Difficulty changed to: %s\n", arg);
        return true;
    }

    private static boolean settingss_remove(String arg) {
        Location loc = new Location(arg);
        if(!Main.board.remove_piece(loc, true)){
            return false;
        }
        Utils.printf("Removed\n");
        return true;
    }

    private static boolean settingss_set(String[] args) {
        Location loc = new Location(args[1]);
        boolean color = args[2].equalsIgnoreCase("black");
        byte type = Utils.piece_to_type(args[3]);
        if (!Main.board.set_piece(loc, color, type, true)){
            return false;
        }
        Utils.printf("Requested piece has been set\n");
        return true;
    }



    public static byte game_function(String user_input){

        String[] args = user_input.split(" ");
        String cmd = args[0];

        if (cmd.equalsIgnoreCase("move"))
            return game_move(args);
        if (cmd.equalsIgnoreCase("get_move"))
            return game_get_move();
        if (cmd.equalsIgnoreCase("get_moves"))
            return game_get_moves();
        if (cmd.equalsIgnoreCase("save"))
            return game_save();
        if (cmd.equalsIgnoreCase("get_score"))
            return get_score();
        if (cmd.equalsIgnoreCase("main"))

        Utils.printf("Invalid command");
        return 0;
    }

    private static byte game_move(String[] args) {
        Location from = new Location(args[1]);
        Location to = new Location(args[3]);
        Move move = new Move(from, to);
        // did the player asked for promotion? default = queen
        byte promotion = (args.length == 5) ?
                Utils.piece_to_type(args[4]) : (byte) 4;
        byte type = (Main.board.get_piece(from.c, from.r));
        boolean move_success = Main.board.move_piece(move, Main.turn, type, promotion, true);
        if (move_success){
            //check if the move leads to check/tie/mate
            switch (Main.board.CMT(Main.turn)){
                case (1) :
                    Utils.printf ("Check!\n");
                    Main.turn = !Main.turn;
                    return 2;
                case (2) :
                    Utils.printf ("The game ends in a tie\n");
                    Main.turn = !Main.turn;
                    return 3;
                case (3) :
                    Utils.printf ("Mate! %s player wins the game\n", (Main.turn) ? "Black" : "White");
                    return 4;
            }
            Main.turn = !Main.turn;
            return 1;
        }
        return 0;
    }


}
