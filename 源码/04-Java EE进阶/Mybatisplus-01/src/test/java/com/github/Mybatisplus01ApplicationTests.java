package com.github;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.mapper.UserMapper;
import com.github.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatisplus01ApplicationTests {

    //  继承了 BaseMapper ，所有的方法都来自己父类
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 参数是一个wrapper，条件是构造器，先使用null
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void contextLoads2() {
        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println); // 与map对比一下
    }

    @Test
    void test2(){
        // 查询名字Jack
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jack");
        User user = userMapper.selectOne(wrapper); // 查询一个数据，出现多个结果使用List或者Map
        System.out.println(user);
    }

    @Test
    void test3(){
        // 查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30); // 区间
        Integer count = userMapper.selectCount(wrapper); // 查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4(){
        // 查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右 t%
        wrapper
                .notLike("name","e")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testWrapper5() {
        //模糊查询
        // SELECT id,name,age,email,version,deleted,create_time,update_time
        // FROM user
        // WHERE deleted=0 AND id IN
        // (select id from user where id<5)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id","select id from user where id<5");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    public void testWrapper6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行降序排序
        wrapper.orderByDesc("id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    // 基本删除操作
    @Test
    public void DeleteTest(){
        userMapper.deleteById(14993819200201L);
    }

    // 通过id批量删除
    @Test
    public void DeleteByIdTest(){
        userMapper.deleteBatchIds(Arrays.asList(14993819200199L,6));
    }

    // 通过Map删除
    @Test
    public void DeleteMapTest(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","KYDH,开源导航");
        userMapper.deleteByMap(map);
    }

    // 更新操作
    @Test
    public void UpdateTest(){
        User user = new User();
        // 通过条件自动拼接SQL
        user.setId(7L);
        user.setName("KYDH,开源导航");
        user.setAge(26);
        // 注意：updateById的参数是一个对象！
        Integer i = userMapper.updateById(user);
        System.out.println(i);
    }

    // 测试乐观锁
    @Test
    public void testOptimisticLocker(){
        // 1.查询用户信息
        User user = userMapper.selectById(1L);
        // 2.修改用户信息
        user.setName("金顶");
        user.setEmail("2451367@qq.com");
        // 3.执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁失败！多线程下
    @Test
    public void testOptimisticLocker2(){
        // 线程 1
        User user = userMapper.selectById(1L);
        user.setName("夸父1");
        user.setEmail("2451367@qq.com");
        // 模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("夸父2");
        user2.setEmail("2451367@qq.com");
        userMapper.updateById(user2);
        // 自旋锁来多次尝试提交！
        userMapper.updateById(user); // 如果没有乐观锁就会覆盖插队线程的值！
    }

    // 查询测试
    @Test
    public void SelectByIdTest(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 批量查询
    @Test
    public void SelectByBatchIdTest(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 按条件查询之一使用map操作
    @Test
    public void SelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义查询
        map.put("name","哇哈哈");
        map.put("age",22);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 测试插入
    @Test
    public void insertTest(){
        User user = new User();
        user.setName("哇哈哈");
        user.setAge(22);
        user.setEmail("2589654784@qq.com");
        // 自动生成id
        Integer insert = userMapper.insert(user);
        // 受影响的行数
        System.out.println(insert);
        // 通过日志发现id会自动回填
        System.out.println(user);
    }

    // 分页查询
    @Test
    public void testPage(){
        // 参数一 current：当前页
        // 参数二 size：页面大小
        // 使用了分页插件之后，所有的分页操作都变得简单了
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println("总页数==>"+page.getTotal());
    }

}
