package pak;



/**
 * Created by user on 02-Jul-16.
 */
public class Flow {

    public static boolean game_setting_function(String user_input) {

        String[] args = user_input.split(" ");
        String cmd = args[0];

        if (cmd.equalsIgnoreCase("set"))
            return setting_set(args);
        if (cmd.equalsIgnoreCase("rm"))
            return setting_remove(args[1]);
        if (cmd.equalsIgnoreCase("difficulty"))
            return setting_change_difficulty(args[1]);
        if (cmd.equalsIgnoreCase("game_mode"))
            return setting_game_mode(args[1]);
        if (cmd.equalsIgnoreCase("user_color"))
            return setting_user_color(args[1]);
        if (cmd.equalsIgnoreCase("load"))
            return setting_load(args[1]);
        if (cmd.equalsIgnoreCase("next_player"))
            return setting_next_player(args[1]);
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

    private static boolean setting_next_player(String arg) {
        if (arg.equalsIgnoreCase("white")) Main.turn = false;
        else Main.turn = true;
        Utils.printf("Next player is %s\n", arg);
        return true;
    }

    private static boolean setting_load(String arg) {
        return true;
    }

    private static boolean setting_user_color(String arg) {
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

    private static boolean setting_game_mode(String arg) {
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

    private static boolean setting_change_difficulty(String arg) {
        if (Integer.parseInt(arg) > 5 || Integer.parseInt(arg) <= 0){
            Utils.printf("Invalid difficulty");
            return false;
        }
        Main.difficulty = Byte.parseByte(arg);
        Utils.printf("Difficulty changed to: %s\n", arg);
        return true;
    }

    private static boolean setting_remove(String arg) {
        Location loc = new Location(arg);
        if(!Main.board.rm(loc.r, loc.c, true)){
            return false;
        }
        Utils.printf("Removed\n");
        return true;
    }

    private static boolean setting_set(String[] args) {
        Location loc = new Location(args[1]);
        boolean color = args[2].equalsIgnoreCase("black");
        byte type = Utils.piece_to_num(args[3]);
        if (!Main.board.set_piece(loc, color, type, true)){
            return false;
        }
        Utils.printf("Requested disc has been set\n");
        return true;
    }



    public static boolean game_function(String user_input){

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
        return false;
    }

    private static int game_move(String[] args) {
        Location from = new Location(args[1]);
        Location to = new Location(args[3]);
        Move move = new Move(from, to);
        byte promotion = (args.length == 5) ?
                Utils.piece_to_num(args[4]) : (byte) 4;
        byte type = (Main.board.get_piece(from.c, from.r));
        boolean move_success = Logic.move_piece(move, type, promotion, true)
        return 0;
    }


}
