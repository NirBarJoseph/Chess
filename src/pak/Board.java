package pak;


import javax.rmi.CORBA.Util;

public class Board {

    byte[][] board;
    byte[][] pieces;

    public Board(){
        this.board = new byte[][] {
                {4,1,0,0,0,0,-1,-4},
                {2,1,0,0,0,0,-1,-2},
                {3,1,0,0,0,0,-1,-3},
                {5,1,0,0,0,0,-1,-5},
                {6,1,0,0,0,0,-1,-6},
                {3,1,0,0,0,0,-1,-3},
                {2,1,0,0,0,0,-1,-2},
                {4,1,0,0,0,0,-1,-4}
        };
        this.pieces = new byte[][] {
                {8,8},
                {2,2},
                {2,2},
                {2,2},
                {1,1},
                {1,1},
        };
    }

    public boolean set_piece(Location loc, boolean color, byte type, boolean flag) {
	    /*	if the input is invalid	*/
        if (loc.c > 7 || loc.r > 7 || loc.c < 0 || loc.r < 0) {
            Utils.printf("Invalid position on the board\n");
            return false;
        }
	    /*	if the input is invalid	*//*
        if (color != 0 && color != 1) {
            Utils.printf("Wrong Input");
            return false;
        }*/
        // if called from set mode a flag should be raised
        if (flag){
		/* limit check */
            if(!checkSetLimit(color, type)){
                Utils.printf("Setting this piece creates an invalid board\n" );
                return false;
            }
        }
        byte col = (byte) ((color) ? (1) : (0));
        if(type >= 0){
            this.pieces[type][col]++;
        } else { return false;}
	    /*	set the disc	*/
        this.board[loc.c][loc.r] = (byte) ((color) ? (-1) * (type + 1) : type + 1);

        return true;
    }

    public boolean remove_piece(Location loc, boolean flag){ return this.remove_piece(loc.r, loc.c, flag);}

    public boolean remove_piece(byte r, byte c, boolean flag){
        	/*	if the input is invalid	*/
        if ( (r > 7 || c > 7 || r < 0 || c < 0 || board[c][r] == 0) && flag ) {
            Utils.printf("Invalid position on the board\n");
            return false;
        }
        //piece count update
        if(board[c][r] == 1) pieces[0][0]--;
        else if(board[c][r] == -1) pieces[0][1]--;
        else if(board[c][r] == 2) pieces[1][0]--;
        else if(board[c][r] == -2) pieces[1][1]--;
        else if(board[c][r] == 3) pieces[2][0]--;
        else if(board[c][r] == -3) pieces[2][1]--;
        else if(board[c][r] == 4) pieces[3][0]--;
        else if(board[c][r] == -4) pieces[3][1]--;
        else if(board[c][r] == 5) pieces[4][0]--;
        else if(board[c][r] == -5) pieces[4][1]--;
        else if(board[c][r] == 6) pieces[5][0]--;
        else if(board[c][r] == -6) pieces[5][1]--;
        board[c][r] = 0;

        return true;
    }

    private boolean checkSetLimit(boolean color, byte type) {

        if (type == 0 && ((!color && this.pieces[0][0] == 8) || (color && this.pieces[0][1] == 8)))
            return false;
        else if (type == 1 && ((!color && this.pieces[1][0] == 2)|| (color && this.pieces[1][1] == 2)))
            return false;
        else if (type == 2 && ((!color && this.pieces[2][0] == 2)|| (color && this.pieces[2][1] == 2)))
            return false;
        else if (type == 3 && ((!color && this.pieces[3][0] == 2)|| (color && this.pieces[3][1] == 2)))
            return false;
        else if (type == 4 && ((!color && this.pieces[4][0] == 1)|| (color && this.pieces[4][1] == 1)))
            return false;
        else if (type == 5 && ((!color && this.pieces[5][0] == 1)|| (color && this.pieces[5][1] == 1)))
            return false;

        return true;
    }

    public boolean move_piece(Move move, boolean color, byte type, byte promotion, boolean check_valid){
        if (check_valid)
            if (!Logic.is_valid(move, type, color, true))
                return false;

        //move the piece
        /* remove "from" disc and possibly eaten enemy pieces */
        this.remove_piece(move.from, false);
        int toType = Main.board.get_piece(move.to);
        if (toType >=0){
            // update the pieces array if toType is piece
            this.remove_piece(move.to, false);
        }
        //set the piece in target
        //check if it's a promoted pawn
        if (type == 0 && move.to.r == 7*(color ? 0 : 1)) {
            type = promotion;
        }
        Main.board.set_piece(move.to, color , type, false);
        return true;
    }

    public void init_board(){
        this.board = new byte[][] {
                {4,1,0,0,0,0,-1,-4},
                {2,1,0,0,0,0,-1,-2},
                {3,1,0,0,0,0,-1,-3},
                {5,1,0,0,0,0,-1,-5},
                {6,1,0,0,0,0,-1,-6},
                {3,1,0,0,0,0,-1,-3},
                {2,1,0,0,0,0,-1,-2},
                {4,1,0,0,0,0,-1,-4}
        };
        this.pieces = new byte[][] {
                {8,8},
                {2,2},
                {2,2},
                {2,2},
                {1,1},
                {1,1},
        };
    }

    public void print(){

        print_line();
        for (int j = 7; j >=0 ; j--){
            System.out.format((j < 8 ? " %d" : "%d"), j + 1);
            for(int i = 0; i < 8; i++){
                System.out.format("| %c ", num_to_char(board[i][j]));
            }
            System.out.println("|");
            print_line();
        }
        System.out.print("   ");
        for(int j = 0; j < 8; j++) {
            System.out.format(" %c  ", (char) ('a' + j));
        }
        System.out.println("\n");
    }

    private void print_line(){
        int i;
        System.out.print("  |");
        for (i = 1; i < 8 * 4; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    /*
    This function returns the char representing a given abs piece
    x = means error
     */
    private char num_to_char(short num){
        if(num == 0) return ' ';
        else if(num == 1) return 'm';
        else if(num == -1) return 'M';
        else if(num == 2) return 'n';
        else if(num == -2) return 'N';
        else if(num == 3) return 'b';
        else if(num == -3) return 'B';
        else if(num == 4) return 'r';
        else if(num == -4) return 'R';
        else if(num == 5) return 'q';
        else if(num == -5) return 'Q';
        else if(num == 6) return 'k';
        else if(num == -6) return 'K';
        return 'x';
    }

    public void clear() {
        this.board = new byte[][] {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        this.pieces = new byte[][] {
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
        };
    }

    public boolean is_legel_start(){
        int i;
        // checks if there are two kings of different color to start the game
        if (pieces[5][0] != 1 || pieces[5][1]!= 1){
            Utils.printf("Wrong board initialization\n");
            return false;
        }
        // checks if the first player is set to state of tie
        if (Utils.isTie(Main.turn)){
            Utils.printf("First player Stuck!\n");
            return false;
        }
        //checks if there is a pawn at the edge of the board
        for(i = 0; i < 8; i++){
            if(board[i][0] == -1){
                Utils.printf("black pawn in <%c,1> should have been promoted\n", Character.toString((char)('a'+i)));
                return false;
            }
            if(board[i][7] == 1){
                Utils.printf("White pawn in <%c,8> should have been promoted\n", Character.toString((char)('a'+i)));
                return false;
            }
        }
        return true;

    }

    /*
    * This function return the piece value in a given location
    * i.e. the absolute value -1
    * -1 = empty
    * 0 = pawn
    * 1 = knight
    * 2 = bishop
    * 3 = rook
    * 4 = queen
    * 5 = king*/
    public byte get_piece(byte c, byte r) {
        return (byte) (Math.abs(this.board [c][r]) - 1);
    }

    public byte get_piece(Location loc){ return (byte) (Math.abs(this.board [loc.c][loc.r]) - 1);}

    /* checks for a mate / tie / check
    * 0 for nothing , 1 for check, 2 for tie, 3 for mate (mate = tie + check)
    */
    public byte CMT(boolean turn) {
        return (byte) ((this.is_check(turn)) + (this.is_tie(!turn)));
    }

    public byte is_tie(boolean turn) {
        return 0;
    }

    public byte is_check(boolean turn) {
        return 0;
    }
}
