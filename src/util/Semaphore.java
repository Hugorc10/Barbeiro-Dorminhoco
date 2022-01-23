package util;

public class Semaphore {
    
    private int value;
    
    public Semaphore(int v) {
        value = v;
    }
    
    public synchronized void down() {
        while (value <= 0) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Erro na espera");
            }
        }
        value--;
    }
    
    public synchronized void up() {
        ++value;
        notify(); //acorda processo
    }
}
