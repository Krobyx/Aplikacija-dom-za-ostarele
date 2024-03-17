import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Home test = new Home();
                test.show();

                try {
                    PostgreSQL db = new PostgreSQL();
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
