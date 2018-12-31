package models;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.PacemakerAPI;
import models.User;
import static models.Fixtures.users;

public class UserTest {

  PacemakerAPI pacemaker = new PacemakerAPI("https://shrouded-oasis-60771.herokuapp.com/");
  User homer = new User("homer", "simpson", "homer@simpson.com", "secret");

  @Before
  public void setup() {
    pacemaker.deleteUsers();
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testCreateUser() {
    User user = pacemaker.createUser(homer.firstname, homer.lastname, homer.email, homer.password);
    assertEquals(user, homer);
    User user2 = pacemaker.getUserByEmail(homer.email);
    assertEquals(user2, homer);
  }

  @Test
  public void testCreateUsers() {
    users.forEach(
        user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
    Collection<User> returnedUsers = pacemaker.getUsers();
    assertEquals(users.size(), returnedUsers.size());
  }
  
  @Test
  public void testGetUser() {
    
    User returnedUser1 = pacemaker.createUser("David","Malone","davidtest@gmail.com","secret");
    User returnedUser2 = pacemaker.getUserByEmail("davidtest@gmail.com");
    assertEquals(returnedUser1, returnedUser2);
    User returnedUser3 = pacemaker.getUser(returnedUser1.getId());
    assertEquals(returnedUser1, returnedUser3);
  
}
  

  @Test
  public void testCreateUserWithActivity() {
    pacemaker.deleteActivities(homer.id);
    pacemaker.deleteUsers();
    User user = new User("Test", "Activity", "test@gmail.com","secret");
    Activity activity = new Activity("Walk","Waterford",3);

    User returnedUser = pacemaker.createUser(user.getFirstname(),user.getLastname(),user.getEmail(),"secret");
    pacemaker.createActivity(returnedUser.getId(),activity.getType(),activity.getLocation(),3);

    Collection<Activity> activities = pacemaker.getActivities(returnedUser.getId());
    assertEquals (activities.size(), 1);
    //assertEquals (activities.get(0), location);
  }


}