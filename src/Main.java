import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        System.out.println("**********\nInit Project\n**********");

        BufferedImage bi = null;
        File input = new File(args[0]);

        try{
            bi = ImageIO.read(input);
        }catch(IOException e){
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(args[0]+" was read successfully");

//        for(int i=0; i < bi.getHeight(); i++){
//            for(int j=0; j < bi.getWidth(); j++){
//                System.out.println(bi.getRGB(j,i));
//            }
//        }

        //Using a non-iterative version of RGB

        byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();

        for (int i=0; i < pixels.length; i++){
            System.out.println((int) (pixels[i] & 0xFF));
        }
    }
}
