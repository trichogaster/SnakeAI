import java.util.*;

public class AstarAlgoritam {

    private ZmijaPanel panel;
    public AstarAlgoritam(ZmijaPanel panel) {
        this.panel = panel;
    }
    public LinkedList<ZmijaNode> pretrazi(ZmijaNode glava, ZmijaNode food){

        Queue<ZmijaNode> red = new PriorityQueue<>();
        List<ZmijaNode> pretrazeni = new ArrayList<>();

        ///setujemo pocetne vrednosti glave zmije
        glava.g = 0;
        glava.h = heuristika(glava, food);
        glava.setParent(null);
        red.add(glava);


        while (!red.isEmpty()){

            ZmijaNode trenutni = red.remove();

            ///ako smo dosli do hrane
            if(trenutni.equals(food)){
                return novaPutanja(trenutni);
            }

            ///pravimo listu svi okolnih kvadratica
            List<ZmijaNode> lista = susednaPolja(trenutni);
            for(int i=0; i<lista.size(); i++){

                ZmijaNode node = lista.get(i);

                boolean inQueue = red.contains(node);
                boolean inVidjeni = pretrazeni.contains(node);

                int novaDistanca = trenutni.g + heuristika(trenutni, node);
                if(!inQueue && !inVidjeni){
                    node.setParent(trenutni);
                    node.g = novaDistanca;
                    node.h = heuristika(node, food);

                    if(verifikacijaPolja(node))
                        red.add(node);
                }
            }
            pretrazeni.add(trenutni);
        }
        return null;
    }

    public int heuristika(ZmijaNode glava, ZmijaNode food){
        int x = Math.abs(glava.x - food.x);
        int y = Math.abs(glava.y - food.y);
        return x+y;
    }

    public List<ZmijaNode> susednaPolja(ZmijaNode node){
        ZmijaNode levo = new ZmijaNode(node.x- Konstante.ZMIJA_SIRINA_VISINA,node.y);
        ZmijaNode desno = new ZmijaNode(node.x+ Konstante.ZMIJA_SIRINA_VISINA, node.y);
        ZmijaNode gore = new ZmijaNode(node.x, node.y- Konstante.ZMIJA_SIRINA_VISINA);
        ZmijaNode dole = new ZmijaNode(node.x, node.y+ Konstante.ZMIJA_SIRINA_VISINA);

        List<ZmijaNode> lis=new ArrayList<>();
        lis.add(levo);
        lis.add(desno);
        lis.add(gore);
        lis.add(dole);

        return lis;
    }

    public boolean verifikacijaPolja(ZmijaNode node){
        if(node.x > (Konstante.SIRINA_TABLE - Konstante.ZMIJA_SIRINA_VISINA) || node.y > (Konstante.VISINA_TABLE - Konstante.ZMIJA_SIRINA_VISINA) ||
                node.x < 0 || node.y < 0)
            return false;

        return !panel.getMyZmija().getBody().contains(node);

    }

    public LinkedList<ZmijaNode> novaPutanja(ZmijaNode node){

        LinkedList<ZmijaNode> put = new LinkedList<>();

        while(node.getParent() !=null){
            put.addFirst(node);
            node = node.getParent();
        }
        return put;
    }
}
