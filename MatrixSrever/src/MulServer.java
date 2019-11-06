import java.rmi.*;
import java.rmi.registry.*;
public class MulServer
{  
public static void main(String[] args)throws Exception
{
MatrixIMPL temp = new MatrixIMPL();
String rmiObjectName = "Matrix Mul";
Registry reg = LocateRegistry.createRegistry(2020);
reg.rebind(rmiObjectName, (Remote)temp); 
System.out.println("Srever is Ready");
}}