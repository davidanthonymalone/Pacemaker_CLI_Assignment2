package controllers;
import static models.Fixtures.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static models.FixturesTest.users2;
import static models.FixturesTest.activities2;




import java.io.File;
import java.util.List;

import controllers.PacemakerAPI;
import models.Activity;
import models.Location;
import models.User;
import retrofit2.Call;

import static models.FixturesTest.activities2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PacemakerAPITest {
	private PacemakerAPI pacemaker;
	

	
	

//	@Test 
//	public void testGetActivities() {
//		User newUser = pacemaker.createUser("testUser", "testUSerSurname", "david@gmail.com", "secret");
//		 pacemaker.createActivity(newUser.getId(), "Walk", "Waterford", 5);
//		List <Activity> activities = (List<Activity>) pacemaker.getActivities(newUser.getId());
//		assertNotNull(activities);
//		
//
//	}
	
	@Test
	public void testEquals() {
		User homer = new User("homer", "simpson", "homer@simpson.com", "secret");
		User homer2 = new User("homer", "simpson", "homer@simpson.com", "secret");
		User bart = new User("bart", "simpson", "bartr@simpson.com", "secret");

		assertEquals(homer, homer);
		assertEquals(homer, homer2);
		assertNotEquals(homer, bart);
	}
////	
//	@Test
//	public void testAddActivity() {
//		pacemaker.createUser("Marge", "Simpson", "marge@simpson.com", "secret");
//		var marge = pacemaker.getUserByEmail("marge@simpson.com");
//		var activity = pacemaker.createActivity(marge.id, activities[0].type, activities[0].location,
//				activities[0].distance);
//		var returnedActivity = pacemaker.getActivity(marge.id,activity.id);
//		assertNotSame(activities[0], returnedActivity);
//	}
	
	

	}


