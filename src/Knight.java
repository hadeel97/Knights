public class Knight {
    byte x ,y , i ,stamina ;

    public Knight(int x , int y, int index , int stamina){
        this.x = (byte) x;
        this.y = (byte) y;
        this.i = (byte) index;
        this.stamina = (byte) stamina;
    }

    public Knight(Knight knight) {
        this.x= knight.x;
        this.y= knight.y;
        this.i= knight.i;
        this.stamina= knight.stamina;
    }

    @Override
    public String toString(){
        return String.format("K %s (%s,%s) stamina=%s",i,x,y,stamina);
    }


}
