package com.qkd.entity;

import java.math.BigDecimal;
import java.io.Serializable;

public class PharmacyMed implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.DEPART_ID
     *
     * @mbggenerated
     */
    private String departId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.NMAE
     *
     * @mbggenerated
     */
    private String nmae;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.CODE
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.GNAME_ID
     *
     * @mbggenerated
     */
    private String gnameId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.PHAR_ID
     *
     * @mbggenerated
     */
    private String  pharId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.TYPE_ID
     *
     * @mbggenerated
     */
    private String typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.EFID
     *
     * @mbggenerated
     */
    private String efid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.RETAIL_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal retailPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.PACK_UNIT
     *
     * @mbggenerated
     */
    private String packUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.PACK_NUM
     *
     * @mbggenerated
     */
    private Long packNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.IN_USED
     *
     * @mbggenerated
     */
    private String inUsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.OUT_USED
     *
     * @mbggenerated
     */
    private String outUsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.STOCK
     *
     * @mbggenerated
     */
    private Long stock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pharmacy_med.REMARK
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.ID
     *
     * @return the value of pharmacy_med.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.ID
     *
     * @param id the value for pharmacy_med.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.DEPART_ID
     *
     * @return the value of pharmacy_med.DEPART_ID
     *
     * @mbggenerated
     */
    public String getDepartId() {
        return departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.DEPART_ID
     *
     * @param departId the value for pharmacy_med.DEPART_ID
     *
     * @mbggenerated
     */
    public void setDepartId(String departId) {
        this.departId = departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.NMAE
     *
     * @return the value of pharmacy_med.NMAE
     *
     * @mbggenerated
     */
    public String getNmae() {
        return nmae;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.NMAE
     *
     * @param nmae the value for pharmacy_med.NMAE
     *
     * @mbggenerated
     */
    public void setNmae(String nmae) {
        this.nmae = nmae == null ? null : nmae.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.CODE
     *
     * @return the value of pharmacy_med.CODE
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.CODE
     *
     * @param code the value for pharmacy_med.CODE
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.GNAME_ID
     *
     * @return the value of pharmacy_med.GNAME_ID
     *
     * @mbggenerated
     */
    public String getGnameId() {
        return gnameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.GNAME_ID
     *
     * @param gnameId the value for pharmacy_med.GNAME_ID
     *
     * @mbggenerated
     */
    public void setGnameId(String gnameId) {
        this.gnameId = gnameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.PHAR_ID
     *
     * @return the value of pharmacy_med.PHAR_ID
     *
     * @mbggenerated
     */
    public String getPharId() {
        return pharId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.PHAR_ID
     *
     * @param pharId the value for pharmacy_med.PHAR_ID
     *
     * @mbggenerated
     */
    public void setPharId(String pharId) {
        this.pharId = pharId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.TYPE_ID
     *
     * @return the value of pharmacy_med.TYPE_ID
     *
     * @mbggenerated
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.TYPE_ID
     *
     * @param typeId the value for pharmacy_med.TYPE_ID
     *
     * @mbggenerated
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.EFID
     *
     * @return the value of pharmacy_med.EFID
     *
     * @mbggenerated
     */
    public String getEfid() {
        return efid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.EFID
     *
     * @param efid the value for pharmacy_med.EFID
     *
     * @mbggenerated
     */
    public void setEfid(String efid) {
        this.efid = efid == null ? null : efid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.RETAIL_PRICE
     *
     * @return the value of pharmacy_med.RETAIL_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.RETAIL_PRICE
     *
     * @param retailPrice the value for pharmacy_med.RETAIL_PRICE
     *
     * @mbggenerated
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.PACK_UNIT
     *
     * @return the value of pharmacy_med.PACK_UNIT
     *
     * @mbggenerated
     */
    public String getPackUnit() {
        return packUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.PACK_UNIT
     *
     * @param packUnit the value for pharmacy_med.PACK_UNIT
     *
     * @mbggenerated
     */
    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit == null ? null : packUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.PACK_NUM
     *
     * @return the value of pharmacy_med.PACK_NUM
     *
     * @mbggenerated
     */
    public Long getPackNum() {
        return packNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.PACK_NUM
     *
     * @param packNum the value for pharmacy_med.PACK_NUM
     *
     * @mbggenerated
     */
    public void setPackNum(Long packNum) {
        this.packNum = packNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.IN_USED
     *
     * @return the value of pharmacy_med.IN_USED
     *
     * @mbggenerated
     */
    public String getInUsed() {
        return inUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.IN_USED
     *
     * @param inUsed the value for pharmacy_med.IN_USED
     *
     * @mbggenerated
     */
    public void setInUsed(String inUsed) {
        this.inUsed = inUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.OUT_USED
     *
     * @return the value of pharmacy_med.OUT_USED
     *
     * @mbggenerated
     */
    public String getOutUsed() {
        return outUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.OUT_USED
     *
     * @param outUsed the value for pharmacy_med.OUT_USED
     *
     * @mbggenerated
     */
    public void setOutUsed(String outUsed) {
        this.outUsed = outUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.STOCK
     *
     * @return the value of pharmacy_med.STOCK
     *
     * @mbggenerated
     */
    public Long getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.STOCK
     *
     * @param stock the value for pharmacy_med.STOCK
     *
     * @mbggenerated
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pharmacy_med.REMARK
     *
     * @return the value of pharmacy_med.REMARK
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pharmacy_med.REMARK
     *
     * @param remark the value for pharmacy_med.REMARK
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "PharmacyMed{" +
                "id=" + id +
                ", departId=" + departId +
                ", nmae='" + nmae + '\'' +
                ", code='" + code + '\'' +
                ", gnameId=" + gnameId +
                ", pharId=" + pharId +
                ", typeId=" + typeId +
                ", efid='" + efid + '\'' +
                ", retailPrice=" + retailPrice +
                ", packUnit='" + packUnit + '\'' +
                ", packNum=" + packNum +
                ", inUsed=" + inUsed +
                ", outUsed=" + outUsed +
                ", stock=" + stock +
                ", remark='" + remark + '\'' +
                '}';
    }
}