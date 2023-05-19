package DFS;
import java.util.*;

//This is the Room class that defines the rooms of the dungeon
public class Room 
{
		// active will be true if the rooms is activated and false if it is inactivated
	 	public boolean active;
	 	// If a door exists in the index of Door_Traversal,
	    // The value at the index will be an Unicode arrow facing the direction of the door
	 	// index 0 is top, index 1 is right, index 2 is bottom and index 3 is left
	    public String[] Door_Traversal = new String [4];
	    // Stores the room type
	    public String room_type;
	    // Stores the room layout
	    public String room_layout;
	    
	    // Default constructor
	    public Room()
	    {
	        active = false;
	        room_type = "0";
	        Arrays.fill(Door_Traversal, " ");
	        room_layout = "00";
	    }
	    
	    // This function activates the room
	    public void activate()
	    {
	        active = true;
	    }
	    
	    // This function is used to assign the location of the doors for each room
	    public void traversal(int direction)
	    {
	    	if(direction == 1)
	    	{
	    		Door_Traversal[direction - 1] = "↑";
	    	}
	    	else if(direction == 2)
	    	{
	    		Door_Traversal[direction - 1] = "→";
	    	}
	    	else if(direction == 3)
	    	{
	    		Door_Traversal[direction - 1] = "↓";
	    	}
	    	else if(direction == 4)
	    	{
	    		Door_Traversal[direction - 1] = "←";
	    	}
	    }
	    
	    // This function is used to set the room layout
	    // Room layout is determined based on the location of the doors in the room
	    // 15 possible room types
	    public void SetRoomLayout()
	    {
	    	if(Door_Traversal[0] == "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "A1";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "A2";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "A3";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "A4";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "B1";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "B2";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "C1";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "C2";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "C3";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "C4";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] != "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "D1";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] != "←")
	    	{
	    		room_layout = "D2";
	    	}
	    	else if(Door_Traversal[0] != "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "D3";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] != "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "D4";
	    	}
	    	else if(Door_Traversal[0] == "↑" && Door_Traversal[1] == "→" && Door_Traversal[2] == "↓" 
	    			&& Door_Traversal[3] == "←")
	    	{
	    		room_layout = "E";
	    	}
	    } 
	    
}		


