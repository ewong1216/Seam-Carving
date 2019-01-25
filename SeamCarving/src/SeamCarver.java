import java.awt.Color;

public class SeamCarver{
	private SmC_Picture p;
	
	public SeamCarver(SmC_Picture pictureP){
		if(pictureP == null){
			throw new NullPointerException();
		}
		p = new SmC_Picture(pictureP);
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
		if(x < 0 || y < 0 || x >= width() || y >= height()){
			throw new IndexOutOfBoundsException();
		}
		if(x == 0 || y == 0 || x == width() - 1 || y == height() - 1){
			return 1000.0;
		}
		return Math.sqrt(gradientSq(x,y,1,0) + gradientSq(x,y,0,1));
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