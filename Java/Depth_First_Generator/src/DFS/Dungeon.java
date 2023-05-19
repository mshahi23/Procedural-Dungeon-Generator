package DFS;
import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;

// Dungeon class
// This is the core class that contains the depth-first algorithm
public class Dungeon 
{
	// curse_count is used to keep track of how many rooms
	// have not been assigned the cursed type
	public static int curse_count = 0;
	
	// Main class that handles the dungeon generation
	public static void main(String[] args)
    {
		// The room_gen variable decides how many rooms are generated
		// Change this variable as required
		int room_gen = 5;
		
		// The col and row variables decide the dimension of the Dungeon
		// These values can also be manually tuned however the current setting is optimal
		int col = (int)Math.sqrt(room_gen) + 1;
		int row = (int)Math.sqrt(room_gen) + 1;
		
		// Stack used for breadth first algorithm
		Stack<Integer> undo = new Stack<Integer>();
		
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
	    
	    // Boolean variable used to check if the algorithm is in backtracking state
	    // False by default (not in backtracking state)
	    boolean backtracking = false;
	    
	    // direction stores the direction which is randomly chosen by the algorithm
	    int direction = 0;
	    
	    // prev_direction stores the direction used in the previous iteration
	    int prev_direction = 0;
	    String prev_room = "";
		
		// This loop initializes a room object in each index 
		for (int i = 0; i < row; i++) //loops through rows
		{
	    	for (int j = 0; j < col; j++) //loops through columns
	    	{
	    		Room room = new Room();
	    		Dungeon[i][j] = room;
	    	}
		}
		
		// Activating the starting room that was randomly selected
	    Dungeon[start_row][start_col].active = true;
	    
	    // Updating the arrays that store information about the location and room generation order
	    Room_Track[start_row][start_col] = 1;
	    
	    // Assigning the starting room type to the starting room
	    Dungeon[start_row][start_col].room_type = "S";
	    
	    // The dungeon generating algorithm starts here and loops until the desired number of rooms have been generated
	    while(room_count < room_gen)
	    {
	    	// Stores the room type of the current room
	    	prev_room = Dungeon[current_row][current_col].room_type;
	    	
	    	// This if statement runs the stuck function to check if the algorithm has become stuck
	    	// If it becomes stuck it will update the backtracking boolean and enter the backtracking state
	        if(stuck(Dungeon, col, row, current_col, current_row))
	        {
	        	backtracking = true;
	        }
	        
	        // If the algorithm is not in the backtracking state it will resume its normal function
	        if(backtracking != true)
	        {
	        	// A direction is randomly chosen to create a new room
		        direction = r.nextInt(4) + 1;
		        
		        // If direction is 0, a room will be generated above
		        if(direction == 1)
		        {
		        	// Checking for edge cases based on fig 7.2 of the IS
		        	if(current_row > 0)
		        	{
		        		// If true, the current location will be moved to the room above
		        		// It will not check if the room in this location has been activated or not
		        		current_row--;
		        	    if(Dungeon[current_row][current_col].active == false)
			            {
		        	    	// If the room has not been activated, the room will be activated
		        	    	// The corresponding information about the room will be updated
		        	    	// The direction used to make this room will be stored in the stack
		        	    	// Room count will be incremented
			                Dungeon[current_row][current_col].activate();
			                Dungeon[current_row][current_col].traversal(3);
			                Dungeon[current_row + 1][current_col].traversal(direction);
			                room_count++;
			                Room_Track[current_row][current_col] = room_count;
			                roomType(Dungeon[current_row][current_col], prev_room);
			                undo.push(direction);
			            }
		        	    else
		        	    {
		        	    	current_row++;
		        	    	if(current_row > (row -1))
		        	    	{
		        	    		current_row = row - 1;
		        	    	}
		        	    }
		        	}
		        }
		        
		        // If direction is 1, a room will be generated to the right of the current room
		        else if(direction == 2)
		        {
		        	// Checking for edge cases based on fig 7.2 of the IS
		        	if(current_col < col-1)
		        	{
		        		// The rest of the code is similar and it will activate the room if it has not been activated
		        		current_col++;
		        		if(Dungeon[current_row][current_col].active == false)
			            {
			                Dungeon[current_row][current_col].activate();
			                Dungeon[current_row][current_col].traversal(4);
			                Dungeon[current_row][current_col - 1].traversal(direction);
			                room_count++;
			                Room_Track[current_row][current_col] = room_count;
			                roomType(Dungeon[current_row][current_col], prev_room);
			                undo.push(direction);
			            }
		        		else
			        	{
		        	    	current_col--;
		        	      	if(current_col < 0)
		        	    	{
		        	    		current_col = 0;
		        	    	}
		        	    }
		        	}
		        }
		        
		        // If direction is 3, a room will be generated to the bottom of the current room
		        else if(direction == 3)
		        {
		        	// Checking for edge cases based on fig 7.2 of the IS
		        	if(current_row < col -1)
		        	{
		        		// The rest of the code is similar and it will activate the room if it has not been activated
		        		current_row++;
		        		if(Dungeon[current_row][current_col].active == false)
			            {
			                Dungeon[current_row][current_col].activate();
			                Dungeon[current_row][current_col].traversal(1);
			                Dungeon[current_row - 1][current_col].traversal(direction);
			                room_count++;
			                Room_Track[current_row][current_col] = room_count;
			                roomType(Dungeon[current_row][current_col], prev_room);
			                undo.push(direction);
			            }
		        		else
			        	{
		        	    	current_row--;
		        	      	if(current_row < 0)
		        	    	{
		        	    		current_row = 0;
		        	    	}
		        	    }
		        	}
		        }
		        
		        // If direction is 4, a room will be generated to the left of the current room
		        else if(direction == 4)
		        {
		        	// Checking for edge cases based on fig 7.2 of the IS
		        	if(current_col > 0)
		        	{
		        		// The rest of the code is similar and it will activate the room if it has not been activated
		        		current_col--;
		        		if(Dungeon[current_row][current_col].active == false)
			            {
			                Dungeon[current_row][current_col].activate();
			                Dungeon[current_row][current_col].traversal(2);
			                Dungeon[current_row][current_col + 1].traversal(direction);
			                room_count++;
			                Room_Track[current_row][current_col] = room_count;
			                roomType(Dungeon[current_row][current_col], prev_room);
			                undo.push(direction);
			            }
		        		else
			        	{
		        	    	current_col++;
		        	      	if(current_col > (col -1))
		        	    	{
		        	    		current_col = col - 1;
		        	    	}
		        	    }
		        	}
		        }
	        }
	        // If backtracking is true it will run this block of code
	        else 
	        {
	        	// The previous direction is popped and stored
	        	prev_direction = undo.pop();
	        	
	        	// If previous direction was up, the algorithm will backtrack down
	        	if (prev_direction == 1)
	        	{
	        		current_row++;
	        	}
	        	// If previous direction was right, the algorithm will backtrack left
	        	else if(prev_direction == 2)
	        	{
	        		current_col--;
	        	}
	        	// If previous direction was down, the algorithm will backtrack up
	        	else if(prev_direction == 3)
	        	{
	        		current_row--;
	        	}
	        	// If previous direction was left, the algorithm will backtrack right
	        	else if(prev_direction == 4)
	        	{
	        		current_col++;
	        	}
	        	// Backtracking is complete and it will return to its previous state
	        	backtracking = false;
	        }
	    }
	    
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
	    printDungeon(Room_Track, Dungeon, col, row);
	    
	    // printTxt function is called to create the text file which will be given to the Unity generator.
	    try {
			printTxt(Dungeon, Room_Track, col, row);
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	    
    }
	
	// This function is used to check if the algorithm has become stuck in the dead end
	// This function requires the Dungeon array and the current position of the algorithm
	// It checks if it is stuck by looking at the neighboring rooms of the current position
	// If all the surrounding rooms are activated or does not exist, it is stuck 
	public static boolean stuck(Room[][] Dungeon, int col, int row, int current_col, int current_row)
	{
		boolean flag = false;
		if(current_col < (col - 1) && current_col != 0 && current_row < (row - 1) && current_row != 0)
		{
			if(Dungeon[current_row + 1][current_col].active == true 
					&& Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true
					&& Dungeon[current_row][current_col-1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_row == 0  && current_col == 0)
		{
			if(Dungeon[current_row + 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_row == 0 && current_col == (col-1))
		{
			if(Dungeon[current_row + 1][current_col].active == true  
					&& Dungeon[current_row][current_col-1].active == true)
			{
				flag = true;
			}
			else 
			{
				flag = false;
			}
		}
		else if(current_row == (row-1) && current_col == 0)
		{
			if(Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_row == (row-1) && current_col == (col-1))
		{
			if(Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col-1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_row == 0 && current_col < (col - 1) && current_col > 0)
		{
			if(Dungeon[current_row + 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true
					&& Dungeon[current_row][current_col-1].active == true)	
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_row == (row - 1) && current_col < (col - 1) && current_col > 0)
		{
			if(Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true
					&& Dungeon[current_row][current_col-1].active == true)	
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_col == 0 && current_row < (row - 1) && current_row > 0)
		{
			if(Dungeon[current_row + 1][current_col].active == true 
					&& Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col +1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else if(current_col == (col - 1) && current_row < (row - 1) && current_row > 0)
		{
			if(Dungeon[current_row + 1][current_col].active == true 
					&& Dungeon[current_row - 1][current_col].active == true 
					&& Dungeon[current_row][current_col-1].active == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		return flag;
	}
	
	// This function is used to create the 2D representation of the dungeon
	// Requires most of the arrays that provide information about the dungeon to create the representation
	public static void printDungeon(int[][] Room_Track, Room[][] Dungeon, int col, int row)
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
	public static void printTxt(Room[][] Dungeon, int[][] Room_Track, int col, int row) throws IOException
	{
		PrintWriter out = new PrintWriter("Dungeon.txt");
		
		out.println(col + " " + row);
		
		for (int i= 0; i < row; i++) //loops through rows
	    {
			for (int j= 0; j < col; j++) //loops through columns
			{
				out.print(Room_Track[i][j]+ " ");
			}
			out.print("\n");
	    }
	    
	    for (int i = 0; i < row; i++) //loops through rows
        {
    		for (int j= 0; j < col; j++) //loops through columns
    		{
    			out.print(Dungeon[i][j].room_layout + " ");
    		}
    		out.print("\n");
        }
	    
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
