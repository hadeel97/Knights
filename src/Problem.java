import java.util.HashMap;

public abstract class Problem {
String[] operators ;
State initialState;
public HashMap <String,State> stateSpace;
State goalState;
protected abstract void genStateSpace(State s);
abstract int  pathCost(String op);
}
