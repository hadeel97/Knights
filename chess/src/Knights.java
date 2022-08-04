import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Knights extends SearchProblem {

	private HashSet<String> hs;

	public Knights() {
		super();
		this.actions = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 ,9};
		this.hs = new HashSet<String>();
	}

	public Object[] genGrid() {
		Object [] obj = new Object[4];
		String Grid = "";

		// GENERATE RANDOM GRID DIMENTIONS
		int grid_dimention_max = 5;
		int grid_dimention_min = 5;
		int xdimention = (int) Math
				.floor(Math.random() * (grid_dimention_max - grid_dimention_min + 1) + grid_dimention_min);
		int ydimention = (int) Math
				.floor(Math.random() * (grid_dimention_max - grid_dimention_min + 1) + grid_dimention_min);
		Grid += xdimention;
		Grid += ",";
		Grid += xdimention;
		Grid += ";";
		String [][] board = new String[xdimention][xdimention];

		int[][] availaible = new int[xdimention][ydimention];

		// GENERATE RANDOM NUMBER AND POSITON OF QUEENS
		int max_queens = (xdimention * ydimention) / 6;
		int min_queens = 1;

		int queen_count = (int) Math.floor(Math.random() * (max_queens - min_queens + 1) + min_queens);
		int [][] queens = new int[queen_count][2];
		for (int i = 1; i <= queen_count; i++) {
			int random_number_x = (int) Math.floor(Math.random() * (xdimention));
			int random_number_y = (int) Math.floor(Math.random() * (ydimention));
			while (!isEmpty(availaible, random_number_x, random_number_y)) {
				random_number_x = (int) Math.floor(Math.random() * (xdimention));
				random_number_y = (int) Math.floor(Math.random() * (ydimention));
			}
			int queen_x = random_number_x;
			int queen_y = random_number_y;
			Grid += queen_x;
			Grid += ",";
			Grid += queen_y;
			Grid += ";";
			
			queens[i-1][0]= queen_x;
			queens[i-1][1]= queen_y;	

			availaible[queen_x][queen_y] = 1;
		}
			
			// GENERATE RANDOM NUMBER AND POSITON OF KNIGHTS
			int max_knights = (xdimention * ydimention) / 6;
			int min_knights = 1;
			
			int knight_count = (int) Math.floor(Math.random() * (max_knights - min_knights + 1) + min_knights);
			int [][] knights = new int[knight_count][3];
			for (int i1 = 1; i1 <= knight_count; i1++) {
				 int random_number_x = (int) Math.floor(Math.random() * (xdimention));
				 int random_number_y = (int) Math.floor(Math.random() * (ydimention));
				while (!isEmpty(availaible, random_number_x, random_number_y)) {
					random_number_x = (int) Math.floor(Math.random() * (xdimention));
					random_number_y = (int) Math.floor(Math.random() * (ydimention));
				}
				int knight_x = random_number_x;
				int knight_y = random_number_y;
				int knight_s = (int) Math.floor(Math.random()*9)+1;
				
				Grid += knight_x;
				Grid += ",";
				Grid += knight_y;
				Grid += ",";
				Grid += knight_s;
				Grid += ";";
				
				knights[i1-1][0]= knight_x;
				knights[i1-1][1]= knight_y;	
				knights[i1-1][2]= knight_s;
				availaible[knight_x][knight_y] = 1;
		
		}
			obj[0]=Grid;
			obj[1]=queens;
			obj[2]=knights;
			obj[3] = board;
		return obj;
	}

	public boolean isEmpty(int[][] availaible, int x, int y) {

		if (availaible[x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Knights k = new Knights();
		Object [] o = k.genGrid();
		
		
		int[][] kk = {{2,4,10}};
		int[][] qq = {{1,2}};
		
		
		ChessState c = new ChessState((String[][])o[3],qq,kk);
		//ChessState c = new ChessState((String[][])o[3],(int[][]) o[1],(int[][]) o[2]);
		c.PopulateGrid();
		//c= k.genGrid();
	//	Node n= new Node(c, null, null, 0, 0);
	//	k.initialState=n;
	//	System.out.println(n.state.move(0,1).toString());
		//System.out.println(solve(n.state,"bf"));
		System.out.println(c.move(0,8).move(0, 1).toString());
		
		
		
		
		// String test1 = "15,15;9,9;1,6;5,12;4,8;12,3;8,7;5,2;1,12;0,10,3;6,9,7;7,11,9;10,3,2;";

	}

	@Override
	public Node transferFunction(Node node, int action , int knight) {
		Integer[] arr = {knight,action};
		if( action == 9 ) return new Node(node.state.inspire(), node,arr, node.depth+1, node.depth+1);
		else return new Node(node.state.move(knight, action), node, arr, node.depth+1, node.depth+1) ;
	}

	@Override
	public ArrayList<Node> aplyActions(Node node) {
		
		Node n = node;
		Node nn = new Node(n.state, n.parentNode, n.operator, n.depth, n.pathcost);
		ArrayList<Node> r = new ArrayList<Node>();
		for (int k =0 ; k<n.state.Knights.length;k++) {
		for (int action : this.actions) {
			
			Node childnode = transferFunction(nn, action, k);
			if ((childnode != null)&&(childnode.state != null)) {
				if (!hs.contains(childnode.state.getID())) {
					r.add(childnode);
					hs.add(childnode.state.getID());
				} else {
					r.add(null);
				}
			} else {
				r.add(null);
			}
		}}
		return r;
	}

	@Override
	public boolean goalTest(ChessState state) {
		if (state.queencount==0) return true;
		else return false;
	}
	

	@Override
	public int pathCost(ArrayList<Integer> actions) {
		int sum = 0;
		for (Object act : actions) {
			if (act != null) {
				sum++;
			}

		}
		return sum;
	}

	@Override
	public int heuristic(int hnumber, ChessState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printState(Node node) {
		System.out.println(node.state);

	}
	
	public static String solve(ChessState initialstate , String strategy) {
		Knights problem = new Knights();
		problem.initialState = new Node(initialstate, null, null, 0, 0);
		problem.hs.add(problem.initialState.state.getID());
		Object[] searchRes;
		switch (strategy.toLowerCase()) {
		case "bf":
			searchRes = bfs(problem);
			break;
		case "df":
			searchRes = dfs(problem);
			break;
		case "id":
			searchRes = iterativeDeep(problem);
			break;
		case "uc":
			searchRes = ucs(problem);
			break;
		case "gr1":
			searchRes = greedy(problem, 1);
			break;
		case "gr2":
			searchRes = greedy(problem, 2);
			break;
		case "as1":
			searchRes = AStar(problem, 1);
			break;
		case "as2":
			searchRes = AStar(problem, 2);
			break;
		default:
			searchRes = null;
			break;

		}
		if (searchRes[0] != null) {
			Object[] ret = new Object[3];
			Node n = (Node) searchRes[0];
			ArrayList<Integer[]> a = getActions(n);
			Collections.reverse(a);
			ArrayList<String> actionSeq = new ArrayList<>();
			for (Integer[] object : a) {
				if((int)object[1]==9) actionSeq.add("K"+object[0]+"inspire");
				else actionSeq.add("K"+object[0]+"MOVE"+object[1]);
				
			}
			ret[0] = actionSeq;
			ret[1] = n.pathcost;
			ret[2] = searchRes[1];
		
			String path = actionSeq.toString();
			return path;
		} else {
			return "No Solution";
		}
		
	}

	public static ArrayList<Integer[]> getActions(Node n) {
		ArrayList<Integer[]> o = new ArrayList<>();
		Node c = n;
		do {
			o.add(c.operator);
			c = c.parentNode;
		} while (c.parentNode != null);
		return o;
	}
	private static Object[] AStar(Knights problem, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object[] greedy(Knights problem, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object[] ucs(Knights problem) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object[] iterativeDeep(Knights problem) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object[] dfs(Knights problem) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object[] bfs(Knights problem) {
		Node bestgoalDiscovered = null;
		Node n = problem.initialState;
		LinkedList<Node> nodesQueue = new LinkedList<Node>();
		nodesQueue.add(n);
		int expandedNodes = 0;
		
		while (!nodesQueue.isEmpty()) {
			for (int i = 0; i < nodesQueue.size(); i++) {
				Node dequeuedNode = nodesQueue.remove();
				if (problem.goalTest(dequeuedNode.state)) {
					Object[] ret = new Object[2];
					System.out.println("solved!");
					ret[0]=dequeuedNode;
					ret[1]=expandedNodes;
					return ret;
				} else {
					expandedNodes++;
						ArrayList<Node> objectArr = problem.aplyActions(dequeuedNode);
						
						for (int j = 0; j < objectArr.size(); j++) {
							if (objectArr.get(j) != null) {
								
							
								nodesQueue.add(objectArr.get(j));
							}
						}
					
}

			}
		}
		Object[] ret = new Object[2];
		ret[0] = bestgoalDiscovered;
		if (ret[0] != null) {
			System.out.println(((Node) ret[0]).state);
		
			ret[1] = expandedNodes;
		}

		return ret;
	}

}
