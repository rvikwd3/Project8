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
    final BufferedImage read(File input_file){
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

    //Extract RGB matrix from BufferedImage (FastRGB)
    final int[][] convImageToRGBMatrix(BufferedImage bi){
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

    //Get Red from aRGB
    final int getRedFromARGB(int argb){
        return ((argb >> 16)&0xFF);
    }

    //Get Green from aRGB
    final int getGreenFromARGB(int argb){
        return ((argb >> 8)&0xFF);
    }

    //Get Blue from aRGB
    final int getBlueFromARGB(int argb){
        return ((argb)&0xFF);
    }

    //Print RGB values of aRGB integer
    final void printRGBFromARGB(int argb){
        System.out.println("R="+((argb >> 16)&0xFF)+"\tG="+((argb >> 8)&0xFF)+"\tB="+((argb)&0xFF));
    }

    //Convert RGB values to HSV values
    //http://lolengine.net/blog/2013/01/13/fast-rgb-to-hsv
    final double[] RGBtoHSV(int r, int g, int b){

        double result[] = {0,0,0};
        double red = r/255.0;
        double green = g/255.0;
        double blue = b/255.0;

        double K = 0.0;

        if(green < blue){
            double tmp = green; green = blue; blue = tmp;
            K = -1;
        }

        if(red < green){
            double tmp = red; red = green; green = tmp;
            K = -2 / 6 - K;
        }

        if(green < blue){
            double tmp = green; green = blue; blue = tmp;
            K = -K;
        }

        double chroma = red - blue;
        result[0] = Math.abs(K + (green - blue) / (6 * chroma + 0.000001));
        result[1] = chroma / (red + 0.000001);
        result[2] = red;

        return result;
    }
}
