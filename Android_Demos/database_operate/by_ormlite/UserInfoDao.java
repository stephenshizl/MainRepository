package com.example.a61555.ormlitedemo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 61555 on 2017/8/4.
 * 封装 DAO 方法
 */

public class UserInfoDao {

    public Dao dao = null;

    public UserInfoDao(Context context) {
        dao = DatabaseHelper.getDaoInstance(context);
    }

    /**
     * 插入单条数据
     * @param userInfo
     * @return
     */
    public boolean insertSingle(UserInfo userInfo){
        if (userInfo != null) {
            try {
                return (dao.create(userInfo) > 0) ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 批量插入
     * @param userList
     * @return
     */
    public boolean insertBatch(List userList) {
        if (userList != null) {
            try {
                return (dao.create(userList) > 0) ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 单条修改
     * @param userInfo
     * @return
     */
    public boolean updateSingle(UserInfo userInfo) {
        if (userInfo != null) {
            try {
                return (dao.update(userInfo) > 0) ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 单条删除
     * @param userInfo
     * @return
     */
    public boolean deleteSingle(UserInfo userInfo) {
        if (userInfo != null) {
            try {
                return (dao.delete(userInfo) > 0) ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 以 ID 查询单条数据
     * @param id
     * @return
     */
    public UserInfo findById(int id) {
        try {
            return (UserInfo) dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
