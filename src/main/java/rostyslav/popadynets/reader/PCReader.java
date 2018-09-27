package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class PCReader {

	public static List<String> PCMANUFACTURER = new LinkedList<>();
	public static List<String> PCMODEL = new LinkedList<>();
	public static List<String> PCPROCESSOR = new LinkedList<>();
	public static List<String> PCRAM = new LinkedList<>();
	public static List<String> PCOS = new LinkedList<>();
	public static List<String> PCDRIVECAPACITY = new LinkedList<>();
	public static List<String> PCCOLOR = new LinkedList<>();
	public static List<String> PCCOOLING = new LinkedList<>();
	public static List<String> PCGRAPHICADAPTER = new LinkedList<>();
	public static List<String> PCADITIONAL = new LinkedList<>();
	public static List<String> PCDESCRIPTION = new LinkedList<>();
	public static List<String> PCWEIGHT = new LinkedList<>();
	public static List<String> PCPRICE = new LinkedList<>();
	public static List<String> PCDIMENSIONSHEIGHT = new LinkedList<>();
	public static List<String> PCDIMENSIONSWIDTH = new LinkedList<>();
	public static List<String> PCDIMENSIONSLENGTH = new LinkedList<>();
	public static List<String> PCPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File pcs = new File("startValues/PCs/pcs");

		File pcPhotos = new File("startValues/PCs/PCPhotosUrl");

		if (pcs.exists() && pcPhotos.exists()) {

			FileReader fileReaderPCs = new FileReader(pcs);

			BufferedReader buffereReaderPCs = new BufferedReader(fileReaderPCs);

			FileReader fileReaderPCPhotos = new FileReader(pcPhotos);

			BufferedReader buffereReaderPCPhotos = new BufferedReader(fileReaderPCPhotos);

			while (buffereReaderPCs.ready()) {

				PCMANUFACTURER.add(buffereReaderPCs.readLine());
				PCMODEL.add(buffereReaderPCs.readLine());
				PCPROCESSOR.add(buffereReaderPCs.readLine());
				PCRAM.add(buffereReaderPCs.readLine());
				PCOS.add(buffereReaderPCs.readLine());
				PCDRIVECAPACITY.add(buffereReaderPCs.readLine());
				PCCOLOR.add(buffereReaderPCs.readLine());
				PCCOOLING.add(buffereReaderPCs.readLine());
				PCGRAPHICADAPTER.add(buffereReaderPCs.readLine());
				PCADITIONAL.add(buffereReaderPCs.readLine());
				PCDESCRIPTION.add(buffereReaderPCs.readLine());
				PCWEIGHT.add(buffereReaderPCs.readLine());
				PCPRICE.add(buffereReaderPCs.readLine());
				PCDIMENSIONSHEIGHT.add(buffereReaderPCs.readLine());
				PCDIMENSIONSWIDTH.add(buffereReaderPCs.readLine());
				PCDIMENSIONSLENGTH.add(buffereReaderPCs.readLine());

			}

			while (buffereReaderPCPhotos.ready()) {

				PCPHOTOSURL.add(buffereReaderPCPhotos.readLine());

			}

			buffereReaderPCs.close();
			buffereReaderPCPhotos.close();
		}
	}
}
