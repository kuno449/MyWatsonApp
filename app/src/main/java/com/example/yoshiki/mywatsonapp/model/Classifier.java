package com.example.yoshiki.mywatsonapp.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classifier {

    @Expose
    private List<Class> classes = new ArrayList<Class>();
    @Expose
    private String classifierId;
    @Expose
    private String name;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The classes
     */
    public List<Class> getClasses() {
        return classes;
    }

    /**
     * 
     * @param classes
     *     The classes
     */
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    /**
     * 
     * @return
     *     The classifierId
     */
    public String getClassifierId() {
        return classifierId;
    }

    /**
     * 
     * @param classifierId
     *     The classifier_id
     */
    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
