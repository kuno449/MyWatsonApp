package com.example.yoshiki.mywatsonapp.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Class {

    @Expose
    private String _class;
    @Expose
    private Double score;
    @Expose
    private String typeHierarchy;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The _class
     */
    public String getClass_() {
        return _class;
    }

    /**
     * 
     * @param _class
     *     The class
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * 
     * @return
     *     The score
     */
    public Double getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The typeHierarchy
     */
    public String getTypeHierarchy() {
        return typeHierarchy;
    }

    /**
     * 
     * @param typeHierarchy
     *     The type_hierarchy
     */
    public void setTypeHierarchy(String typeHierarchy) {
        this.typeHierarchy = typeHierarchy;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
