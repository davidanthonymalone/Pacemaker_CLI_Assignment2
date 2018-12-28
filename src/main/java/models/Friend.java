package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.util.UUID;

import com.google.common.base.Objects;

public class Friend implements Serializable {
	  public String id;
	  public String email;
	  public String friendUserID;
	
	  
	  

	  public String getFriendUserID() {
		return friendUserID;
	}




	public void setFriendUserID(String friendUserID) {
		this.friendUserID = friendUserID;
	}




	public Friend() {
	  }




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}


	  public Friend(String email, String id, String friendUserID) {
		  this.id = email;
		    this.email = id;
		    this.friendUserID = friendUserID;
	  }




	@Override
	public int hashCode() {
		return Objects.hashCode(this.id,this.email,this.friendUserID);
	}




	@Override
	public String toString() {
		 return toStringHelper(this)
			        .addValue(email)
			        .toString();
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	  
	  
}
