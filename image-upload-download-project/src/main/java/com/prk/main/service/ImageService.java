package com.prk.main.service;

import java.util.Optional;

import com.prk.main.model.ImageModel;

public interface ImageService {

	ImageModel saveImage(ImageModel img);

	Optional<ImageModel> getImageById(int id);

	void deleteImageById(int id);

}
