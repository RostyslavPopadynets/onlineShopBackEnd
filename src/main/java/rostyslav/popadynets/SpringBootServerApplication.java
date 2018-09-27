package rostyslav.popadynets;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import rostyslav.popadynets.domain.LaptopDTO;
import rostyslav.popadynets.domain.MonitorDTO;
import rostyslav.popadynets.domain.PCDTO;
import rostyslav.popadynets.domain.PhoneDTO;
import rostyslav.popadynets.domain.TVDTO;
import rostyslav.popadynets.domain.TabletDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.entity.enums.UserRole;
import rostyslav.popadynets.reader.LaptopReader;
import rostyslav.popadynets.reader.MonitorReader;
import rostyslav.popadynets.reader.PCReader;
import rostyslav.popadynets.reader.PhoneReader;
import rostyslav.popadynets.reader.TVReader;
import rostyslav.popadynets.reader.TabletReader;
import rostyslav.popadynets.reader.UserReader;
import rostyslav.popadynets.repository.LaptopRepository;
import rostyslav.popadynets.repository.MonitorRepository;
import rostyslav.popadynets.repository.PCRepository;
import rostyslav.popadynets.repository.PhoneRepository;
import rostyslav.popadynets.repository.TVRepository;
import rostyslav.popadynets.repository.TabletRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.LaptopService;
import rostyslav.popadynets.service.MonitorService;
import rostyslav.popadynets.service.PCService;
import rostyslav.popadynets.service.PhoneService;
import rostyslav.popadynets.service.TVService;
import rostyslav.popadynets.service.TabletService;
import rostyslav.popadynets.service.UserService;

@SpringBootApplication
public class SpringBootServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootServerApplication.class, args);
		AddTVs(context);
		AddTablets(context);
		AddPhones(context);
		AddPCs(context);
		AddMonitors(context);
		AddLaptops(context);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() == 0) {
			UserDTO userAdmin = UserDTO.builder().firstName("Admin").lastName("Admin").email("rostyslav.popadynets@gmail.com")
					.role(UserRole.ROLE_ADMIN).password("rostyslav.popadynets").emailVarificationStatus(Boolean.TRUE).build();
			System.out.println(userAdmin);
			userService.addUser(userAdmin);
			try {
				UserReader.read();
				for (int i = 0; i < 60; i++) {
					UserDTO user = UserDTO.builder().firstName(UserReader.USERFIRSTNAMES.get(i))
							.lastName(UserReader.USERLASTNAMES.get(i)).email(UserReader.USEREMAILS.get(i))
							.password(UserReader.USERPASSWORDS.get(i)).emailVarificationStatus(Boolean.TRUE).build();

					System.out.println(user);
					userService.addUser(user);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про користувача.");
				e.printStackTrace();
			}
		}
	}

	public static void AddTVs(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		TVService tvService = context.getBean(TVService.class);
		TVRepository tvRepository = context.getBean(TVRepository.class);

		if (tvRepository.count() == 0) {
			try {
				UserReader.read();
				TVReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					TVDTO tv = TVDTO.builder().manufacturer(TVReader.TVMANUFACTURER.get(i))
							.model(TVReader.TVMODEL.get(i))
							.displayDiagonal(BigDecimal.valueOf(Long.parseLong(TVReader.TVDISPLAYDIAGONAL.get(i))))
							.resolutionDisplay(TVReader.TVRESOLUTIONDISPLAY.get(i))
							.smartPlatform(TVReader.TVSMARTPLATFORM.get(i))
							.displayBrightness(TVReader.TVDISPLAYBRIGHTNESS.get(i)).color(TVReader.TVCOLOR.get(i))
							.hdr(Integer.parseInt(TVReader.TVHDR.get(i)) == 1 ? true : false)
							.additional(TVReader.TVADITIONAL.get(i)).description(TVReader.TVDESCRIPTION.get(i))
							.price(BigDecimal.valueOf(Long.parseLong(TVReader.TVPRICE.get(i))))
							.description(TVReader.TVDESCRIPTION.get(i)).color(TVReader.TVCOLOR.get(i))
							.weight(BigDecimal.valueOf(Double.parseDouble(TVReader.TVWEIGHT.get(i))))
							.dimensionsHeight(
									BigDecimal.valueOf(Double.parseDouble(TVReader.TVDIMENSIONSHEIGHT.get(i))))
							.dimensionsWidth(BigDecimal.valueOf(Double.parseDouble(TVReader.TVWEIGHT.get(i))))
							.dimensionsLength(
									BigDecimal.valueOf(Double.parseDouble(TVReader.TVDIMENSIONSLENGTH.get(i))))
							.imageUrl1(TVReader.TVPHOTOSURL.get(j++)).imageUrl2(TVReader.TVPHOTOSURL.get(j++))
							.imageUrl3(TVReader.TVPHOTOSURL.get(j++)).imageUrl4(TVReader.TVPHOTOSURL.get(j++))
							.imageUrl5(TVReader.TVPHOTOSURL.get(j++)).build();

					tv.setTvUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i)));
					System.out.println(tv);
					tvService.addTV(tv);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про телевізор.");
				e.printStackTrace();
			}
		}
	}

	public static void AddTablets(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		TabletService tabletService = context.getBean(TabletService.class);
		TabletRepository tabletRepository = context.getBean(TabletRepository.class);

		if (tabletRepository.count() == 0) {
			try {
				UserReader.read();
				TabletReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					TabletDTO tablet = TabletDTO.builder().manufacturer(TabletReader.TABLETMANUFACTURER.get(i))
							.model(TabletReader.TABLETMODEL.get(i))
							.displayDiagonal(
									BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETDISPLAYDIAGONAL.get(i))))
							.resolutionDisplay(TabletReader.TABLETRESOLUTIONDISPLAY.get(i))
							.ram(TabletReader.TABLETRAM.get(i)).os(TabletReader.TABLETOS.get(i))
							.driveCapacity(TabletReader.TABLETDRIVECAPACITY.get(i))
							.color(TabletReader.TABLETCOLOR.get(i)).processor(TabletReader.TABLETPROCESSOR.get(i))
							.maxFlashMemory(TabletReader.TABLETMAXFLASHMEMOEY.get(i))
							.frontalCamera(TabletReader.TABLETFRONTALCAMERA.get(i))
							.mainCamera(TabletReader.TABLETMAINCAMERA.get(i))
							.coreNumber(TabletReader.TABLETCORENUMBER.get(i))
							.frequency(BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETFREQUENCY.get(i))))
							.batteryCapacity(TabletReader.TABLETBATTERYCAPACITY.get(i))
							.additional(TabletReader.TABLETADITIONAL.get(i))
							.description(TabletReader.TABLETDESCRIPTION.get(i))
							.weight(BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETWEIGHT.get(i))))
							.dimensionsLength(
									BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETDIMENSIONSLENGHT.get(i))))
							.dimensionsHeight(
									BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETDIMENSIONSHEIGHT.get(i))))
							.dimensionsWidth(
									BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETDIMENSIONSWIDTH.get(i))))
							.price(BigDecimal.valueOf(Double.parseDouble(TabletReader.TABLETPRICE.get(i))))
							.imageUrl1(TabletReader.TABLETPHOTOSURL.get(j++))
							.imageUrl2(TabletReader.TABLETPHOTOSURL.get(j++))
							.imageUrl3(TabletReader.TABLETPHOTOSURL.get(j++))
							.imageUrl4(TabletReader.TABLETPHOTOSURL.get(j++))
							.imageUrl5(TabletReader.TABLETPHOTOSURL.get(j++)).build();

					tablet.setTabletUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i + 10)));
					System.out.println(tablet);
					tabletService.addTablet(tablet);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про планшет.");
				e.printStackTrace();
			}
		}
	}

	public static void AddPhones(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		PhoneService phoneService = context.getBean(PhoneService.class);
		PhoneRepository phoneRepository = context.getBean(PhoneRepository.class);

		if (phoneRepository.count() == 0) {
			try {
				UserReader.read();
				PhoneReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					PhoneDTO phone = PhoneDTO.builder().manufacturer(PhoneReader.PHONEMANUFACTURER.get(i))
							.model(PhoneReader.PHONEMODEL.get(i))
							.displayDiagonal(
									BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEDISPLAYDIAGONAL.get(i))))
							.resolutionDisplay(PhoneReader.PHONERESOLUTIONDISPLAY.get(i))
							.ram(PhoneReader.PHONERAM.get(i)).os(PhoneReader.PHONEOS.get(i))
							.driveCapacity(PhoneReader.PHONEDRIVECAPACITY.get(i)).color(PhoneReader.PHONECOLOR.get(i))
							.numberSim(PhoneReader.PHONENUMBERSIM.get(i))
							.maxFlashMemory(PhoneReader.PHONEMAXFLASHMEMOEY.get(i))
							.frontalCamera(PhoneReader.PHONEFRONTALCAMERA.get(i))
							.mainCamera(PhoneReader.PHONEMAINCAMERA.get(i))
							.flash(Integer.parseInt(PhoneReader.PHONEFLESH.get(i)) == 1 ? true : false)
							.autofocus(Integer.parseInt(PhoneReader.PHONEAUTOFOCUS.get(i)) == 1 ? true : false)
							.coreNumber(PhoneReader.PHONECORENUMBER.get(i))
							.frequency(BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEFREQUENCY.get(i))))
							.batteryCapacity(PhoneReader.PHONEBATTERYCAPACITY.get(i))
							.color(PhoneReader.PHONECOLOR.get(i)).additional(PhoneReader.PHONEADITIONAL.get(i))
							.description(PhoneReader.PHONEDESCRIPTION.get(i))
							.weight(BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEWEIGHT.get(i))))
							.price(BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEPRICE.get(i))))
							.dimensionsWidth(
									BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEDIMENSIONSWIDTH.get(i))))
							.dimensionsLength(
									BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEDIMENSIONSLENGHT.get(i))))
							.dimensionsHeight(
									BigDecimal.valueOf(Double.parseDouble(PhoneReader.PHONEDIMENSIONSHEIGHT.get(i))))
							.imageUrl1(PhoneReader.PHONEPHOTOSURL.get(j++))
							.imageUrl2(PhoneReader.PHONEPHOTOSURL.get(j++))
							.imageUrl3(PhoneReader.PHONEPHOTOSURL.get(j++))
							.imageUrl4(PhoneReader.PHONEPHOTOSURL.get(j++))
							.imageUrl5(PhoneReader.PHONEPHOTOSURL.get(j++)).build();

					phone.setPhoneUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i + 20)));
					System.out.println(phone);
					phoneService.addPhone(phone);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про планшет.");
				e.printStackTrace();
			}
		}
	}

	public static void AddPCs(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		PCService pcService = context.getBean(PCService.class);
		PCRepository pcRepository = context.getBean(PCRepository.class);

		if (pcRepository.count() == 0) {
			try {
				UserReader.read();
				PCReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					PCDTO pc = PCDTO.builder().manufacturer(PCReader.PCMANUFACTURER.get(i))
							.model(PCReader.PCMODEL.get(i)).processor(PCReader.PCPROCESSOR.get(i))
							.ram(PCReader.PCRAM.get(i)).os(PCReader.PCOS.get(i))
							.driveCapacity(PCReader.PCDRIVECAPACITY.get(i)).color(PCReader.PCCOLOR.get(i))
							.cooling(PCReader.PCCOOLING.get(i)).graphicAdapter(PCReader.PCGRAPHICADAPTER.get(i))
							.additional(PCReader.PCADITIONAL.get(i)).description(PCReader.PCDESCRIPTION.get(i))
							.weight(BigDecimal.valueOf(Double.parseDouble(PCReader.PCWEIGHT.get(i))))
							.price(BigDecimal.valueOf(Long.parseLong(PCReader.PCPRICE.get(i))))
							.dimensionsHeight(
									BigDecimal.valueOf(Double.parseDouble(PCReader.PCDIMENSIONSHEIGHT.get(i))))
							.dimensionsWidth(BigDecimal.valueOf(Double.parseDouble(PCReader.PCDIMENSIONSWIDTH.get(i))))
							.dimensionsLength(
									BigDecimal.valueOf(Double.parseDouble(PCReader.PCDIMENSIONSLENGTH.get(i))))
							.imageUrl1(PCReader.PCPHOTOSURL.get(j++)).imageUrl2(PCReader.PCPHOTOSURL.get(j++))
							.imageUrl3(PCReader.PCPHOTOSURL.get(j++)).imageUrl4(PCReader.PCPHOTOSURL.get(j++))
							.imageUrl5(PCReader.PCPHOTOSURL.get(j++)).build();

					pc.setPcUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i + 30)));
					System.out.println(pc);
					pcService.addPC(pc);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про телевізор.");
				e.printStackTrace();
			}
		}
	}

	public static void AddMonitors(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		MonitorService monitorService = context.getBean(MonitorService.class);
		MonitorRepository monitorRepository = context.getBean(MonitorRepository.class);

		if (monitorRepository.count() == 0) {
			try {
				UserReader.read();
				MonitorReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					MonitorDTO monitor = MonitorDTO.builder().manufacturer(MonitorReader.MONITORMANUFACTURER.get(i))
							.model(MonitorReader.MONITORMODEL.get(i))
							.displayDiagonal(
									BigDecimal.valueOf(Double.parseDouble(MonitorReader.MONITORDISPLAYDIAGONAL.get(i))))
							.maxResolutionDisplay(MonitorReader.MONITORMAXRESOLUTIONDISPLAY.get(i))
							.matrixType(MonitorReader.MONITORMATRIXTYPE.get(i))
							.displayBrightness(MonitorReader.MONITORDISPLAYBRIGHTNESS.get(i))
							.interfaces(MonitorReader.MONITORINTERFACES.get(i))
							.attitudeParties(MonitorReader.MONITORATTITUDEPARTIES.get(i))
							.color(MonitorReader.MONITORCOLOR.get(i))
							.webcam(Integer.parseInt(MonitorReader.MONITORWEBCAM.get(i)) == 1 ? true : false)
							.additional(MonitorReader.MONITORADDITIONAL.get(i))
							.description(MonitorReader.MONITORDESCRIPTION.get(i))
							.price(BigDecimal.valueOf(Long.parseLong(MonitorReader.MONITORPRICE.get(i))))
							.imageUrl1(MonitorReader.MONITORPHOTOSURL.get(j++))
							.imageUrl2(MonitorReader.MONITORPHOTOSURL.get(j++))
							.imageUrl3(MonitorReader.MONITORPHOTOSURL.get(j++))
							.imageUrl4(MonitorReader.MONITORPHOTOSURL.get(j++))
							.imageUrl5(MonitorReader.MONITORPHOTOSURL.get(j++)).build();

					monitor.setMonitorUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i + 40)));
					System.out.println(monitor);
					monitorService.addMonitor(monitor);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про телевізор.");
				e.printStackTrace();
			}
		}
	}

	public static void AddLaptops(ConfigurableApplicationContext context) {

		UserRepository userRepository = context.getBean(UserRepository.class);
		LaptopService laptopService = context.getBean(LaptopService.class);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		if (laptopRepository.count() == 0) {
			try {
				UserReader.read();
				LaptopReader.read();
				for (int i = 0, j = 0; i < 10; i++) {

					LaptopDTO laptop = LaptopDTO.builder().manufacturer(LaptopReader.LAPTOPMANUFACTURER.get(i))
							.model(LaptopReader.LAPTOPMODEL.get(i)).processor(LaptopReader.LAPTOPPROCESSOR.get(i))
							.diagonalScreen(LaptopReader.LAPTOPDIAGONALSCREEN.get(i)).ram(LaptopReader.LAPTOPRAM.get(i))
							.driveCapacity(LaptopReader.LAPTOPDRIVECAPACITY.get(i))
							.color(LaptopReader.LAPTOPCOLOR.get(i))
							.graphicAdapter(LaptopReader.LAPTOPGRAPHICADAPTER.get(i))
							.battery(LaptopReader.LAPTOPBATTERY.get(i)).additional(LaptopReader.LAPTOPADITIONAL.get(i))
							.description(LaptopReader.LAPTOPDESCRIPTION.get(i))
							.weight(BigDecimal.valueOf(Double.parseDouble(LaptopReader.LAPTOPWEIGHT.get(i))))
							.price(BigDecimal.valueOf(Double.parseDouble(LaptopReader.LAPTOPRICE.get(i))))
							.dimensionsWidth(
									BigDecimal.valueOf(Double.parseDouble(LaptopReader.LAPTOPDIMENSIONSWIDTH.get(i))))
							.dimensionsHeight(
									BigDecimal.valueOf(Double.parseDouble(LaptopReader.LAPTOPDIMENSIONSHEIGHT.get(i))))
							.dimensionsLength(
									BigDecimal.valueOf(Double.parseDouble(LaptopReader.LAPTOPDIMENSIONSLENGTH.get(i))))
							.imageUrl1(LaptopReader.LAPTOPPHOTOSURL.get(j++))
							.imageUrl2(LaptopReader.LAPTOPPHOTOSURL.get(j++))
							.imageUrl3(LaptopReader.LAPTOPPHOTOSURL.get(j++))
							.imageUrl4(LaptopReader.LAPTOPPHOTOSURL.get(j++))
							.imageUrl5(LaptopReader.LAPTOPPHOTOSURL.get(j++)).build();

					laptop.setLaptopUser(userRepository.findByEmail(UserReader.USEREMAILS.get(i + 50)));
					System.out.println(laptop);
					laptopService.addLaptop(laptop);
				}
			} catch (Exception e) {
				System.err.println("Помилка зчитування даних про планшет.");
				e.printStackTrace();
			}
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
