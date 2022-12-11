interface IPolaczenie {
    char get(int indeks);

    void set(int indeks, char c);

    int length();
}

class Baza { // singleton
    private StringBuilder tab = new StringBuilder();
    private Baza() {
    }

    private static Baza instance = new Baza();

    public static Baza getInstance() {
        return instance;
    }

    public static IPolaczenie getPolaczenie() {
        return Polaczenie.getInstance();
    }

    private static class Polaczenie implements IPolaczenie {
        private Baza baza;
        private static Polaczenie[] polaczenia = { new Polaczenie(), new Polaczenie(), new Polaczenie() };
        private static int kolejnosc = 0;

        private Polaczenie() {
            this.baza = Baza.getInstance();
        }

        public static IPolaczenie getInstance() {
            kolejnosc = (kolejnosc + 1) % polaczenia.length;
            return polaczenia[kolejnosc];
        }

        public char get(int indeks) {
            return baza.tab.charAt(indeks);
        }

        public void set(int indeks, char c) {
            baza.tab.setCharAt(indeks, c);
        }

        public int length() {
            return baza.tab.length();
        }

    }

    public static void main(String[] args) {
        Baza baza_1 = Baza.getInstance();
        Baza baza_2 = Baza.getInstance();
        
        System.out.println("baza_1 = " + baza_1);
        System.out.println("baza_2 = " + baza_2);
        
        IPolaczenie polaczenie_1, polaczenie_2, polaczenie_3, polaczenie_4;
        
        polaczenie_1 = Polaczenie.getInstance();
        polaczenie_2 = Polaczenie.getInstance();
        polaczenie_3 = Polaczenie.getInstance();
        polaczenie_4 = Polaczenie.getInstance();
        
        System.out.println(polaczenie_1);
        System.out.println(polaczenie_2);
        System.out.println(polaczenie_3);
        System.out.println(polaczenie_4);
    }
}
