package com.qkd.dao;

import com.qkd.entity.StorMed;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StorMedMapper")
public interface StorMedMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    boolean saveNewStorMed(StorMed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    int insertSelective(StorMed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    StorMed selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    boolean updateByPrimaryKeySelective(StorMed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stor_med
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StorMed record);

    List<StorMed> listAll(StorMed storMed);

    List<StorMed> listAllFilt(StorMed storMed);

    boolean  updateStockByNmaeAndDepart(StorMed storMed);

    boolean  reduceStockByNmaeAndDepart(StorMed storMed);
}