package com.oolong.controller.service;

import com.oolong.model.bean.Dish;
import com.oolong.view.utils.JdbcCrud;


import java.util.List;

/**
 * @author oolong
 */
public class DishServer {
    public static List<Dish> checkAll() {
        String sql = "select id,price,name,category from dish";
        List<Dish> list = JdbcCrud.getForList(Dish.class, sql);

        return list;
        }
 }

