import java.util.Comparator;

public class GreedySearch implements Comparator< Node> {
	Boolean fun1 ,fun2;

	public GreedySearch(int i) {
		switch(i){
		case 1: fun1=true;fun2=false;break;
		case 2 : fun1=false;fun2=true;break;
		}
	}

	@Override
	public int compare(Node n1, Node n2) {

		if(fun1){
			KnightsProblemState n1KpState = (KnightsProblemState)n1.myState;
			KnightsProblemState n2KpState = (KnightsProblemState)n2.myState;


			return (
					(n1KpState.queens.length - n2KpState.queens.length)
//							+
//					(n1.depth-n2.depth)
			);
		}

			return n1.depth-n2.depth;
			}


}
