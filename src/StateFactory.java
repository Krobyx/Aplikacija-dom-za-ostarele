

public class StateFactory {
    // Singleton vzorec
    private static StateFactory instance;
    public int uporabnikId = 3;

    private StateFactory() {

    }

    // Singleton metoda za dostop do instance
    public static synchronized StateFactory getInstance() {
        if (instance == null) {
            instance = new StateFactory();
        }
        return instance;
    }
}
