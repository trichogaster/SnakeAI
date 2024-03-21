public class ZmijaNode implements Comparable<ZmijaNode>{

    int x;
    int y;
    int g=0;
    int h=0;
    private ZmijaNode parent = null;

    public ZmijaNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int vrednost(){
        return g+h;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj instanceof ZmijaNode){
            ZmijaNode node = (ZmijaNode) obj;
            if(this.x == node.x && this.y == node.y){
                return true;
            }
        }
        return false;
    }

    public ZmijaNode getParent() {
        return parent;
    }

    public void setParent(ZmijaNode parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(ZmijaNode o) {
        int thisVal = this.vrednost();
        int otherVal = o.vrednost();

        int value = thisVal - otherVal;

        return (value>0)?1:(value<0)? -1: 0;
    }
}
