import java.util.LinkedList;

public class GenerisanjeFooda {

    private ZmijaNode currFood;
    private LinkedList<ZmijaNode> putanjaDoHrane;

    public GenerisanjeFooda() {
    }

    public ZmijaNode generateFood(Zmija zmija){
        while(true){
            int x=0;
            int y=0;
            x =  (Konstante.ZMIJA_SIRINA_VISINA * (int)(Math.random() * Konstante.SIRINA_TABLE / Konstante.ZMIJA_SIRINA_VISINA));
            y =  (Konstante.ZMIJA_SIRINA_VISINA * (int)(Math.random() * Konstante.VISINA_TABLE / Konstante.ZMIJA_SIRINA_VISINA));
            ZmijaNode n = new ZmijaNode(x,y);

            if(!zmija.getBody().contains(n)){
                currFood = n;
                putanjaDoHrane = null;
                return n;
            }
        }
    }

    public ZmijaNode getCurrFood() {
        return currFood;
    }

    public void setCurrFood(ZmijaNode currFood) {
        this.currFood = currFood;
    }

    public LinkedList<ZmijaNode> getPutanjaDoHrane() {
        return putanjaDoHrane;
    }

    public void setPutanjaDoHrane(LinkedList<ZmijaNode> putanjaDoHrane) {
        this.putanjaDoHrane = putanjaDoHrane;
    }
}
