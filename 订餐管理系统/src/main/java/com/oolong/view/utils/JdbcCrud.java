package com.oolong.view.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oolong
 */
public class JdbcCrud {
    /**
     * @Description 此方法是数据库的通用增删改方法，
     * @author oolong
     * @date  10:43
     * @since version-1.0
     * @return 返回值为修改成功条目数，如果为0则修改失败
     */
    @Test
    public void updateTest(){

    }
    public static void update(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            // 1.连接数据库
            connection = JdbcUtil.getConnection();
            // 2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                //小心参数声明错误！第一个是数据的参数从1开始，第二个是Java参数从0开始
                ps.setObject( i+1, args[i]);
            }
            // 4.执行
            /**
             * ps.executeUpdate()
             * 此方法返回值是修改成功的条数;
             * 修改成功多少就会返回多少，没有修改则返回0
             */
            // return ps.executeUpdate();
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            JdbcUtil.closeResource(connection,ps);
        }

        // return 0;
    }

    
    /**
     * @Description 针对于不同表的通用的查询操作，返回表中的一组记录
     * @author oolong
     * @date  10:57
     * @since version-1.0 
     * @return 不同表的集合
     * 连接数据库
     * 预编译sql
     */

    public static <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            // 获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                //任何一个类都要提供一个public的空参构造器，使用newInstance()将这个空参构造器构建出来
                T t = clazz.newInstance();
                // 处理结果集中的一行数据中的每一个列：给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    Object ColumnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给t对象指定的columnName属性，赋值为ColumnValue.:通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    // 保证数据可访问
                    field.setAccessible(true);
                    // 设置字段值。
                    field.set(t, ColumnValue);
                }
                // 将t添加到ArrayList中。
                list.add(t);

            }
            //list可能没有数据，size等于0
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, ps, resultSet);
        }


        return null;

    }

    /**
     * @Description 针对于不同表的通用的查询操作，返回表中的一条记录
     * @author oolong
     * @date  11:07
     * @since version-1.0
     * @return 查询表的类型
     */

    public static  <T> T getInstance(Class<T> clazz,String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            conn = JdbcUtil.getConnection();
            // 预编译sql
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            // 得到结果集
            rs = ps.executeQuery();
            // 获得元数据
            ResultSetMetaData rsdm = rs.getMetaData();

            // 获取字段数目
            int columnCount = rsdm.getColumnCount();
            //创建数据对象
             T t = clazz.newInstance();
            if(rs.next()){

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i+1);

                    // 获取每个列名
                    String columnName = rsdm.getColumnName(i + 1);

                    Field field = clazz.getDeclaredField(columnName);

                    field.setAccessible(true);

                    field.set(t,columnValue);

                }

            }

            return t;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps,rs);
        }

        return  null;
    }
}
