import java.util.Arrays;


interface IPolaczenie {
    
    char get(int indeks);
    
    void set(int indeks, char c);
    
    int length();
    
}

class Baza {
    
    private char[] tab = new char[100]; /* Baza danych */
    
    private Baza() { } /* Pusty konstruktor */
    private static Baza instance = null; /* Singleton */
    public static Baza getInstance() {
        if (instance == null) {
            instance = new Baza();
        }
        return instance;
    }
    
    public static IPolaczenie getPolaczenie() {
        return Polaczenie.getInstance();
    }
    
    
    private static class Polaczenie implements IPolaczenie { /* Multiton */
        
        private static final Baza baza = new Baza();
        private static Polaczenie[] instances = null;
        private static int obecny = 0;
        
        public static IPolaczenie getInstance() {
            if (instances == null) {
                instances = new Polaczenie[] {new Polaczenie()};
            } else if (instances.length < 3) {
                instances = Arrays.copyOf(instances, instances.length+1);
                instances[instances.length-1] = new Polaczenie();
                obecny++;
            } else {
                obecny++;
                obecny %= instances.length;
            }
            return instances[obecny];
        }
        
        public char get(int indeks) {
            
            return baza.tab[indeks];
            
        }
        
        public void set(int indeks, char c) {
            
            baza.tab[indeks] = c;
            
        }
        
        public int length() {
            
            return baza.tab.length;
            
        }
        
    }
    
}

public class Main {
    public static void main(String[] args) {
        Baza baza;
        baza = Baza.getInstance();
        System.out.println(baza);
        IPolaczenie pol1, pol2, pol3, pol4, pol5;
        pol1 = Baza.getPolaczenie();
        pol2 = Baza.getPolaczenie();
        pol3 = Baza.getPolaczenie();
        pol4 = Baza.getPolaczenie();
        pol5 = Baza.getPolaczenie();
        System.out.println(pol1);
        System.out.println(pol2);
        System.out.println(pol3);
        System.out.println(pol4);
        System.out.println(pol5);
        
        pol1.set(0, '1');
        System.out.println(pol1.get(0));
        
        pol2.set(0, '2');
        System.out.println(pol1.get(0));
    }

}