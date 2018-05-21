package com.qkd.entity;

import java.io.Serializable;

public class DicType implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_type.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_type.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_type.CODE
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_type.ID
     *
     * @return the value of dic_type.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_type.ID
     *
     * @param id the value for dic_type.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_type.NAME
     *
     * @return the value of dic_type.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_type.NAME
     *
     * @param name the value for dic_type.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_type.CODE
     *
     * @return the value of dic_type.CODE
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_type.CODE
     *
     * @param code the value for dic_type.CODE
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        return "DicType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}