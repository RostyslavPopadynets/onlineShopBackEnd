package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.MonitorDTO;
import rostyslav.popadynets.domain.filters.MonitorFilter;

public interface MonitorService {

	public void addMonitor(MonitorDTO monitorDTO);
	
	public MonitorDTO findMonitorById(String monitorid);
	
	public List<MonitorDTO> findAllMonitors();
	
	public MonitorDTO findByMonitorManufacturer(String manufacturer);

	public MonitorDTO findByMonitorModel(String model);

	public MonitorDTO findByMonitorDisplayDiagonal(BigDecimal displayDiagonal);

	public MonitorDTO findByMonitorMaxResolutionDisplay(String maxResolutionDisplay);

	public MonitorDTO findByMonitorMatrixType(String matrixType);

	public MonitorDTO findByMonitorDisplayBrightness(String displayBrightness);

	public MonitorDTO findByMonitorInterfaces(String interfaces);

	public MonitorDTO findByMonitorAttitudeParties(String attitudeParties);

	public MonitorDTO findByMonitorColor(String color);

	public MonitorDTO findByMonitorWebcam(boolean webcam);

	public MonitorDTO findByMonitorPrice(BigDecimal price);
	
	public List<MonitorDTO> findMonitorByUserId(String userId);
	
	public void deleteMonitor(String monitorId);
	
	public void updateMonitor(MonitorDTO monitorDTO);

	public List<MonitorDTO> findAllMonitorsBySpecification(MonitorFilter filter, String ret);
	
	public List<MonitorDTO> findAllMonitorsByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
