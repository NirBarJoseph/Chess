package pak;

/**
 * Created by user on 02-Jul-16.
 */
public class Logic {


    protected static boolean is_valid(Move move, byte type, boolean color, boolean print_flag) {
        /*	checks valid position	*/
        if (move.from.c > 7 || move.from.c < 0 || move.from.r > 7
                || move.from.r < 0) {
            if (print_flag) Utils.printf("Wrong position");
            return false;
        }
        else if (move.to.c > 7 || move.to.c < 0 || move.to.r > 7
                || move.to.r < 0) {
            if (print_flag) Utils.printf("Wrong position");
            return false;
        }
	    /*	check that slot is not empty	*/
        else if (Main.board.get_piece(move.from) == -1) {
            if (print_flag) Utils.printf("The specified position does not contain your piece\n");
            return false;
        }
	    /*	checks right color	*/
        else if (((Main.board.board[move.from.c][move.from.r]<0) && !color) ||
                ((Main.board.board[move.from.c][move.from.r]>0) && color)){
            if (print_flag) Utils.printf("The specified position does not contain your piece\n");
            return false;
        }
	    /*	to must contain the opposite piece	*/
        else if ((Main.board.board[move.to.c][move.to.r]<0 && color) ||
                (Main.board.board[move.to.c][move.to.r]>0 && !color)){
            if (print_flag) Utils.printf("Illegal Move\n");
            return false;
        }
	    /*	can't stay in the same place	*/
        else if ( (move.from.c == move.to.c) && (move.from.r == move.to.r) ){
            if (print_flag)Utils.printf("Illegal Move\n");
            return false;
        }


	    /*	send to specific isValids per piece type*/
        switch (type){
            case(0) :
                if( isValidPawn(move, color))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
            case (1) :
                if( isValidRook(move))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
            case (2):
                if( isValidKnight(move))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
            case (3) :
                if( isValidBishop(move))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
            case (4) :
                if( isValidQueen(move))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
            case (5) :
                if( isValidKing(move))
                    break;
                else{
                    if (print_flag) Utils.printf("Illegal Move\n");
                    return false;
                }
        }

        //check that the move doesn't put your own king at risk
        if (isOwnCheck(move, color, type)){
            if (print_flag) Utils.printf("Illegal Move\n");
            return false;
        }

        return true;
    }
}
