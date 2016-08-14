package com.example.yoshiki.mywatsonapp.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image {

    @Expose
    private List<Classifier> classifiers = new ArrayList<Classifier>();
    @Expose
    private String resolvedUrl;
    @Expose
    private String sourceUrl;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The classifiers
     */
    public List<Classifier> getClassifiers() {
        return classifiers;
    }

    /**
     * 
     * @param classifiers
     *     The classifiers
     */
    public void setClassifiers(List<Classifier> classifiers) {
        this.classifiers = classifiers;
    }

    /**
     * 
     * @return
     *     The resolvedUrl
     */
    public String getResolvedUrl() {
        return resolvedUrl;
    }

    /**
     * 
     * @param resolvedUrl
     *     The resolved_url
     */
    public void setResolvedUrl(String resolvedUrl) {
        this.resolvedUrl = resolvedUrl;
    }

    /**
     * 
     * @return
     *     The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 
     * @param sourceUrl
     *     The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
