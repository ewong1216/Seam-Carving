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
		double[][] transposedEnergy = new double[height()][width()];
		for(int row = 0; row < height(); row++){
			for(int col = 0; col < width(); col++){
				transposedEnergy[row][col] = energy[col][row];
			}
		}
		return findVerticalSeam(transposedEnergy);
	}
	private int[] findVerticalSeam(double[][] energy){
		int width = energy.length;
		int height = energy[0].length;
		double[][] distTo = new double[width][height];
		int[][] edgeTo = new int[width][height]; //Each pixel has 3 possible previous pixels: -1 is from top left, 0 from above, 1 from top right
		for(int row = 1; row < height; row++){
			for(int col = 0; col < width; col++){
				distTo[col][row] = 999999; //Set all distances except the first row to "infinity"
			}
		}
		
		for(int row = 0; row < height-1; row++){ //Last row no edges
			for(int col = 0; col < width; col++){ //Will go through in a top order
				double dist = distTo[col][row] + energy[col][row+1];
				if(distTo[col][row+1] > dist){ //All vertices (except last row) have edge pointing down
					distTo[col][row+1] = dist;
					edgeTo[col][row+1] = 0;
				}
				
				if(col != 0 && distTo[col-1][row+1] > distTo[col][row] + energy[col-1][row+1]){ //First col does not have diagonal left edge
					distTo[col-1][row+1] = distTo[col][row] + energy[col-1][row+1];
					edgeTo[col-1][row+1] = 1;
				}
				
				if(col != width-1 && distTo[col+1][row+1] > distTo[col][row] + energy[col+1][row+1]){ //last col does not have diagonal right edge
					distTo[col+1][row+1] = distTo[col][row] + energy[col+1][row+1];
					edgeTo[col+1][row+1] = -1;
				}
			}
		}
		
		int shortest = 0; //Index of the end of the shortest path at the bottom
		for(int col = 1; col < width; col++){
			if(distTo[col][height-1] < distTo[shortest][height-1]){
				shortest = col;
			}
		}
		
		int[] vertSeam = new int[height];
		vertSeam[vertSeam.length-1] = shortest;
		int col = shortest + edgeTo[shortest][height-1];
		for(int row = height-2; row > -1; row--){ //bottom to top
			vertSeam[row] = col;
			col = col + edgeTo[col][row];
		}
		
		return vertSeam;
	}
	public int[] findVerticalSeam(){
		return findVerticalSeam(energy);
	}

	public void removeHorizontalSeam(int[] a){
		throw new UnsupportedOperationException();
	}

	public void removeVerticalSeam(int[] a){
		throw new UnsupportedOperationException();
	}
}