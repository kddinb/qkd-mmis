package com.qkd.entity;

import java.io.Serializable;

public class DicPhar implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_phar.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_phar.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_phar.CODE
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_phar.PARENT_ID
     *
     * @mbggenerated
     */
    private Integer parentId;

    private Integer _parentId;

    private  String state;


    public Integer get_parentId()
    {
        return _parentId;
    }

    public void set_parentId(Integer _parentId)
    {
        this._parentId = _parentId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_phar.ID
     *
     * @return the value of dic_phar.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_phar.ID
     *
     * @param id the value for dic_phar.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_phar.NAME
     *
     * @return the value of dic_phar.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_phar.NAME
     *
     * @param name the value for dic_phar.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_phar.CODE
     *
     * @return the value of dic_phar.CODE
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_phar.CODE
     *
     * @param code the value for dic_phar.CODE
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_phar.PARENT_ID
     *
     * @return the value of dic_phar.PARENT_ID
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_phar.PARENT_ID
     *
     * @param parentId the value for dic_phar.PARENT_ID
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "DicPhar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}