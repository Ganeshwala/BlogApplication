package com.SpringBoot.BlogApp.Services.Impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.BlogApp.Services.FileService;

public class FileServiceImp implements FileService {

	@Override
	public String uploadImage(String folderLocation, MultipartFile file) {
		
		try {
			
			File saveImgLocation = new ClassPathResource(folderLocation).getFile();
			
			Path path = Paths.get(saveImgLocation.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			
			/*String fileName= file.getOriginalFilename(); //file name
			
			// fullpath name
			String fileStoragePath = path+File.separator+fileName;
			System.out.println("fileStoragePath==========>"+fileStoragePath);
			
			File storeLocation = new File(path);
			if(!storeLocation.exists()) {
				storeLocation.mkdir();
			}
			
			Files.copy(file.getInputStream(), Paths.get(fileStoragePath),StandardCopyOption.REPLACE_EXISTING);*/
			
		}catch(Exception e) {
			
		}
		
		
		return file.getOriginalFilename();
	}

}
