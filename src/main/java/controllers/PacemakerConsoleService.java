package controllers;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Activity;
import models.Friend;
import models.Location;
import models.User;
import parsers.AsciiTableParser;
import parsers.Parser;
import retrofit2.Call;

public class PacemakerConsoleService {

 private PacemakerAPI paceApi = new PacemakerAPI("https://shrouded-oasis-60771.herokuapp.com/");
 private Parser console = new AsciiTableParser();
 private User loggedInUser = null;

 public PacemakerConsoleService() {}

 // Starter Command

 @Command(description = "Register: Create an account for a new user")
 public void register(@Param(name = "first name") String firstName,
  @Param(name = "last name") String lastName,
  @Param(name = "email") String email, @Param(name = "password") String password) {
  console.renderUser(paceApi.createUser(firstName, lastName, email, password));
 }



 @Command(description = "List Users: List all users emails, first and last names")
 public void getUsers() {
  console.renderUsers(paceApi.getUsers());
 }

 @Command(description = "Login: Log in a registered user in to pacemaker")
 public void login(@Param(name = "email") String email,
  @Param(name = "password") String password) {
  Optional < User > user = Optional.fromNullable(paceApi.getUserByEmail(email));
  if (user.isPresent()) {
   if (user.get().password.equals(password)) {
    loggedInUser = user.get();
    console.println("Logged in " + loggedInUser.email);
    console.println("You are now Logged in");
   } else {
    console.println("Error on login");
   }
  }
 }

 @Command(description = "Logout: Logout current user")
 public void logout() {
  console.println("Logging out " + loggedInUser.email);
  console.println("You have successfully logged out");
  loggedInUser = null;
 }



 @Command(description = "Add activity: create and add an activity for the logged in user")
 public void addActivity(
  @Param(name = "type") String type,
  @Param(name = "location") String location,
  @Param(name = "distance") double distance) {
  Optional < User > user = Optional.fromNullable(loggedInUser);
  if (user.isPresent()) {
   console
    .renderActivity(paceApi.createActivity(user.get().id, type, location, distance));
  }
 }

 @Command(description = "List Activities: List all activities for logged in user")
 public void listActivities() {
  Optional < User > user = Optional.fromNullable(loggedInUser);
  if (user.isPresent()) {
   console
    .renderActivities(paceApi.getActivities(user.get().id));
  }
 }

 // Baseline Commands

 @Command(description = "Add location: Append location to an activity")
 public void addLocation(@Param(name = "activity-id") String id, @Param(name = "longitude") double longitude, @Param(name = "latitude") double latitude) {
  Optional < Activity > activity = Optional.fromNullable(paceApi.getActivity(loggedInUser.getId(), id));
  if (activity.isPresent()) {
   paceApi.addLocation(loggedInUser.getId(), activity.get().id, latitude, longitude);
   console.println("Location Added");
  } else {
   console.println("not found");
  }
 }


 @Command(description = "ActivityReport: List all activities for logged in user, sorted alphabetically by type")
 public void activityReport() {
  Optional < User > user = Optional.fromNullable(loggedInUser);
  if (user.isPresent()) {
   console.renderActivities(paceApi.listActivities(user.get().id, "type"));
  }
 }


 @Command(
  description = "Activity Report: List all activities for logged in user by type. Sorted longest to shortest distance")
 public void activityReport(@Param(name = "byType: type") String type) {
  Optional < User > user = Optional.fromNullable(loggedInUser);
  if (user.isPresent()) {
   List < Activity > reportActivities = new ArrayList < > ();
   Collection < Activity > usersActivities = paceApi.getActivities(user.get().id);
   usersActivities.forEach(a -> {
    if (a.type.equals(type))
     reportActivities.add(a);
   });
   reportActivities.sort((a1, a2) -> {
    if (a1.distance >= a2.distance)
     return -1;
    else
     return 1;
   });
   console.renderActivities(reportActivities);
  }
 }

 @Command(description = "List all locations for a specific activity")
 public void listActivityLocations(@Param(name = "activity-id") String id) {
  Optional < User > user = Optional.fromNullable(loggedInUser);
  Optional < List < Location >> location = Optional.fromNullable(paceApi.getLocations(user.get().id, id));
  if (location.isPresent()) {
   console.renderLocations(location);
   //  console.renderLocations(activity.getLocation());

  }
 }

 @Command(description = "Follow Friend: Follow a specific friend")
 public void follow(@Param(name = "email") String email) {

  Optional < User > lUser = Optional.fromNullable(loggedInUser);
  Optional < User > user = Optional.fromNullable(paceApi.getUserByEmail(email));

  List < Friend > friends = (List < Friend > ) paceApi.getFriends(lUser.get().id);



  if (friends.isEmpty()) {
   paceApi.addFriend(loggedInUser.getId(), user.get().email, user.get().id);
   console.println("Contgrats you followed your first friend");


  } else {
   for (Friend friend: friends) {

    if (friend.getEmail().equals(email)) {
     console.println("friend already followed");
    } else {


     console.println("You have followed " + user.get().email);

    }

   }

  }




 }

 @Command(description = "List Friends: List all of the friends of the logged in user")
 public void listFriends() {
  Optional < User > user = Optional.fromNullable(loggedInUser);

  if (user.isPresent()) {

   console.renderFriends(paceApi.getFriends(user.get().id));
  } else {
   console.println("User does not exist");


  }
 }

 @Command(description = "Friend Activity Report: List all activities of specific friend, sorted alphabetically by type)")
 public void friendActivityReport(@Param(name = "email") String email) {
  Optional < User > lUser = Optional.fromNullable(loggedInUser);
  Optional < User > user = Optional.fromNullable(paceApi.getUserByEmail(email));

  List < Friend > friends = (List < Friend > ) paceApi.getFriends(lUser.get().id);
  Friend reportFriend = new Friend();
  List < Friend > followedfriends = new ArrayList < Friend > ();
  for (Friend friend: friends) {
   if (friend.getEmail().equals(email)) {
    reportFriend = friend;
    Optional < User > user2 = Optional.fromNullable(paceApi.getUser(reportFriend.getFriendUserID()));
    List < Activity > activities = (List < Activity > ) paceApi.getActivities(user2.get().id);

    console.renderActivities(paceApi.getActivities(user2.get().id));
   }





  }
 }





 // Good Commands

 @Command(description = "Unfollow Friends: Stop following a friend")
 public void unfollowFriend() {}




 @Command(description = "Message Friend: send a message to a friend")
 public void messageFriend(@Param(name = "email") String email,
  @Param(name = "message") String message) {}

 @Command(description = "List Messages: List all messages for the logged in user")
 public void listMessages() {}

 @Command(description = "Distance Leader Board: list summary distances of all friends, sorted longest to shortest")
 public void distanceLeaderBoard() {}

 // Excellent Commands

 @Command(description = "Distance Leader Board: distance leader board refined by type")
 public void distanceLeaderBoardByType(@Param(name = "byType: type") String type) {}

 @Command(description = "Message All Friends: send a message to all friends")
 public void messageAllFriends(@Param(name = "message") String message) {}

 @Command(description = "Location Leader Board: list sorted summary distances of all friends in named location")
 public void locationLeaderBoard(@Param(name = "location") String message) {}



 // Outstanding Commands

 // Todo
}