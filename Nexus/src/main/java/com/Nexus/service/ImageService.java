package com.Nexus.service;

import com.Nexus.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ImageService {

    public Image create(Image image);
    public List<Image> viewAll();
    public Image viewById(long id);



}
