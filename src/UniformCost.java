import java.util.Comparator;

public class UniformCost implements Comparator<Node>  {
	@Override
	public int compare(Node o1, Node o2) {
		return o1.pathCost-o2.pathCost;
	}

}
