package BFS;
import java.util.*;

// This is the Room class that defines the rooms of the dungeon
public class Room 
{
		// active will be true if the rooms is activated and false if it is inactivated
	 	public boolean active;
	 	// Max no. of doors that the room is connected to
	    public int max_doors;
	    // Doors is an array that checks if a door exists in a certain direction
	    // index 0 is top, index 1 is right, index 2 is bottom and index 3 is left
	    // If a door exists the value at the direction represented by the index,
	    // the value will be 1
	    public int [] Doors = new int [4];
	    // If a door exists in the index of Door_Traversal,
	    // The value at the index will be an Unicode arrow facing the direction of the door
	    public String [] Door_Traversal = new String [4];
	    // Stores the room type
	    public String room_type;
	    // Stores positional information about the room (top left corner, bottom side, middle and etc.)
	    public String room_position;
	    // Stores the column and row index of the room
	    public int col;
	    public int row;
	    // Stores the direction of the door through which the previous room is located
	    public int parent_direction;
	    // Stores the room layout
	    public String room_layout;
	    
	    // Default constructor
	    public Room()
	    {
	        active = false;
	        max_doors =  0;
	        room_type = "0";
	        col = 0;
	        row = 0;
	        Arrays.fill(Doors, 0);
	        Arrays.fill(Door_Traversal, " ");
	        parent_direction = 5;
	        room_layout = "00";
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


