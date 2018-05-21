package com.qkd.dao;

import com.qkd.entity.Apply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ApplyMapper")
public interface ApplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    boolean saveNewApply(Apply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    int insertSelective(Apply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    Apply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    boolean updateByPrimaryKeySelective(Apply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Apply record);

    List<Apply> listAll(Apply apply);
}