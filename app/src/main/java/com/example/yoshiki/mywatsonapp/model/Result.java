package com.example.yoshiki.mywatsonapp.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    @Expose
    private Integer customClasses;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @Expose
    private Integer imagesProcessed;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The customClasses
     */
    public Integer getCustomClasses() {
        return customClasses;
    }

    /**
     * 
     * @param customClasses
     *     The custom_classes
     */
    public void setCustomClasses(Integer customClasses) {
        this.customClasses = customClasses;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The imagesProcessed
     */
    public Integer getImagesProcessed() {
        return imagesProcessed;
    }

    /**
     * 
     * @param imagesProcessed
     *     The images_processed
     */
    public void setImagesProcessed(Integer imagesProcessed) {
        this.imagesProcessed = imagesProcessed;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
