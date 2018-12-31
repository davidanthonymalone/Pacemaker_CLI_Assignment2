package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fixtures {

  public static List<User> users =
      new ArrayList<>(Arrays.asList(new User("marge", "simpson", "marge@simpson.com", "secret"),
          new User("lisa", "simpson", "lisa@simpson.com", "secret"),
          new User("David", "Malone", "david@gmail.com", "secret"),
          new User("bart", "simpson", "bart@simpson.com", "secret"),
          new User("maggie", "simpson", "maggie@simpson.com", "secret")));
  
  
  public static User[] users2 =
	  {
	    new User ("marge", "simpson", "marge@simpson.com",  "secret"),
	    new User ("lisa",  "simpson", "lisa@simpson.com",   "secret"),
	    new User ("bart",  "simpson", "bart@simpson.com",   "secret"),
	    new User ("maggie","simpson", "maggie@simpson.com", "secret")
	  };
  
  public static Activity[] activities =
	  {
	    new Activity ("walk",  "fridge", 0.001),
	    new Activity ("walk",  "bar",    1.0),
	    new Activity ("run",   "work",   2.2),
	    new Activity ("walk",  "shop",   2.5),
	    new Activity ("cycle", "school", 4.5)
	  };
}