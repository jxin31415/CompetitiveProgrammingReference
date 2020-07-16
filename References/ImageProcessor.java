package References;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessor {
	
	public static void main (String [] args) throws IOException {
		System.out.println(Integer.toBinaryString(16755216));
		File f = new File("output.png");
	    BufferedImage img = ImageIO.read(f);
	    
	    StringBuilder sb = new StringBuilder("");
	    int count = 0;
	    for(int x = 0; x < img.getHeight(); x++) {
	    	for(int y = 0; y < img.getWidth(); y++) {
	    		//System.out.println(img.getRGB(y, x));
	    		sb.append(Integer.toBinaryString(img.getRGB(y, x)).charAt(8));
	    		sb.append(Integer.toBinaryString(img.getRGB(y, x)).charAt(16));
	    		sb.append(Integer.toBinaryString(img.getRGB(y, x)).charAt(24));
	    		//System.out.println(Integer.toBinaryString(img.getRGB(y, x)));
	    		//System.out.println(Integer.toBinaryString(img.getRGB(y, x)).charAt(8));
	    	}
	    }
	    
	    System.out.println(sb);
	    //f = new File("out.png");
	    //ImageIO.write(img, "png", f);
	}
	
}
