package com.banxia.infrastructure.persistent.dao;

import com.banxia.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author BanXia
 * @description: 奖品表 DAO
 * @Date 2025/10/16 16:36
 */
@Mapper
public interface IAwardDao {

    List<Award> queryList();
}
