import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.*;
public class Client {
    public static void main(String[] args) {
        try {
            int count=0;
            Double num;
            System.out.println("Enter numbers to sort:");
            Scanner sc= new Scanner(System.in);
            num=sc.nextDouble();
            double[] d=new double[50];

            while(num!=-1){
                d[count]=num;
                count++;
                num=sc.nextDouble();
            }
            d[count] = num;
            int byts=count*8;
            double[] d1=new double[count+1];
            for(int x=0;x<count+1;x++){
                d1[x]=d[x];
            }

            DatagramSocket socket=new DatagramSocket(2000);
            DatagramPacket packet=new DatagramPacket(new byte[1024],1024);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            packet.setAddress(InetAddress.getLoopbackAddress());
            packet.setPort(7000);
            receivePacket.setPort(7000);
            packet.setData(toByteArray(d1));
            socket.send(packet);
            socket.receive(packet);
            d1=toDoubleArray(packet.getData());
            double []d2=new double[count];
            for(int x=0;x<count;x++){
                d2[x]=d1[x];
            }
            for(double e:d2){
                System.out.println(e);
                if(e==-1) {
                    break;
                }
        }}
        catch(IOException ex){

        }

    }







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
}
