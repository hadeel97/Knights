import java.util.*;


public  class GeneralSearch {
	Problem p;
	Queue<Node> que ;
	String queF="";
	HashMap<Integer,State> seenStates = new HashMap<>();
	
	
	//static int nEx=0;
	int qSize=0;
	int aDepth=1;
	int qSz=0;
	
	public GeneralSearch(Problem p , String QingFun){
		this.p=p;
		this.queF=QingFun;
		
		//create initial node 
		Node root = new Node(p.initialState);
		//define Qing function
		switch(QingFun){
		case"BF" : que = new PriorityQueue<Node>(5000000,new BreadthFirst());break;
		case "DF" :  que = new PriorityQueue<Node>(50000000,new DepthFirst());break;
		case "UC" :  que = new PriorityQueue<Node>(5000000,new UniformCost());break;
		case "ID" :que = new PriorityQueue<Node>(5000000,new DepthFirst());break;
		case"G1": que = new PriorityQueue<Node>(500000,new GreedySearch(1));break;
		case"G2":que = new PriorityQueue<Node>(50000000,new GreedySearch(2));break;
		case"AS1":que = new PriorityQueue<Node>(5000000,new AStarSearch(1));break;
		case"AS2":que = new PriorityQueue<Node>(5000000,new AStarSearch(2));break;
		}
		//generate root children
		genChildren(root);
		
		//System.out.println(solve());

		
	}
	
	
	
public String solve() {
	String solution="";
	int i =0;
	int mE =0;
		while(true){
			if(que.isEmpty()) {
				if(queF.equals("ID")&&aDepth<15) {
					aDepth++;
					seenStates.clear();
					Node root = new Node(p.initialState);
					genChildren(root);
				return	this.solve();
				
				}
				System.out.println("NUmber of nodes explored : " + i);
				return "No solution";
			}
			Node cN = que.poll();
			//System.out.println(cN.toString());
			
			if(cN.myState.compareTo(p.goalState)==0){
				System.out.println("found goal");
				System.out.println(cN.myState);

				Node t = cN.parent;
				if(t!=null)
				solution+= cN.operator+"\n";
				while(t.parent!=null)
				{
				solution+=t.operator+"\n";
				t=t.parent;
				}
				System.out.println("NUmber of nodes tested : " + i);
				System.out.println("number of nodes that generated children "+ mE);
				System.out.println("NUmber of children added to q with control  : " + qSize);
				System.out.println("NUmber of children added to q without   control  : " + qSz);
				System.out.println("generated Twice  : " + (qSz-qSize));
				return solution;
				
			}else{
				if(!seenStates.containsKey(cN.myState.hashCode())) {
					genChildren(cN);
					 mE++;
					 seenStates.put(cN.myState.hashCode(),cN.myState);
				}
			}
			
			i++;	
		}
		
	}




private void genChildren(Node N) {

	p.genStateSpace(N.myState);

	for(String op : p.stateSpace.keySet()){
//		if(op == "inspire")
//			System.out.println(p.stateSpace.get(op));
		int cost = N.pathCost + p.pathCost(op);
		Node child = new Node(p.stateSpace.get(op),N,op,N.depth+1,cost);
		if(queF.equals("ID")) {
			if(!seenStates.containsKey(child.myState.hashCode()))
				if(child.depth<aDepth)
		que.add(child);
			
		}else {
			//cycle control
	
			if(!seenStates.containsKey(child.myState.hashCode())) {
			que.add(child);
			qSize++;
			}
			qSz++;
			
		}
		
		//System.out.println(child.operator + " D child " + child.depth);
		
	}
		
		
	}


}
