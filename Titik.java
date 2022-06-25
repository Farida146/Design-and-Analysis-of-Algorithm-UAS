package newpackage;

public class Titik {
    int x, y;
    
    public Titik(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
    
    public boolean compare(Titik titikLain){
        if(titikLain.x == x && titikLain.y == y){
            return true;
        }
        return false;
    }
}
