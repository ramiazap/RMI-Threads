
import java.lang.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

public class MatrixIMPL extends UnicastRemoteObject implements matrix {

    int[][] matrix3 = new int[1000][1000];
    long Stime,Etime;
     
    public MatrixIMPL() throws Exception {
    }

    public int[][] matrixmul(int[][] matrix1, int[][] matrix2,int N) throws RemoteException {
        Thread myThreads[] = new Thread[N];
        int start=0;
        int end=(1000/N);
        Stime=System.nanoTime();
        for (int i = 0; i < myThreads.length; i++){    
        myThreads[i]= new Thread(new multithread(start, end, matrix1, matrix2));
        myThreads[i].start();
        try {
            myThreads[i].join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MatrixIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        start=end;
        end=end+end;
        }
        Etime=System.nanoTime();
        return matrix3;
    }

    @Override
    public long time() throws RemoteException {
        return Etime-Stime;
    }

    public class multithread extends Thread {

        private int start, end;
        private int[][] a;
        private int[][] b;

        public multithread(int start, int end, int a[][], int b[][]) {
            this.start = start;
            this.end = end;
            this.a = a;
            this.b = b;
        }

        public void run() {
            int result=0;
            for (int i = start; i < end; i++) {
                for (int j = 0; j < a.length; j++) {
                    for (int k = 0; k < a.length; k++) {
                        result+= a[i][k] * b[k][j];
                    }
                    matrix3[i][j] = result;
                    result=0;
                }
            } 
        }
    }
}
