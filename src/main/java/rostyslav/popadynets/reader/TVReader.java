package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class TVReader {

	public static List<String> TVMANUFACTURER = new LinkedList<>();
	public static List<String> TVMODEL = new LinkedList<>();
	public static List<String> TVDISPLAYDIAGONAL = new LinkedList<>();
	public static List<String> TVRESOLUTIONDISPLAY = new LinkedList<>();
	public static List<String> TVSMARTPLATFORM = new LinkedList<>();
	public static List<String> TVDISPLAYBRIGHTNESS = new LinkedList<>();
	public static List<String> TVCOLOR = new LinkedList<>();
	public static List<String> TVHDR = new LinkedList<>();
	public static List<String> TVADITIONAL = new LinkedList<>();
	public static List<String> TVDESCRIPTION = new LinkedList<>();
	public static List<String> TVPRICE = new LinkedList<>();
	public static List<String> TVWEIGHT = new LinkedList<>();
	public static List<String> TVDIMENSIONSLENGTH = new LinkedList<>();
	public static List<String> TVDIMENSIONSHEIGHT = new LinkedList<>();
	public static List<String> TVDIMENSIONSWIDTH = new LinkedList<>();
	public static List<String> TVPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File tv = new File("startValues/TVs/tvs");
		
		File tvPhotos = new File("startValues/TVs/TVPhotosUrl");

		if (tv.exists() && tvPhotos.exists()) {

			FileReader fileReaderTVs = new FileReader(tv);

			BufferedReader buffereReaderTVs = new BufferedReader(fileReaderTVs);

			FileReader fileReaderPhotos = new FileReader(tvPhotos);

			BufferedReader buffereReaderPhotos = new BufferedReader(fileReaderPhotos);

			while (buffereReaderTVs.ready()) {

				TVMANUFACTURER.add(buffereReaderTVs.readLine());
				TVMODEL.add(buffereReaderTVs.readLine());
				TVDISPLAYDIAGONAL.add(buffereReaderTVs.readLine());
				TVRESOLUTIONDISPLAY.add(buffereReaderTVs.readLine());
				TVSMARTPLATFORM.add(buffereReaderTVs.readLine());
				TVDISPLAYBRIGHTNESS.add(buffereReaderTVs.readLine());
				TVCOLOR.add(buffereReaderTVs.readLine());
				TVHDR.add(buffereReaderTVs.readLine());
				TVADITIONAL.add(buffereReaderTVs.readLine());
				TVDESCRIPTION.add(buffereReaderTVs.readLine());
				TVPRICE.add(buffereReaderTVs.readLine());
				TVWEIGHT.add(buffereReaderTVs.readLine());
				TVDIMENSIONSLENGTH.add(buffereReaderTVs.readLine());
				TVDIMENSIONSHEIGHT.add(buffereReaderTVs.readLine());
				TVDIMENSIONSWIDTH.add(buffereReaderTVs.readLine());

			}

			while (buffereReaderPhotos.ready()) {

				TVPHOTOSURL.add(buffereReaderPhotos.readLine());

			}

			buffereReaderTVs.close();
			buffereReaderPhotos.close();
		}
	}
}