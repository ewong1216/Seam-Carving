/******************************************************************************
 *  Compilation:  javac ShowSeams.java
 *  Execution:    java ShowSeams input.png
 *  Dependencies: SeamCarver.java SCUtility.java
 *
 *  Read image from file specified as command line argument. Show 3 images 
 *  original image as well as horizontal and vertical seams of that image.
 *  Each image hides the previous one - drag them to see all three.
 *
 ******************************************************************************/

public class SmC_Test_ShowSeams {

    private static void showHorizontalSeam(SeamCarver sc) {
        SmC_Picture picture = SmC_Test_SCUtility.toEnergyPicture(sc);
        int[] horizontalSeam = sc.findHorizontalSeam();
        SmC_Picture overlay = SmC_Test_SCUtility.seamOverlay(picture, true, horizontalSeam);
        overlay.show();
    }


    private static void showVerticalSeam(SeamCarver sc) {
        SmC_Picture picture = SmC_Test_SCUtility.toEnergyPicture(sc);
        int[] verticalSeam = sc.findVerticalSeam();
        SmC_Picture overlay = SmC_Test_SCUtility.seamOverlay(picture, false, verticalSeam);
        overlay.show();
    }

    public static void main(String[] args) {
        SmC_Picture picture = new SmC_Picture("testInput/6x5.png");
        StdOut.printf("image is %d columns by %d rows\n", picture.width(), picture.height());
        picture.show();        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Displaying horizontal seam calculated.\n");
        showHorizontalSeam(sc);

        StdOut.printf("Displaying vertical seam calculated.\n");
        showVerticalSeam(sc);

    }

}
