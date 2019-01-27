import java.awt.Color;

public class SeamCarver{
	private SmC_Picture p;
	private double[][] energy;
	
	public SeamCarver(SmC_Picture pictureP){
		if(pictureP == null){
			throw new NullPointerException();
		}
		p = new SmC_Picture(pictureP);
		energy = new double[width()][height()];
		for(int col = 0; col < width(); col++){
			energy[col][0] = 1000.0;
			energy[col][height()-1] = 1000.0;
		}
		for(int row = 1; row < height()-1; row++){
			energy[0][row] = 1000.0;
			energy[width()-1][row] = 1000.0;
		}
		for(int col = 1; col < width()-1; col++){
			for(int row = 1; row < height()-1; row++){
				energy[col][row] = Math.sqrt(gradientSq(col,row,1,0) + gradientSq(col,row,0,1));
			}
		}
		
	}

	public SmC_Picture picture(){
		return p;
	}

	public int width(){
		return p.width();
	}

	public int height(){
		return p.height();
	}
	
	public double energy(int x, int y){
		return energy[x][y]; //Will throw indexoutofbounds if needed
	}
	private double gradientSq(int x, int y, int xChange, int yChange){
		Color left = p.get(x - xChange, y - yChange);
		Color right = p.get(x + xChange, y + yChange);
		int r = right.getRed() - left.getRed();
		int g = right.getGreen() - left.getGreen();
		int b = right.getBlue() - left.getBlue();
		return r*r + g*g + b*b;
	}

	public int[] findHorizontalSeam(){
		throw new UnsupportedOperationException();
	}

	public int[] findVerticalSeam(){
		throw new UnsupportedOperationException();
	}

	public void removeHorizontalSeam(int[] a){
		throw new UnsupportedOperationException();
	}

	public void removeVerticalSeam(int[] a){
		throw new UnsupportedOperationException();
	}
}