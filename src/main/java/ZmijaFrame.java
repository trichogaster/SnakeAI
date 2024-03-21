import javax.swing.*;

public class ZmijaFrame extends JFrame {

    public ZmijaFrame(){

        ZmijaPanel panel = new ZmijaPanel();
        add(panel);
        pack();
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);

    }
}
