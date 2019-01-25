public class SeamCarver{
	private SmC_Picture p;
	
	public SeamCarver(SmC_Picture pictureP){
		p = pictureP;
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
		throw new UnsupportedOperationException();
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