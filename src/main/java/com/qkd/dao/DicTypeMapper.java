package com.qkd.dao;

import com.qkd.entity.DicType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DicTypeMapper")
public interface DicTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    boolean saveNewDicType(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    int insertSelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    DicType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    boolean updateByPrimaryKeySelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DicType record);


    List<DicType> listAll(DicType dicType);

}