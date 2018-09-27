package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class MonitorReader {

	public static List<String> MONITORMANUFACTURER = new LinkedList<>();
	public static List<String> MONITORMODEL = new LinkedList<>();
	public static List<String> MONITORDISPLAYDIAGONAL = new LinkedList<>();
	public static List<String> MONITORMAXRESOLUTIONDISPLAY = new LinkedList<>();
	public static List<String> MONITORMATRIXTYPE = new LinkedList<>();
	public static List<String> MONITORDISPLAYBRIGHTNESS = new LinkedList<>();
	public static List<String> MONITORINTERFACES = new LinkedList<>();
	public static List<String> MONITORATTITUDEPARTIES = new LinkedList<>();
	public static List<String> MONITORCOLOR = new LinkedList<>();
	public static List<String> MONITORWEBCAM = new LinkedList<>();
	public static List<String> MONITORADDITIONAL = new LinkedList<>();
	public static List<String> MONITORDESCRIPTION = new LinkedList<>();
	public static List<String> MONITORPRICE = new LinkedList<>();
	public static List<String> MONITORPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File monitors = new File("startValues/Monitors/monitors");

		File monitorPhotos = new File("startValues/Monitors/MonitorPhotosUrl");

		if (monitors.exists() && monitorPhotos.exists()) {

			FileReader fileReaderMonitors = new FileReader(monitors);

			BufferedReader buffereReaderMonitors = new BufferedReader(fileReaderMonitors);

			FileReader fileReaderMonitorPhotos = new FileReader(monitorPhotos);

			BufferedReader buffereReaderMonitorPhotos = new BufferedReader(fileReaderMonitorPhotos);

			while (buffereReaderMonitors.ready()) {

				MONITORMANUFACTURER.add(buffereReaderMonitors.readLine());
				MONITORMODEL.add(buffereReaderMonitors.readLine());
				MONITORDISPLAYDIAGONAL.add(buffereReaderMonitors.readLine());
				MONITORMAXRESOLUTIONDISPLAY.add(buffereReaderMonitors.readLine());
				MONITORMATRIXTYPE.add(buffereReaderMonitors.readLine());
				MONITORDISPLAYBRIGHTNESS.add(buffereReaderMonitors.readLine());
				MONITORINTERFACES.add(buffereReaderMonitors.readLine());
				MONITORATTITUDEPARTIES.add(buffereReaderMonitors.readLine());
				MONITORCOLOR.add(buffereReaderMonitors.readLine());
				MONITORWEBCAM.add(buffereReaderMonitors.readLine());
				MONITORADDITIONAL.add(buffereReaderMonitors.readLine());
				MONITORDESCRIPTION.add(buffereReaderMonitors.readLine());
				MONITORPRICE.add(buffereReaderMonitors.readLine());

			}

			while (buffereReaderMonitorPhotos.ready()) {

				MONITORPHOTOSURL.add(buffereReaderMonitorPhotos.readLine());

			}

			buffereReaderMonitors.close();
			buffereReaderMonitorPhotos.close();
		}
	}
}
