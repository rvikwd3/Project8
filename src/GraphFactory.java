import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
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
            System.out.println(input_file + " saved in buffer\nPlease convert image to RGB matrix");
        }

        return bi;
    }

    int[][] convImageToRGBMatrix(BufferedImage bi){
        final byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        final int width = bi.getWidth();
        final int height = bi.getHeight();
        final boolean hasAlpha = bi.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (hasAlpha){
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength){
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24);   //Alpha
                argb += (((int) pixels[pixel+1] & 0xff));         //Blue
                argb += (((int) pixels[pixel+2] & 0xff) << 8);    //Green
                argb += (((int) pixels[pixel+3] & 0xff) << 16);   //Red

                result[row][col] = argb;

                col++;

                if(col == width){
                    col = 0;
                    row++;
                }
            }
        }else{
            final int pixelLength = 3;
            for (int pixel=0, row=0, col=0; pixel<pixels.length; pixel += pixelLength){
                int argb = 0;

                argb += -16777216;                              //255 Alpha
                argb += (((int) pixels[pixel] & 0xff));         //Blue
                argb += (((int) pixels[pixel+1] & 0xff) << 8);    //Green
                argb += (((int) pixels[pixel+2] & 0xff) << 16);   //Red

                result[row][col] = argb;

                col++;

                if(col == width){
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }
}
