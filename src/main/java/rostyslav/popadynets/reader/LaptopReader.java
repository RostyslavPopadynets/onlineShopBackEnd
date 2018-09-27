package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class LaptopReader {

	public static List<String> LAPTOPMANUFACTURER = new LinkedList<>();
	public static List<String> LAPTOPMODEL = new LinkedList<>();
	public static List<String> LAPTOPPROCESSOR = new LinkedList<>();
	public static List<String> LAPTOPDIAGONALSCREEN = new LinkedList<>();
	public static List<String> LAPTOPRAM = new LinkedList<>();
	public static List<String> LAPTOPDRIVECAPACITY = new LinkedList<>();
	public static List<String> LAPTOPCOLOR = new LinkedList<>();
	public static List<String> LAPTOPGRAPHICADAPTER = new LinkedList<>();
	public static List<String> LAPTOPBATTERY = new LinkedList<>();
	public static List<String> LAPTOPADITIONAL = new LinkedList<>();
	public static List<String> LAPTOPDESCRIPTION = new LinkedList<>();
	public static List<String> LAPTOPWEIGHT = new LinkedList<>();
	public static List<String> LAPTOPRICE = new LinkedList<>();
	public static List<String> LAPTOPDIMENSIONSLENGTH = new LinkedList<>();
	public static List<String> LAPTOPDIMENSIONSWIDTH = new LinkedList<>();
	public static List<String> LAPTOPDIMENSIONSHEIGHT = new LinkedList<>();
	public static List<String> LAPTOPPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File laptops = new File("startValues/Laptops/laptops");

		File laptopPhotos = new File("startValues/Laptops/LaptopPhotosUrl");

		if (laptops.exists() && laptopPhotos.exists()) {

			FileReader fileReaderLaptops = new FileReader(laptops);

			BufferedReader buffereReaderLaptops = new BufferedReader(fileReaderLaptops);

			FileReader fileReaderPhotos = new FileReader(laptopPhotos);

			BufferedReader buffereReaderPhotos = new BufferedReader(fileReaderPhotos);

			while (buffereReaderLaptops.ready()) {

				LAPTOPMANUFACTURER.add(buffereReaderLaptops.readLine());
				LAPTOPMODEL.add(buffereReaderLaptops.readLine());
				LAPTOPPROCESSOR.add(buffereReaderLaptops.readLine());
				LAPTOPDIAGONALSCREEN.add(buffereReaderLaptops.readLine());
				LAPTOPRAM.add(buffereReaderLaptops.readLine());
				LAPTOPDRIVECAPACITY.add(buffereReaderLaptops.readLine());
				LAPTOPCOLOR.add(buffereReaderLaptops.readLine());
				LAPTOPGRAPHICADAPTER.add(buffereReaderLaptops.readLine());
				LAPTOPBATTERY.add(buffereReaderLaptops.readLine());
				LAPTOPADITIONAL.add(buffereReaderLaptops.readLine());
				LAPTOPDESCRIPTION.add(buffereReaderLaptops.readLine());
				LAPTOPWEIGHT.add(buffereReaderLaptops.readLine());
				LAPTOPRICE.add(buffereReaderLaptops.readLine());
				LAPTOPDIMENSIONSLENGTH.add(buffereReaderLaptops.readLine());
				LAPTOPDIMENSIONSWIDTH.add(buffereReaderLaptops.readLine());
				LAPTOPDIMENSIONSHEIGHT.add(buffereReaderLaptops.readLine());

			}

			while (buffereReaderPhotos.ready()) {

				LAPTOPPHOTOSURL.add(buffereReaderPhotos.readLine());

			}

			buffereReaderLaptops.close();
			buffereReaderPhotos.close();
		}
	}
}
