package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class PhoneReader {

	public static List<String> PHONEMANUFACTURER = new LinkedList<>();
	public static List<String> PHONEMODEL = new LinkedList<>();
	public static List<String> PHONEDISPLAYDIAGONAL = new LinkedList<>();
	public static List<String> PHONERESOLUTIONDISPLAY = new LinkedList<>();
	public static List<String> PHONERAM = new LinkedList<>();
	public static List<String> PHONEOS = new LinkedList<>();
	public static List<String> PHONEDRIVECAPACITY = new LinkedList<>();
	public static List<String> PHONECOLOR = new LinkedList<>();
	public static List<String> PHONENUMBERSIM = new LinkedList<>();
	public static List<String> PHONEMAXFLASHMEMOEY = new LinkedList<>();
	public static List<String> PHONEFRONTALCAMERA = new LinkedList<>();
	public static List<String> PHONEMAINCAMERA = new LinkedList<>();
	public static List<String> PHONEFLESH = new LinkedList<>();
	public static List<String> PHONEAUTOFOCUS = new LinkedList<>();
	public static List<String> PHONECORENUMBER = new LinkedList<>();
	public static List<String> PHONEFREQUENCY = new LinkedList<>();
	public static List<String> PHONEBATTERYCAPACITY = new LinkedList<>();
	public static List<String> PHONEADITIONAL = new LinkedList<>();
	public static List<String> PHONEDESCRIPTION = new LinkedList<>();
	public static List<String> PHONEWEIGHT = new LinkedList<>();
	public static List<String> PHONEPRICE = new LinkedList<>();
	public static List<String> PHONEDIMENSIONSWIDTH = new LinkedList<>();
	public static List<String> PHONEDIMENSIONSLENGHT = new LinkedList<>();
	public static List<String> PHONEDIMENSIONSHEIGHT = new LinkedList<>();
	public static List<String> PHONEPHOTOSURL = new LinkedList<>();

	public static void read() throws Exception {

		File phones = new File("startValues/Phones/phones");

		File phonePhotos = new File("startValues/Phones/phonePhotosUrl");

		if (phones.exists() && phonePhotos.exists()) {

			FileReader fileReaderPhones = new FileReader(phones);

			BufferedReader buffereReaderPhones = new BufferedReader(fileReaderPhones);

			FileReader fileReaderPhonePhotos = new FileReader(phonePhotos);

			BufferedReader buffereReaderPhonePhotos = new BufferedReader(fileReaderPhonePhotos);

			while (buffereReaderPhones.ready()) {

				PHONEMANUFACTURER.add(buffereReaderPhones.readLine());
				PHONEMODEL.add(buffereReaderPhones.readLine());
				PHONEDISPLAYDIAGONAL.add(buffereReaderPhones.readLine());
				PHONERESOLUTIONDISPLAY.add(buffereReaderPhones.readLine());
				PHONERAM.add(buffereReaderPhones.readLine());
				PHONEOS.add(buffereReaderPhones.readLine());
				PHONEDRIVECAPACITY.add(buffereReaderPhones.readLine());
				PHONECOLOR.add(buffereReaderPhones.readLine());
				PHONENUMBERSIM.add(buffereReaderPhones.readLine());
				PHONEMAXFLASHMEMOEY.add(buffereReaderPhones.readLine());
				PHONEFRONTALCAMERA.add(buffereReaderPhones.readLine());
				PHONEMAINCAMERA.add(buffereReaderPhones.readLine());
				PHONEFLESH.add(buffereReaderPhones.readLine());
				PHONEAUTOFOCUS.add(buffereReaderPhones.readLine());
				PHONECORENUMBER.add(buffereReaderPhones.readLine());
				PHONEFREQUENCY.add(buffereReaderPhones.readLine());
				PHONEBATTERYCAPACITY.add(buffereReaderPhones.readLine());
				PHONEADITIONAL.add(buffereReaderPhones.readLine());
				PHONEDESCRIPTION.add(buffereReaderPhones.readLine());
				PHONEWEIGHT.add(buffereReaderPhones.readLine());
				PHONEPRICE.add(buffereReaderPhones.readLine());
				PHONEDIMENSIONSWIDTH.add(buffereReaderPhones.readLine());
				PHONEDIMENSIONSLENGHT.add(buffereReaderPhones.readLine());
				PHONEDIMENSIONSHEIGHT.add(buffereReaderPhones.readLine());

			}

			while (buffereReaderPhonePhotos.ready()) {

				PHONEPHOTOSURL.add(buffereReaderPhonePhotos.readLine());

			}

			buffereReaderPhones.close();
			buffereReaderPhonePhotos.close();
		}
	}
}