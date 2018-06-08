import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class Main {
    public static void main(String[] args){
        System.out.println("**********\nInit Project\n**********");

        //Initialize GraphFactory
        GraphFactory graphFactory = new GraphFactory();

        //Read image given in Arguments
        BufferedImage init_img = graphFactory.read(new File(args[0]));

        //Extract RGB pixel array from image
        int[][] init_img_rgb_matrix = new int[init_img.getHeight()][init_img.getWidth()];
        init_img_rgb_matrix = graphFactory.convImageToRGBMatrix(init_img);

        //Print RGB Matrix
        for(int i=0; i < init_img.getHeight(); i++){
            for(int j=0; j < init_img.getWidth(); j++){
                System.out.println("i="+i+"\tj="+j);
                System.out.println(init_img_rgb_matrix[i][j]);
                graphFactory.printRGBFromARGB(init_img_rgb_matrix[i][j]);
                //extra line
                System.out.println();
            }
            System.out.println();
        }

        double hsv[] = graphFactory.RGBtoHSV(255,0,0);
        System.out.println("H="+hsv[0]+"\tS="+hsv[1]+"\tV="+hsv[2]);
    }
}
