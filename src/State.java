import java.util.Arrays;

public abstract class State implements Comparable<State> {
byte [] [] state;
//public HashMap <String,State> nextStates;

public State(byte[][] map) {
	// TODO Auto-generated constructor stub
	int n = map.length;

	byte[][] cloneBoard = new byte[n][n];
	for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
			cloneBoard[i][j] = map[i][j];
	this.state=cloneBoard;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.deepHashCode(state);
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
	State other = (State) obj;
	if (!Arrays.deepEquals(state, other.state))
		return false;
	return true;
}



}
