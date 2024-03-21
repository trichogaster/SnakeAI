import java.util.ArrayList;
import java.util.List;

public class Zmija {

    private ZmijaNode head;
    private ZmijaNode tail;
    private ZmijaNode food;

    private List<ZmijaNode> body = new ArrayList<>();

    public Zmija(ZmijaNode head, ZmijaNode tail) {
        this.head = head;
        this.tail = tail;
    }

    public void pomeriSnake(ZmijaNode node){
        body.add(0, node);

        head = body.get(0);

        if(!head.equals(food)){
            body.remove(tail);
            tail = body.get(body.size()-1);
        }
    }

    public ZmijaNode getHead() {
        return head;
    }

    public void setHead(ZmijaNode head) {
        this.head = head;
    }

    public ZmijaNode getTail() {
        return tail;
    }

    public void setTail(ZmijaNode tail) {
        this.tail = tail;
    }

    public ZmijaNode getFood() {
        return food;
    }

    public void setFood(ZmijaNode food) {
        this.food = food;
    }

    public List<ZmijaNode> getBody() {
        return body;
    }

    public void setBody(List<ZmijaNode> body) {
        this.body = body;
    }
}
