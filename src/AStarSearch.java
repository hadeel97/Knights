import java.util.Comparator;

public class AStarSearch implements Comparator<Node> {
	Boolean fun1 ,fun2;
	public AStarSearch(int i) {
		switch(i){
		case 1: fun1=true;fun2=false;break;
		case 2 : fun1=false;fun2=true;break;
		}
	}
	@Override
	public int compare(Node o1, Node o2) {
	//distance between player and exit
		if(fun1){
			
		}
		return 0;
	}

}
