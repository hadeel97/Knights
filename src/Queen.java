public class Queen {
    byte x,y,i ;
    public Queen(int x , int y, int index){
        this.x = (byte) x;
        this.y = (byte) y;
        this.i = (byte) index;
    }

    public Queen(Queen queen) {
        this.x = queen.x;
        this.y = queen.y;
        this.i = queen.i;
    }
    @Override
    public String toString(){
        return String.format("Q %s (%s,%s) ",i,x,y);
    }
}
