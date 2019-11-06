import java.rmi.Remote;
import java.rmi.RemoteException;
public interface matrix extends Remote
{
   public int[][] matrixmul(int[][] matrix1, int[][] matrix2,int N) throws RemoteException;
   public long time() throws RemoteException;

}  