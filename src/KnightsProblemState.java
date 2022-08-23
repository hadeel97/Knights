import java.util.Arrays;

public class KnightsProblemState extends State {
    Queen [] queens ;
    Knight [] knights ;

    public KnightsProblemState(byte[][] board , Queen[] queens,Knight [] knights) {
        super(board);
        this.queens = queens;
        this.knights = knights;
    }
//    clone state
    public KnightsProblemState(KnightsProblemState  kpState) {
super(kpState.state);

        Knight[] clonedKnights = new Knight[kpState.knights.length];
        for(int i = 0;i<clonedKnights.length;i++)
            clonedKnights[i] = new Knight(kpState.knights[i]);
        Queen[] clonedQueens = new Queen[kpState.queens.length];
        for(int i = 0;i<clonedQueens.length;i++)
            clonedQueens[i] = new Queen(kpState.queens[i]);

        this.queens = clonedQueens;
        this.knights = clonedKnights;
    }

@Override
public String toString(){
        String boardString="";
        for(int i =0; i<state.length;i++){
            for (int j =0;j<state.length;j++)
                boardString+=" "+state[i][j];
            boardString+="\n";
        }
        String knightsStings= "";
        for(Knight k : knights)
            knightsStings+=k.toString()+"\n";
    String queensStings= "";
    for(Queen q : queens)
        queensStings+=q.toString()+"\n";
        return String.format(
                "grid:\n %s \n ,knights: %s ,\n queens:%s",boardString,knightsStings,queensStings
        );
}
    @Override
    public int compareTo(State o) {
        // goal state
        return this.queens.length;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result * Arrays.deepHashCode(state) * Arrays.deepHashCode(knights) *Arrays.deepHashCode(queens)  ;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KnightsProblemState other = (KnightsProblemState) obj;
        if (!Arrays.deepEquals(state, other.state ))
            return false;
        if (!Arrays.deepEquals(knights, other.knights ))
            return false;
        if (!Arrays.deepEquals(queens, other.queens ))
            return false;
        return true;
    }
}
