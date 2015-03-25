package com.learn.csd;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBObject;

@RestController
public class MainController {
	
	private MongoDAOImpl mongoDaoImpl = MongoDAOImpl.getInstance();

	@RequestMapping(value="/findByCourse",method = RequestMethod.GET)
    public ServerResponse findByCourse(@RequestParam("courseName") String courseName) {
		ServerResponse res = new ServerResponse();
		res.setId("1");
		DBObject dbo = mongoDaoImpl.findByCourse(courseName);
		if(dbo!=null){
			res.setContent(dbo.get("Full Name").toString());
		}
        return res;
	}
	
	@RequestMapping(value="/findByLocation",method = RequestMethod.GET)
    public ServerResponse findByLocation(@RequestParam("location") String location) {
		ServerResponse res = new ServerResponse();
		res.setId("1");
		DBObject dbo = mongoDaoImpl.findByLocation(location);
		if(dbo!=null){
			res.setContent(dbo.get("Full Name").toString());
		}
        return res;
	}
	
}
