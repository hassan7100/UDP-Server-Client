import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.*;
import java.nio.*;
public class Server {
    public static double[] toDoubleArray(byte[] byteArray){
        int times = Double.SIZE / Byte.SIZE;
        double[] doubles = new double[byteArray.length / times];
        for(int i=0;i<doubles.length;i++){
            doubles[i] = ByteBuffer.wrap(byteArray, i*times, times).getDouble();
        }
        return doubles;
    }
    public static byte[] toByteArray(double[] doubleArray){
        int times = Double.SIZE / Byte.SIZE;
        byte[] bytes = new byte[doubleArray.length * times];
        for(int i=0;i<doubleArray.length;i++){
            ByteBuffer.wrap(bytes, i*times, times).putDouble(doubleArray[i]);
        }
        return bytes;
    }
    public static void main(String[] args){
        try {
            DatagramPacket packet ;
            DatagramSocket socket = new DatagramSocket(7000);

            while(true){
                FileWriter wr=new FileWriter("log.txt",true);
                PrintWriter dwr=new PrintWriter(wr);

                packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);

                long millis = System.currentTimeMillis();
                java.util.Date date = new java.util.Date(millis);
                dwr.print(date+"\t"+packet.getAddress().getHostAddress()+":"+packet.getPort()+"\t");
                double[] r=toDoubleArray(packet.getData());
                double d=0;
                int c=0;
                int count=0;
                double []d2=new double[100];
                do{
                    d2[c]=r[c];
                    d=r[c];
                    if(d==-1)
                        count=c;
                    c++;
                }while(d!=-1);

                double []r1=new double[count];
                for(int x=0;x<count;x++){
                    r1[x]=d2[x];
                }
                System.out.println(count);
                java.util.Arrays.sort(r1);
                for(double q:r1){
                    System.out.println(q);
                    dwr.print(q);
                }
                dwr.println();
                dwr.close();
                packet.setData(toByteArray(r1));
                socket.send(packet);



            }
        }
        catch(IOException ex){

        }
    }
}
