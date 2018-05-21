package com.qkd.dao;

import com.qkd.entity.Depart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DepartMapper")
public interface DepartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    int insert(Depart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    boolean saveNewDepart(Depart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    Depart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    boolean updateByPrimaryKeySelective(Depart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Depart record);

    List<Depart> listAll(Depart depart);
}