package BFS;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// Dungeon class
// This is the core class that contains the breadth-first algorithm
public class Dungeon 
{
	// curse_count is used to keep track of how many rooms
	// have not been assigned the cursed type
	public static int curse_count = 0;
	
	// door_count is used to keep track of the number
	// doors a room has
	public static int door_count = 0;
	
	// Main class that handles the dungeon generation
	public static void main(String[] args)
    {
		// The room_gen variable decides how many rooms are generated
		// Change this variable as required
		int room_gen = 5;
		
		// The col and row variables decide the dimension of the Dungeon
		// These values can also be manually tuned however the current setting is optimal
		int col = (int)(Math.sqrt(room_gen * 2.5));
		int row = (int)(Math.sqrt(room_gen * 2.5));
		
		// Queue used for breadth first algorithm
		Queue<Room> order = new LinkedList<Room>();
		
		// Grid array is used to store the location of the generated rooms
		String Grid[][]= new String[row][col];
		
		// Room_Track array is used to store the order in which the rooms were generated
		int Room_Track[][] = new int[row][col];
		
		// Dungeon array that stores the room objects of the dungeons
		Room[][] Dungeon = new Room[row][col]; 
		
		// Random number generator
		Random r = new Random();
		
		// Room counter that keeps track of the number of rooms generated
		int room_count = 1;
		
		// The starting column and row are randomly selected from the dungeon array
		int start_col = r.nextInt(col);
	    int start_row = r.nextInt(row);
	    
	    // The current column and row will be equal to the starting column and row at the start
	    int current_col = start_col;
	    int current_row = start_row;
	
	    // This loop sets each index of the Grid array to 0
		for(int i = 0; i < Grid.length; i++)
		{
	    	Arrays.fill(Grid[i], "0");
	    	Arrays.fill(Room_Track[i], 0);
		}
		
		// This loop initializes a room object in each index 
		for (int i = 0; i < row; i++)
		{
	    	for (int j = 0; j < col; j++)
	    	{
	    		Room room = new Room();
	    		Corner(col, row, j, i, room);
	    		Dungeon[i][j] = room;
	    	}
		}
		
		// Activating the starting room that was randomly selected
	    Dungeon[start_row][start_col].active = true;
	    
	    // Updating the arrays that store information about the location and room generation order
	    Room_Track[start_row][start_col] = 1;
	    Dungeon[start_row][start_col].room_type = "S";
	    
	    // Adding the starting room to the queue
	    order.add(Dungeon[start_row][start_col]);
	    
	    // Creating a temporary variable to store room objects
	    Room temp;
	    
	    // prev_room is used to keep track of the room type of the previous room
	    String prev_room = "";
	    
	    // The dungeon generating algorithm starts here and loops until the desired number of rooms have been generated
	    while(room_count < room_gen)
	    {
	    	// Dequeued room object is stored in the temporary variable
	    	temp = order.remove();
	    	
	    	// Room type of dequeued room object is stored
	    	prev_room = temp.room_type;
	    	
	    	// Current column and row is updated to the location of the dequeued room
	    	current_col = temp.col;
	    	current_row = temp.row;
	    	
	    	// If the room is the starting room, Grid array will show that it is the first room that is generated
	    	if(temp.col == start_col && temp.row == start_row)
	    	{
	    		Grid[current_row][current_col] = "1";
	    	}
	    	 
	    	// Checks if a room is connected above the current room
	    	if(temp.Doors[0] == 1 && room_count < room_gen)
	    	{
	    		// if a room exists, it will move the current row and column to that room
	    		current_row--;
	    		
	    		// Checks if the room is not activated
	    		if(Dungeon[current_row][current_col].active == false)
	    		{
	    			// If it is not activated, the room will be activated and added to the queue,
	    			// and the corresponding information about the room will be updated.
	    			// Total room count is also updated
	    			order.add(Dungeon[current_row][current_col]);
	    			Dungeon[current_row][current_col].active = true;
	    			Dungeon[current_row][current_col].parent_direction = 2;
	    			room_count++;
	    			Room_Track[current_row][current_col] = room_count;
	    			Grid[current_row][current_col] = Integer.toString(room_count);
	    			roomType(Dungeon[current_row][current_col], prev_room);
	    			prev_room = Dungeon[current_row][current_col].room_type;
	    			temp.Door_Traversal[0] = "↑";
	    		}
	    		// Return back to the room that was dequeued at the start of the room
	    		current_row++;
	    	}
	    	
	    	// Checks if a room is connected to the right of the current room
	    	if(temp.Doors[1] == 1 && room_count < room_gen)
	    	{
	    		// This if statement functions similarly to the if statement above
	    		current_col++;
	    		if(Dungeon[current_row][current_col].active == false)
	    		{
	    			order.add(Dungeon[current_row][current_col]);
	    			Dungeon[current_row][current_col].active = true;
	    			Dungeon[current_row][current_col].parent_direction = 3;
	    			room_count++;
	    			Room_Track[current_row][current_col] = room_count;
	    			Grid[current_row][current_col] = Integer.toString(room_count);
	    			roomType(Dungeon[current_row][current_col], prev_room);
	    			prev_room = Dungeon[current_row][current_col].room_type;
	    			temp.Door_Traversal[1] = "→";
	    		}
	    		// Return back to the room that was dequeued at the start of the room
	    		current_col--;
	    	}
	    	
	    	// Checks if a room is connected to the bottom of the current room
	    	if(temp.Doors[2] == 1 && room_count < room_gen)
	    	{
	    		// This if statement functions similarly to the if statement above
	    		current_row++;
	    		if(Dungeon[current_row][current_col].active == false)
	    		{
	    			order.add(Dungeon[current_row][current_col]);
	    			Dungeon[current_row][current_col].active = true;
	    			Dungeon[current_row][current_col].parent_direction = 0;
	    			room_count++;
	    			Room_Track[current_row][current_col] = room_count;
	    			Grid[current_row][current_col] = Integer.toString(room_count);
	    			roomType(Dungeon[current_row][current_col], prev_room);
	    			prev_room = Dungeon[current_row][current_col].room_type;
	    			temp.Door_Traversal[2] = "↓";
	    		}
	    		// Return back to the room that was dequeued at the start of the room
	    		current_row--;
	
	    	}
	    	
	    	// Checks if a room is connected to the left of the current room
	    	if(temp.Doors[3] == 1 && room_count < room_gen)
	    	{
	    		// This if statement functions similarly to the if statement above
	    		current_col--;
	    		if(Dungeon[current_row][current_col].active == false)
	    		{
	    			order.add(Dungeon[current_row][current_col]);
	    			Dungeon[current_row][current_col].active = true;
	    			Dungeon[current_row][current_col].parent_direction = 1;
	    			room_count++;
	    			Room_Track[current_row][current_col] = room_count;
	    			Grid[current_row][current_col] = Integer.toString(room_count);
	    			roomType(Dungeon[current_row][current_col], prev_room);
	    			prev_room = Dungeon[current_row][current_col].room_type;
	    			temp.Door_Traversal[3] = "←";
	    		}
	    		// Return back to the room that was dequeued at the start of the room
	    		current_col++;	
	    	}
	    }
	    
	    // This loop is used to assign the location of the doors for each room
	    for (int i= 0; i < row; i++)
	    {
			for (int j= 0; j < col; j++)
			{
				if(Dungeon[i][j].active == true)
				{
					if(Dungeon[i][j].parent_direction == 0)
					{
						Dungeon[i][j].Door_Traversal[0] = "↑";
					}
					else if(Dungeon[i][j].parent_direction == 1)
					{
						Dungeon[i][j].Door_Traversal[1] = "→";
					}
					else if(Dungeon[i][j].parent_direction == 2)
					{
						Dungeon[i][j].Door_Traversal[2] = "↓";
					}
					else if(Dungeon[i][j].parent_direction == 3)
					{
						Dungeon[i][j].Door_Traversal[3] = "←";
					}
				}
			}
	    } 
	    
	    // This loop is used to give the final generated room the "final" room type
	    for (int i= 0; i < row; i++)
	    {
			for (int j= 0; j < col; j++)
			{
				if(Room_Track[i][j] == room_gen)
				{
					Dungeon[i][j].room_type = "F";
				}
				Dungeon[i][j].SetRoomLayout();
			}
	    } 
	    
	    // printDungeon function is called to create the 2D representation of the dungeon 
	    printDungeon(Dungeon, Grid, col, row);
	    
	    // printTxt function is called to create the text file which will be given to the Unity generator.
	    try {
			printTxt(Dungeon, Grid, col, row);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	// This function checks if a room on the middle, sides or corners of the dungeon array
	// This is based on the concept seen in figure 7.2 of the IS pdf
	// Requires the room object and the position of the room object to compute
	public static void Corner(int col, int row, int current_col, int current_row, Room room)
	{

		Random r = new Random();
		int chosen_direction = 0;
		
		if(current_col < (col - 1) && current_col != 0 && current_row < (row - 1) && current_row != 0)
		{
			room.room_position = "Middle";
			door_count = r.nextInt(3) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0, 1, 2, 3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(4)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}	
		}
		
		else if(current_row == 0  && current_col == 0)
		{
			room.room_position = "Top Left Corner";
			door_count = r.nextInt(1) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {1, 2};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(2)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		
		else if(current_row == 0 && current_col == (col-1))
		{
			room.room_position = "Top Right Corner";
			door_count = r.nextInt(1) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {2, 3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(2)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_row == (row-1) && current_col == 0)
		{
			room.room_position = "Bottom Left corner";
			door_count = r.nextInt(1) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0, 1};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(2)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_row == (row-1) && current_col == (col-1))
		{
			room.room_position = "Bottom Right corner";
			door_count = r.nextInt(1) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0, 3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(2)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_row == 0 && current_col < (col - 1) && current_col > 0)
		{
			room.room_position = "Top Side";
			door_count = r.nextInt(2) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {1,2,3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(3)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_row == (row - 1) && current_col < (col - 1) && current_col > 0)
		{
			room.room_position = "Bottom Side";
			door_count = r.nextInt(2) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0,1,3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(3)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_col == 0 && current_row < (row - 1) && current_row > 0)
		{
			room.room_position = "Left Side";
			door_count = r.nextInt(2) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0,1,2};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(3)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
		else if(current_col == (col - 1) && current_row < (row - 1) && current_row > 0)
		{
			room.room_position = "Right Side";
			door_count = r.nextInt(2) + 2;
			room.max_doors = door_count;
			room.col = current_col;
			room.row = current_row;
			int[] choices = {0,2,3};
			for(int i = 0; i < door_count;)
			{
				chosen_direction = choices[r.nextInt(3)];
				if(room.Doors[chosen_direction] != 1)
				{
					room.Doors[chosen_direction] = 1;
					i++;
				}
			}
		}
	}
	
	// This function is used to create the 2D representation of the dungeon
	// Requires most of the arrays that provide information about the dungeon to create the representation
	public static void printDungeon(Room[][] Dungeon, String[][] Grid, int col, int row)
	{
		
		for (int i= 0; i < row; i++)
	    {
	    	for (int j= 0; j < col; j++)
	    	{
	    		System.out.print(" " + Dungeon[i][j].Door_Traversal[0] + " ");
	        }
	        System.out.print("\n");
	        for (int j= 0; j < col; j++)
	    	{
	    		System.out.print(Dungeon[i][j].Door_Traversal[3] + Dungeon[i][j].room_type + Dungeon[i][j].Door_Traversal[1]);
	        }
	        System.out.print("\n");
	        for (int j= 0; j < col; j++)
	    	{
	    		System.out.print(" " + Dungeon[i][j].Door_Traversal[2] + " ");
	        }
	        System.out.print("\n");
	    }  
	}
	
	
	// This function is used to assign the room type for each room
	// To assign the room type, a room object will need to be passed, 
	// alongside the room type of the previous room that was generated
	// Room type for the current room is assigned based on the room type of the previous room
	public static void roomType(Room room, String prev_room)
	{
		if(curse_count == 5)
		{
			room.room_type = "C";
			curse_count = 0;
		}
		else if(prev_room == "S")
		{
			room.room_type = "T";
			curse_count++;
		}
		else if(prev_room == "T" && curse_count != 5)
		{
			room.room_type = "E";
			curse_count++;
		}
		else if(prev_room == "E" && curse_count != 5)
		{
			room.room_type = "N";
			curse_count++;
		}
		else if(prev_room == "N" && curse_count != 5)
		{
			room.room_type = "T";
			curse_count++;
		}
		else if(prev_room == "C")
		{
			room.room_type = "T";
			curse_count++;
		}
	}
	
	// This function will be used to
	// Requires all the arrays that provide information about the dungeon
	// Throws an IOException if it fails to make the file
	public static void printTxt(Room[][] Dungeon, String[][] Grid, int col, int row) throws IOException
	{
		PrintWriter out = new PrintWriter("Dungeon.txt");
		
		out.println(col + " " + row);
		
		// First the Grid array containing information about the position of the rooms is printed
		for (int i= 0; i < row; i++) //loops through rows
	    {
			for (int j= 0; j < col; j++) //loops through columns
			{
				out.print(Grid[i][j]+" ");
			}
			out.print("\n");
	    }
		
	    // Next the array containing information about the room layout is printed
	    for (int i = 0; i < row; i++) //loops through rows
        {
    		for (int j= 0; j < col; j++) //loops through columns
    		{
    			out.print(Dungeon[i][j].room_layout + " ");
    		}
    		out.print("\n");
        }
	    
	    // Finally, information about the room type is printed
	    for (int i = 0; i < row; i++) //loops through rows
        {
    		for (int j= 0; j < col; j++) //loops through columns
    		{
    			out.print(Dungeon[i][j].room_type + " ");
    		}
    		out.print("\n");
        }
	    
	    out.close();
	}
}
	
