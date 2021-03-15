//package com.smart.demo.service;
//
//import com.smart.demo.dao.IMongoUserDao;
//import com.smart.demo.dao.IMongoUserDao2;
//import com.smart.demo.mode.User;
//import com.smart.demo.mode.User2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService implements IUserService {
//    @Autowired
//    IMongoUserDao userDao;
//
//    @Autowired
//    IMongoUserDao2 userDao2;
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//
//    @Override
//    public boolean saveUser(User user) {
//        try {
//            Object o = userDao.save(user);
//            System.out.println(o);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean saveUser(User2 user) {
//        User2 user1 = userDao2.save(user);
//        System.out.println(user1);
//        return true;
//    }
//
//    @Override
//    public User findUserById(Long id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//        List<User> list = mongoTemplate.find(query, User.class);
//        if (!list.isEmpty()) return list.get(0);
//
//        throw new RuntimeException("获取失败");
//
//    }
//}
