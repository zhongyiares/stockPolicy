package com.thinkgem.jeesite.modules.stock.util;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * Created by zhongyiares on 2017/9/20.
 */
public class MongoDBTest {
    public static void main(String[] args) {
        MongoClientOptions msc = MongoClientOptions.builder().connectionsPerHost(6).connectTimeout(1000).build();

        //MongoClient mongoClient = new MongoClient("192.168.199.240",27017);

        MongoClient mongoClient = new MongoClient("192.168.2.241:27017",msc);

        System.out.println(mongoClient.getMongoClientOptions().getConnectionsPerHost());
        DB db = mongoClient.getDB("mydbs");
        DBCollection users =  db.getCollection("users");



        // method 1
//        BasicDBObject d1 = new BasicDBObject();
//        d1.append("userId","2").append("name","zy2");
       // users.insert(d1);

        //method 2
//        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
//        builder.append("userid","3").add("age",30);
//        users.insert(builder.get());

        //method3
//        String json = "{'userId':'2','sex':'man2'}";
//        DBObject d1 = (DBObject) JSON.parse(json);
//        users.insert(d1);

        //remove
//        users.remove(new BasicDBObject("userId","2"));
//        users.remove(new BasicDBObject());

        //upate
        //users.update(new BasicDBObject("userId","3"),d1);
//        users.update(new BasicDBObject("userId","3"),new BasicDBObject("$set",new BasicDBObject("name","newName")));


        //find
//        DBObject d = users.findOne();
//        System.out.println(d);

//        DBObject con = new BasicDBObject("userId","3");
//        DBCursor c = users.find(con).limit(1);

        DBCursor c = users.find();
        while (c.hasNext()){
            DBObject d = c.next();
            System.out.println(d);
        }

        System.out.println("ok");
    }
}
;