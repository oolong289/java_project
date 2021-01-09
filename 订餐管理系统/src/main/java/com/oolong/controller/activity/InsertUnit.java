package com.oolong.controller.activity;

import com.oolong.view.utils.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * @author oolong
 */
public class InsertUnit {
@Test
    public void insert(){
        // 插入10000数据...
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JdbcUtil.getConnection();
            // 设置不允许自动提交数据
            conn.setAutoCommit(false);
            String string[] = {"1","2","3","4","5","6","7","8","9","0"};
            Random random = new Random(System.currentTimeMillis());
            String accountNum = null;
            String passwordNum = null;
            String encryptedNum = null;;

            String sql = "insert into account(accountNum,password,encrypted) Values(?,?,?)";
            ps = conn.prepareStatement(sql);
            int count = 10000;

            for (int i = 1; i <= count; i++) {
                 accountNum = string[Math.abs(random.nextInt())%9];
                 passwordNum = string[Math.abs(random.nextInt())%9];
                 encryptedNum = string[Math.abs(random.nextInt())%9];;
                for (int j = 0; j < 1; j++) {
                    accountNum = accountNum + string[Math.abs(random.nextInt())%10] + ((i+7) %100)+ ((i+7) %5) +i;
                    passwordNum = passwordNum + string[Math.abs(random.nextInt())%10] + ((i+8) %100)+ ((i+7) %6)+i;
                    encryptedNum = encryptedNum + string[Math.abs(random.nextInt())%10] + ((i+9) %100)+ ((i+7) %4)+i;
                }
                System.out.println(accountNum);
                System.out.println(passwordNum);
                System.out.println(encryptedNum);
                // 插入的数据
                ps.setObject(1,accountNum);
                ps.setObject(2,passwordNum);
                ps.setObject(3,encryptedNum);

                // 1."攒"sql
                ps.addBatch();
                if(i % 500 == 0){
                    // 2.执行Batch
                    ps.executeBatch();

                    // 3.清空Batch
                    ps.clearBatch();
                }

                accountNum = "";
                passwordNum = "";
                encryptedNum = "";

            }

            // 提交数据
            conn.commit();

            long end = System.currentTimeMillis();

            System.out.println("花费的时间" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }
    }


    @Test
    public void insertDish(){
        // 插入10000数据...
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JdbcUtil.getConnection();
            // 设置不允许自动提交数据
            conn.setAutoCommit(false);
            String string[] = {"1","2","3","4","5","6","7","8","9","0"};
            Random random = new Random(System.currentTimeMillis());
            String accountNum = null;
            String passwordNum = null;
            String encryptedNum = null;;

            String sql = "insert into dish(price,`name`,category) Values(?,?,?)";
            ps = conn.prepareStatement(sql);
            int count = 10000;

            for (int i = 1; i <= count; i++) {


                // 插入的数据
                ps.setObject(1,accountNum);
                ps.setObject(2,passwordNum);
                ps.setObject(3,encryptedNum);

                // 1."攒"sql
                ps.addBatch();
                if(i % 500 == 0){
                    // 2.执行Batch
                    ps.executeBatch();

                    // 3.清空Batch
                    ps.clearBatch();
                }

                accountNum = "";
                passwordNum = "";
                encryptedNum = "";

            }

            // 提交数据
            conn.commit();

            long end = System.currentTimeMillis();

            System.out.println("花费的时间" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }
    }

    /**
     * 批量插入方式四：设置连接不允许自动提交数据
     */
    @Test
    public void insert3Test(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JdbcUtil.getConnection();
            // 设置不允许自动提交数据
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) Values(?)";
            ps = conn.prepareStatement(sql);
            int count = 10000;
            for (int i = 1; i <= count; i++) {
                ps.setObject(1,"name_" + i );
                // 1."攒"sql
                ps.addBatch();
                if(i % 500 == 0){
                    // 2.执行Batch
                    ps.executeBatch();

                    // 3.清空Batch
                    ps.clearBatch();
                }


            }

            // 提交数据
            conn.commit();

            long end = System.currentTimeMillis();
            //花费的时间：20000：172723 - 2511
            //         1000000:35925 - 16488
            System.out.println("花费的时间" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }
    }
}

