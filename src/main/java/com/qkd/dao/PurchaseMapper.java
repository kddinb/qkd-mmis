package com.qkd.dao;

import com.qkd.entity.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PurchaseMapper")
public interface PurchaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    boolean saveNewPurchase(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    int insertSelective(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    Purchase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    boolean updateByPrimaryKeySelective(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Purchase record);

    List<Purchase> listAll(Purchase purchase);
}