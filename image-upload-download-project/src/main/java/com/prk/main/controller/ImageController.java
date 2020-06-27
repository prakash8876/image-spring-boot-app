package com.prk.main.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prk.main.model.ImageModel;
import com.prk.main.service.ImageService;

@RestController
@CrossOrigin
@RequestMapping(path = "/img")
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	@RequestMapping(value = "/")
	public String workingOrNot() { return "Project working";} 
	
	// for uploading image to db
	@PostMapping("/upload")
	public ImageModel uploadImage(@RequestParam MultipartFile file) throws IOException {
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),file.getSize(), file.getBytes());
		ImageModel savedImage = service.saveImage(img);
		System.out.println("Image Saved.");
		return savedImage;
	}
	
	// retrieving image from db
	@GetMapping(value = "/getimage/{id}")
	public ImageModel getImage(@PathVariable("id") int id) throws IOException {
		Optional<ImageModel> retrievedImage = service.getImageById(id);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(), retrievedImage.get().getSize(), retrievedImage.get().getImg());
		System.out.println("Image Retrieved");
		return img;
	}
	
	// deleting image from db
	@DeleteMapping(value = "/delete/{id}")
	public String deleteImageById(@PathVariable("id")int id) throws IOException {
		service.deleteImageById(id);
		return "Deleted";
	}
	
	// updating or replacing image in db
	@PutMapping("/replace/{id}")
	public String replaceImage(@PathVariable("id") int id,@RequestParam MultipartFile file) throws IOException {
		Optional<ImageModel> img = service.getImageById(id);
		img.get().setName(file.getOriginalFilename());
		img.get().setType(file.getContentType());
		img.get().setSize(file.getSize());
		img.get().setImg(file.getBytes());
		ImageModel replacedImage = img.get();
		service.saveImage(replacedImage);
		return "Image replaced";
	}

}
