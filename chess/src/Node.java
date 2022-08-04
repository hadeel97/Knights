

public class Node {
	ChessState state;
	Node parentNode;
	Integer[] operator;
	int depth;
	int pathcost;
	int stateHeur;

	public Node(ChessState state, Node parentNode, Integer[] operator, int depth, int pathcost) {
		this.state = state;
		this.parentNode = parentNode;
		this.operator = operator;
		this.depth = depth;
		this.pathcost = pathcost;
	}

	public Node(ChessState state, Node parentNode, Integer[] operator, int depth, int pathcost, int stateHeur) {
		super();
		this.state = state;
		this.parentNode = parentNode;
		this.operator = operator;
		this.depth = depth;
		this.pathcost = pathcost;
		this.stateHeur = stateHeur;
	}

}
