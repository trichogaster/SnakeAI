import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ZmijaPanel extends JPanel {

    private Zmija zmija;
    private final GameRunner gameRunner = new GameRunner(this);
    private final GenerisanjeFooda generisanjeFooda = new GenerisanjeFooda();
    private final AstarAlgoritam algoritam = new AstarAlgoritam(this);

    public ZmijaPanel() {
        setPreferredSize(new Dimension(Konstante.SIRINA_TABLE, Konstante.VISINA_TABLE));
        setBackground(Color.darkGray);
        setFocusable(true);

        ZmijaNode glava = new ZmijaNode(Konstante.SIRINA_TABLE /2, Konstante.VISINA_TABLE /2);
        ZmijaNode rep = new ZmijaNode(Konstante.SIRINA_TABLE /2, (Konstante.VISINA_TABLE /2)+ Konstante.ZMIJA_SIRINA_VISINA);

        zmija = new Zmija(glava, rep);
        zmija.getBody().add(glava);
        zmija.getBody().add(rep);

        setFood();

        new Thread(gameRunner).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawZmija(g);
        drawFood(g);

        if(generisanjeFooda.getPutanjaDoHrane() == null){
            LinkedList<ZmijaNode> list = algoritam.pretrazi(zmija.getHead(), zmija.getFood());
            generisanjeFooda.setPutanjaDoHrane(list);

            if(list == null){
                randomPokret();
            }

        }
        if(generisanjeFooda.getPutanjaDoHrane() != null){
            ZmijaNode node = generisanjeFooda.getPutanjaDoHrane().remove();
            zmija.pomeriSnake(node);
        }

    }

    public void drawFood(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(zmija.getFood().x, zmija.getFood().y, Konstante.ZMIJA_SIRINA_VISINA, Konstante.ZMIJA_SIRINA_VISINA);
    }

    public void drawZmija(Graphics g){
        for(int i=0;i<zmija.getBody().size();i++){
            ZmijaNode node = zmija.getBody().get(i);
            if(node.equals(zmija.getHead()))
                g.setColor(Color.RED);
            else if(node.equals(zmija.getTail()))
                g.setColor(Color.WHITE);
            else g.setColor(Color.ORANGE);

            if(node.equals(zmija.getFood())){
                setFood();
            }

            g.fillRect(node.x, node.y, Konstante.ZMIJA_SIRINA_VISINA, Konstante.ZMIJA_SIRINA_VISINA);
        }

    }
    public void randomPokret() {
        List<ZmijaNode> list=algoritam.susednaPolja(zmija.getHead());

        for(int i=0; i<list.size();i++) {
            ZmijaNode node=list.get(i);

            if(algoritam.verifikacijaPolja(node)) {
                zmija.pomeriSnake(node);
            }

        }
    }

    private void setFood() {
        ZmijaNode food=generisanjeFooda.generateFood(zmija);
        zmija.setFood(food);
        generisanjeFooda.setCurrFood(food);
        generisanjeFooda.setPutanjaDoHrane(null);
    }

    public Zmija getMyZmija() {
        return zmija;
    }

    public GameRunner getGameRunner() {
        return gameRunner;
    }
}
