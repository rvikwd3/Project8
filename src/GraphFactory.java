import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphFactory {

    BufferedImage bi;
    //CONSTRUCTOR
    public void GraphFactory(){
        bi = null;
    }

    //Read file
    BufferedImage read(File input_file){
        try{
            bi = ImageIO.read(input_file);
        }catch(IOException e){
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }finally{
            System.out.println(input_file + " successfully read");
        }

        return bi;
    }
}
