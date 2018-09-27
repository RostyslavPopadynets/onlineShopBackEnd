package rostyslav.popadynets.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class UserReader {

	public static List<String> USERFIRSTNAMES = new LinkedList<>();
	public static List<String> USERLASTNAMES = new LinkedList<>();
	public static List<String> USEREMAILS = new LinkedList<>();
	public static List<String> USERPASSWORDS = new LinkedList<>();

	public static void read() throws Exception {

		File userFirstNames = new File("startValues/Users/userFirstNames");
		File userLastNames = new File("startValues/Users/userLastNames");
		File userEmails = new File("startValues/Users/userEmails");

		if (userFirstNames.exists() && userLastNames.exists() && userEmails.exists()) {

			FileReader fileReaderUserFirstNames = new FileReader(userFirstNames);
			FileReader fileReaderUserLastNames = new FileReader(userLastNames);
			FileReader fileReaderUserEmails = new FileReader(userEmails);

			BufferedReader buffereReaderUserFirstNames = new BufferedReader(fileReaderUserFirstNames);
			BufferedReader buffereReaderUserLastNames = new BufferedReader(fileReaderUserLastNames);
			BufferedReader buffereReaderUserEmails = new BufferedReader(fileReaderUserEmails);

			while (buffereReaderUserFirstNames.ready()) {
				char firstName[] = buffereReaderUserFirstNames.readLine().toLowerCase().toCharArray();
				String firstLater = firstName[0] + "";
				String name = "";
				for (int i = 1; i < firstName.length; i++) {
					name += firstName[i];
				}
				String reghtName = firstLater.toUpperCase() + name;
				USERFIRSTNAMES.add(reghtName);
			}

			while (buffereReaderUserLastNames.ready()) {
				char lastName[] = buffereReaderUserLastNames.readLine().toLowerCase().toCharArray();
				String firstLater = lastName[0] + "";
				String name = "";
				for (int i = 1; i < lastName.length; i++) {
					name += lastName[i];
				}
				String reghtName = firstLater.toUpperCase() + name;
				USERLASTNAMES.add(reghtName);
			}

			while (buffereReaderUserEmails.ready()) {
				USEREMAILS.add(buffereReaderUserEmails.readLine().toLowerCase());
			}

			for (int i = 0; i < 60; i++) {
				String password = USEREMAILS.get(i).split("@")[0];
				USERPASSWORDS.add(password);
			}

			buffereReaderUserFirstNames.close();
			buffereReaderUserLastNames.close();
			buffereReaderUserEmails.close();
		}
	}
}
