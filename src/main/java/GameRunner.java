public class GameRunner implements Runnable{

    private ZmijaPanel panel;

    public GameRunner(ZmijaPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {

        while(true){
            try{
                Thread.sleep(Konstante.DILEJ);
                panel.repaint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
