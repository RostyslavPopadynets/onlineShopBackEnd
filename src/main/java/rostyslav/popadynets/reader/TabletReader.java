package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class TabletReader {

	public static List<String> TABLETMANUFACTURER = new LinkedList<>();
	public static List<String> TABLETMODEL = new LinkedList<>();
	public static List<String> TABLETPRICE = new LinkedList<>();
	public static List<String> TABLETDISPLAYDIAGONAL = new LinkedList<>();
	public static List<String> TABLETRESOLUTIONDISPLAY = new LinkedList<>();
	public static List<String> TABLETRAM = new LinkedList<>();
	public static List<String> TABLETOS = new LinkedList<>();
	public static List<String> TABLETDRIVECAPACITY = new LinkedList<>();
	public static List<String> TABLETCOLOR = new LinkedList<>();
	public static List<String> TABLETPROCESSOR = new LinkedList<>();
	public static List<String> TABLETMAXFLASHMEMOEY = new LinkedList<>();
	public static List<String> TABLETFRONTALCAMERA = new LinkedList<>();
	public static List<String> TABLETMAINCAMERA = new LinkedList<>();
	public static List<String> TABLETCORENUMBER = new LinkedList<>();
	public static List<String> TABLETFREQUENCY = new LinkedList<>();
	public static List<String> TABLETBATTERYCAPACITY = new LinkedList<>();
	public static List<String> TABLETADITIONAL = new LinkedList<>();
	public static List<String> TABLETDESCRIPTION = new LinkedList<>();
	public static List<String> TABLETWEIGHT = new LinkedList<>();
	public static List<String> TABLETDIMENSIONSLENGHT = new LinkedList<>();
	public static List<String> TABLETDIMENSIONSWIDTH = new LinkedList<>();
	public static List<String> TABLETDIMENSIONSHEIGHT = new LinkedList<>();
	public static List<String> TABLETPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File tablets = new File("startValues/Tablets/tablets");

		File tabletPhotos = new File("startValues/Tablets/tabletPhotosUrl");

		if (tablets.exists() && tabletPhotos.exists()) {

			FileReader fileReaderTablets = new FileReader(tablets);

			BufferedReader buffereReaderTablets = new BufferedReader(fileReaderTablets);

			FileReader fileReaderTabletPhotos = new FileReader(tabletPhotos);

			BufferedReader buffereReaderTabletPhotos = new BufferedReader(fileReaderTabletPhotos);

			while (buffereReaderTablets.ready()) {

				TABLETMANUFACTURER.add(buffereReaderTablets.readLine());
				TABLETMODEL.add(buffereReaderTablets.readLine());
				TABLETPRICE.add(buffereReaderTablets.readLine());
				TABLETDISPLAYDIAGONAL.add(buffereReaderTablets.readLine());
				TABLETRESOLUTIONDISPLAY.add(buffereReaderTablets.readLine());
				TABLETRAM.add(buffereReaderTablets.readLine());
				TABLETOS.add(buffereReaderTablets.readLine());
				TABLETDRIVECAPACITY.add(buffereReaderTablets.readLine());
				TABLETCOLOR.add(buffereReaderTablets.readLine());
				TABLETPROCESSOR.add(buffereReaderTablets.readLine());
				TABLETMAXFLASHMEMOEY.add(buffereReaderTablets.readLine());
				TABLETFRONTALCAMERA.add(buffereReaderTablets.readLine());
				TABLETMAINCAMERA.add(buffereReaderTablets.readLine());
				TABLETCORENUMBER.add(buffereReaderTablets.readLine());
				TABLETFREQUENCY.add(buffereReaderTablets.readLine());
				TABLETBATTERYCAPACITY.add(buffereReaderTablets.readLine());
				TABLETADITIONAL.add(buffereReaderTablets.readLine());
				TABLETDESCRIPTION.add(buffereReaderTablets.readLine());
				TABLETWEIGHT.add(buffereReaderTablets.readLine());
				TABLETDIMENSIONSLENGHT.add(buffereReaderTablets.readLine());
				TABLETDIMENSIONSWIDTH.add(buffereReaderTablets.readLine());
				TABLETDIMENSIONSHEIGHT.add(buffereReaderTablets.readLine());

			}

			while (buffereReaderTabletPhotos.ready()) {

				TABLETPHOTOSURL.add(buffereReaderTabletPhotos.readLine());

			}

			buffereReaderTablets.close();
			buffereReaderTabletPhotos.close();
		}
	}
}
