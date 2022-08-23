import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class KnightsProblem extends Problem {
//
static Random random = new Random();

    public static KnightsProblemState genBoard(int maxQueens, int maxKnights, int maxStamina,int boardLength){
        int knightsCount = random.nextInt(maxKnights) + 1;
        int queensCount = random.nextInt(maxQueens) + 1;
        byte [][] board = new byte[boardLength][boardLength];
        // generate knights

        return new KnightsProblemState(board,
                generateQueens(queensCount,board),
                generateKnights(maxStamina,knightsCount,board)
                );
        }

    private static Queen[] generateQueens(int queensCount, byte [][] board) {
        int queensIndex = 1;
        Queen[] queens = new Queen[queensCount];
        for(int i=0;i<queensCount;) {
            int x = random.nextInt(board.length);
            int y = random.nextInt(board.length);
            if (board[x][y] == 0) {
                int queenId = (queensIndex * -1);
                queens[queensIndex - 1] = new Queen(x, y,queenId);
                board[x][y] = (byte) queenId;
                i++;queensIndex++;
            }
        }

        return queens;
    }

    private static Knight[] generateKnights(int maxStamina,int knightsCount,byte [][] board) {
        int knightsIndex = 1;
        Knight [] knights = new Knight[knightsCount];
        for(int i=0;i<knightsCount;) {
            int x = random.nextInt(board.length);
            int y = random.nextInt(board.length);
            if (board[x][y] == 0) {
//                System.out.println(knightsIndex);
                int stamina = random.nextInt(maxStamina) + 1;
                knights[knightsIndex - 1] = new Knight(x, y, knightsIndex, stamina);
                board[x][y] = (byte) knightsIndex;
                i++;knightsIndex++;
            }
        }
        return  knights;
}

    protected void genStateSpace(KnightsProblemState currentState) {
        this.stateSpace = new HashMap();
        Knight[] knights = currentState.knights;
        for(Knight k : knights){
            stateSpace.putAll(allPossibleStates(k,currentState));
        }

    }

    private HashMap<String,State> allPossibleStates(Knight knight,KnightsProblemState currentState){
        byte[][] possibleMovements = new byte [][]{
                {1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}
        };

        HashMap<String,State> possibleStates = new HashMap<>();
        for(byte[] movement : possibleMovements){
           if( applicableMovement(movement,knight,currentState.state)){
               KnightsProblemState kpState = new KnightsProblemState(currentState);
               String action =  moveKnight(kpState,knight,movement);
               possibleStates.put(action,kpState);
           }
        }

        KnightsProblemState kpState = new KnightsProblemState(currentState);
        for(Knight k : kpState.knights)
            k.stamina+=1;
        String knightsStings= "";
        for(Knight k : kpState.knights)
            knightsStings+=k.toString()+" ";
        possibleStates.put("inspire "+knightsStings,kpState);
        return possibleStates;
    }

    private String  moveKnight(KnightsProblemState kpState, Knight knight,byte[] movement) {
        String action = "";
        Knight kpStateKnight =  kpState.knights[knight.i-1];
        kpState.state[kpStateKnight.x][kpStateKnight.y]=0; //change cell to empty
        kpStateKnight.x= (byte) (kpStateKnight.x+movement[0]);
        kpStateKnight.y= (byte) (kpStateKnight.y+movement[1]);
        kpStateKnight.stamina-=1;
        if(kpState.state[kpStateKnight.x][kpStateKnight.y] == 0) {
            action = String.format("MoveKnight %s from  %s,%s to %s,%s", kpStateKnight.i,knight.x,knight.y,kpStateKnight.x,kpStateKnight.y);
        }else{
            byte queenIndex = kpState.state[kpStateKnight.x][kpStateKnight.y];
            Queen[] newQueens ;
            newQueens =  Arrays.stream(kpState.queens)
                    .filter(queen ->
                         queen.i != queenIndex
                    ).toArray(Queen[]::new);
            kpState.queens = newQueens;
            action = String.format("MoveKnight %s from %s,%s to %s,%s ;Kill Queen", kpStateKnight.i,knight.x,knight.y,kpStateKnight.x,kpStateKnight.y);

            }

        kpState.state[kpStateKnight.x][kpStateKnight.y] = kpStateKnight.i;
        return  action;
        }


    private boolean applicableMovement(byte[] movement, Knight k, byte[][]board){
        if(k.x + movement[0] > board.length-1 || k.x + movement[0]< 0
                || k.y + movement[1] > board.length-1 || k.y + movement[1]< 0
                || board[k.x+movement[0]][k.y+movement[1]] > 0
                || k.stamina == 0)
            return false;


        return true;
    }

    @Override
    protected void genStateSpace(State s) {
        this.genStateSpace((KnightsProblemState) s );

    }

    @Override
    int pathCost(String op) {
        //kill queen < move <inspire
        if(op.split(";").length >1)
            return 1;
        if(op.startsWith("inspire"))
            return 3;

        return 2;
    }







}
