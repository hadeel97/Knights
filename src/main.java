public class main {

    public static void main(String [] args) {
        KnightsProblem p = new KnightsProblem();
        p.initialState = p.genBoard(10,10,10,5);
        /*CHOOSE SEARCH METHOD
         * DF:depthFirst    BF:breadthFirst
         * ID:iterativeDepth  UC:uniformCost
         * G1L optimizes on Q count   G2:
         * AS1				    As2
         */

        String searchMethod = "G1";

        GeneralSearch gs = new GeneralSearch(p,searchMethod);
        System.out.println("Search using " + searchMethod);
        System.out.println("number of queens " + ((KnightsProblemState) p.initialState).queens.length);
        System.out.println("number of knights " + ((KnightsProblemState) p.initialState).knights.length);
        System.out.println("______________________________________________________________");
        System.out.println("initialState " + p.initialState);
        System.out.println("______________________________________________________________");

        String solution = gs.solve();
        //System.out.println(solution);
        String [] operations = solution.split("\n");
        String [] opRev = new String[operations.length];
        System.out.println("Solution depth:- "+ operations.length);
        for(int ri =0 ; ri<opRev.length;ri++) {
            opRev[ri] = operations[operations.length - 1 - ri];
            String[] op = opRev[ri].split(",");
            System.out.println(opRev[ri]);
        }


    }
}
