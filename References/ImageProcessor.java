package References;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessor {
	
	public static void main (String [] args) throws IOException {
		File f = new File("in.png");
	    BufferedImage img = ImageIO.read(f);
	    
	    for(int x = 0; x < img.getHeight(); x++) {
	    	for(int y = 0; y < img.getWidth(); y++) {
	    		
	    		System.out.println(img.getRGB(y, x));
	    	}
	    }
	    
	    f = new File("out.png");
	    ImageIO.write(img, "png", f);
	}
	
}
