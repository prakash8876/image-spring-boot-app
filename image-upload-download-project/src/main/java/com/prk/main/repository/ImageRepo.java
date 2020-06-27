package com.prk.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prk.main.model.ImageModel;

@Repository
public interface ImageRepo extends JpaRepository<ImageModel, Integer> {

}
