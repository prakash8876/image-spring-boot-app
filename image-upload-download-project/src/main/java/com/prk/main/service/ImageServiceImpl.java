package com.prk.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prk.main.model.ImageModel;
import com.prk.main.repository.ImageRepo;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageRepo repo;

	@Override
	public ImageModel saveImage(ImageModel img) {
		ImageModel savedImage = repo.save(img);
		return savedImage;
	}

	@Override
	public Optional<ImageModel> getImageById(int id) {
		Optional<ImageModel> img = repo.findById(id);
		return img;
	}

	@Override
	public void deleteImageById(int id) {
		repo.deleteById(id);
	}

}
