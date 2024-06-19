package fibonacci;

public class main {
    public static void main(String[] args) {
        long[] fibos = new long[50];
        fibos[0] = 0;
        fibos[1] = 1;
        for(int i=2;i<fibos.length;i++){
            fibos[i] = fibos[i-1] + fibos[i-2];
            System.out.print(fibos[i]+", ");
        }
    }
}
