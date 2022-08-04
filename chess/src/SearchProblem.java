

import java.util.ArrayList;

public abstract class SearchProblem {
	Node initialState;
	int[] actions;

	public abstract Node transferFunction(Node node, int action , int knight);

	public abstract ArrayList<Node> aplyActions(Node node);

	public abstract boolean goalTest(ChessState state);

	public abstract int pathCost(ArrayList<Integer> actions);

	public abstract int heuristic(int hnumber, ChessState state);

	public abstract void printState(Node state);

}
