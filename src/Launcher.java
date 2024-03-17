import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StanovalciObrazec test = new StanovalciObrazec();
                test.show();
            }
        });
    }
}
