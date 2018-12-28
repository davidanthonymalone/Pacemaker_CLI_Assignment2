package parsers;

import java.util.Collection;
import java.util.List;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.spec.IASCIITableAware;

import models.Activity;
import models.Friend;
import models.Location;
import models.User;

public class Parser {

  public void println(String s) {
    System.out.println(s);
  }

  public void renderUser(User user) {
    System.out.println(user.toString());
  }

  public void renderUsers(Collection<User> users) {
    System.out.println(users.toString());
  }

  public void renderActivity(Activity activities) {
    System.out.println(activities.toString());
  }

  public void renderActivities(Collection<Activity> activities) {
    System.out.println(activities.toString());
  }

  public void renderFriends(Collection<Friend> friends) {
	  
	      
	    System.out.println(friends.toString());
	  }
  
  public void renderLocations(Activity a) {
	  System.out.println(a.toString());
  }
  public void renderFriends(Friend friend) {
	    System.out.println(friend.getEmail());
	  }
}