import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class Main {
    public static void main(String[] args){
        System.out.println("**********\nInit Project\n**********");

        GraphFactory graphFactory = new GraphFactory();

        BufferedImage bi = graphFactory.read(new File(args[0]));

        System.out.println(args[0]+" was read successfully");

//        for(int i=0; i < bi.getHeight(); i++){
//            for(int j=0; j < bi.getWidth(); j++){
//                System.out.println(bi.getRGB(j,i));
//            }
//        }

        //Using a non-iterative version of RGB

        byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();

        for (int i=0; i < pixels.length; i++){
            System.out.println((pixels[i] & 0xFF));
        }
    }
}
