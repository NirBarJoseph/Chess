package pak;

/**
 * Created by user on 02-Jul-16.
 */
public class Logic {


    protected static boolean is_valid(Move move, byte type, boolean print_flag) {
        	/*	checks valid position	*/
//        if (mov.from.column > 7 || mov.from.column < 0 || mov.from.row > 7
//                || mov.from.row < 0) {
//            if (printFlag) print_message(WRONG_POSITION);
//            return 0;
//        }
//        if (mov.target.column > 7 || mov.target.column < 0 || mov.target.row > 7
//                || mov.target.row < 0) {
//            if (printFlag) print_message(WRONG_POSITION);
//            return 0;
//        }
//	/*	check that slot is not empty	*/
//        if (board[mov.from.column][mov.from.row] == 0) {
//            if (printFlag) print_message("The specified position does not contain your piece\n");
//            return 0;
//        }
//	/*	checks right color	*/
//        if (((board[mov.from.column][mov.from.row]<0) && col == 0) ||
//                ((board[mov.from.column][mov.from.row]>0) && col == 1)){
//            if (printFlag) print_message("The specified position does not contain your piece\n");
//            return 0;
//        }
//	/*	target must contain the opposite piece	*/
//        if ((board[mov.target.column][mov.target.row]<0 && col == 1) ||
//                (board[mov.target.column][mov.target.row]>0 && col == 0)){
//            if (printFlag) print_message(ILLEGAL_MOVE);
//            return 0;
//        }
//	/*	can't stay in the same place	*/
//        if ( (mov.from.column == mov.target.column) && (mov.from.row == mov.target.row) ){
//            if (printFlag) print_message(ILLEGAL_MOVE);
//            return 0;
//        }
//
//
//	/*	send to specific isValids per piece type*/
//        switch (type){
//            case(0) :
//                if( isValidPawn(mov, col))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//            case (1) :
//                if( isValidRook(mov))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//            case (2):
//                if( isValidKnight(mov))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//            case (3) :
//                if( isValidBishop(mov))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//            case (4) :
//                if( isValidQueen(mov))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//            case (5) :
//                if( isValidKing(mov))
//                    break;
//                else{
//                    if (printFlag) print_message(ILLEGAL_MOVE);
//                    return 0;
//                }
//        }
//
//        //check that the move doesn't put your own king at risk
//        if (isOwnCheck(mov, col, type)){
//            if (printFlag) print_message(ILLEGAL_MOVE);
//            return 0;
//        }
//
//        return 1;
        return false;
    }
}
