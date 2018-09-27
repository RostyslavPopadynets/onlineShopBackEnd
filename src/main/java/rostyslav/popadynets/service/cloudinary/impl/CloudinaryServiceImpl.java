package rostyslav.popadynets.service.cloudinary.impl;

import java.io.InputStream;
import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;

import rostyslav.popadynets.service.cloudinary.CloudinaryService;

@Service
@PropertySource("classpath:cloudinary.properties")
public class CloudinaryServiceImpl implements CloudinaryService {

	private Cloudinary cloudinary;

	public CloudinaryServiceImpl(Environment env) {
		this.cloudinary = new Cloudinary(env.getProperty("cloudinary.env"));
	}

	@Override
	public String uploadFile(MultipartFile file, String folderName) {
		Uploader uploader = cloudinary.uploader();
		
		String originalName = file.getOriginalFilename();
		String fileName = originalName.substring(0, originalName.lastIndexOf("."));
		
		Map<?, ?> options = ObjectUtils.asMap(
					"public_id", fileName, //доступ до файлу за назвою
					"folder", folderName, //створюємо папки під наші фото 
					"overwrite", true //переписуємо картинку якщо назва та сама
				);
		
		Map<?, ?> result = null;
		
		try (InputStream inputStream = file.getInputStream()){
			result = uploader.uploadLarge(inputStream, options);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return (String) result.get("url");
	}
		
}
