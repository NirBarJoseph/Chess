package pak;


public class Utils {

//    public static void print(String to_print){
//        System.out.print(to_print);
//    }

    public static void printf(String to_print, String... args){
        if (args != null) {
            System.out.format(to_print, (Object) args);
        } else {
            System.out.format(to_print);
        }
    }

    public static byte piece_to_type(String piece){
        if (piece.equalsIgnoreCase("pawn"))    return 0;
        if (piece.equalsIgnoreCase("knight"))  return 1;
        if (piece.equalsIgnoreCase("bishop"))  return 2;
        if (piece.equalsIgnoreCase("rook"))    return 3;
        if (piece.equalsIgnoreCase("queen"))   return 4;
        if (piece.equalsIgnoreCase("king"))    return 5;
        return -1;
    }

    public static boolean isTie(boolean turn){
//        for (int i =0 ; i < 8 ; i++){
//            for (int j =0 ; j < 8 ; j++){
//                //check only places that got the right color piece)
//                if ( (board[i][j] > 0 && col == 0) || (board[i][j] < 0 && col == 1) ){
//                    loc occupiedLoc = createLoc(i,j);
//                    int type = abs(board[i][j]) -1;
//                    moveNode* currList = getMoves(occupiedLoc, col, type);
//                    if (currList != NULL){
//                        //if a move was found it isn't a tie
//                        delMoveList(currList);
//                        return 0;
//                    }
//                }
//            }
//        }
        return true;
    }

}

class InvalidParameters extends Exception
{
    //Parameterless Constructor
    public InvalidParameters() {}

    //Constructor that accepts a message
    public InvalidParameters(String message)
    {
        super(message);
    }
}