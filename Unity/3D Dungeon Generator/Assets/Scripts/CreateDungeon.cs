using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

// This script handles the generation of the 3D dungeon
public class CreateDungeon : MonoBehaviour
{
    // Here we are creating variables to store the Dungeon.txt file,
    // the player objects and the prefabs for the different room layouts
    [SerializeField] TextAsset file;
    [SerializeField] GameObject player;
    [SerializeField] GameObject A;
    [SerializeField] GameObject B;
    [SerializeField] GameObject C;
    [SerializeField] GameObject D;
    [SerializeField] GameObject E;
    [SerializeField] Vector2 offset;

    // Start is called before the first frame update
    // Runs when the game starts
    void Start()
    {
        // String used to split file based on new line
        var splitFile = new string [] {"\r\n", "\r", "\n"};
        // String used to split line based on space
        var splitLine = new char[] {' '};

        // File is split based on lines and each line is stored in the indices of this array
        var Lines = file.text.Split(splitFile, System.StringSplitOptions.RemoveEmptyEntries);
        // Each line is split based on white spaced and stored in the indices of this array
        var colLine = Lines[0].Split(splitLine, System.StringSplitOptions.None);
        // Column size of dungeon grid
        int col = Int32.Parse(colLine[0]);
        // Row size of dungeon grid
        int row = Int32.Parse(colLine[1]);

        // Here we intialize three arrays
        // The first stores the locations of the rooms
        // The second stores the layout of the rooms
        // The third stores the type of the rooms
        string[ , ] rooms = new string [row, col];
        string[ , ] layouts = new string [row, col];
        string[ , ] types = new string [row, col];

        // Here we extract the location of the rooms from the text file
        for(int i = 1; i <= row; i++)
        {
            var room = Lines[i].Split(splitLine, System.StringSplitOptions.None);
            for(int j = 0; j < col; j++)
            {
                rooms[i - 1,j] = room[j];
                print(room[j]);
            }
        }
        
        // Here we extract the layout of the rooms from the text file
        for(int i = (row + 1); i <= row*2; i++)
        {
            var layout = Lines[i].Split(splitLine, System.StringSplitOptions.None);
            for(int j = 0; j < col; j++)
            {
                layouts[i-row-1,j] = layout[j];
                print(layout[j]);
            }
        }

        // Here we extract the type of the rooms from the text file
        for(int i = ((row * 2) + 1); i < Lines.Length; i++)
        {
            var type = Lines[i].Split(splitLine, System.StringSplitOptions.None);
            for(int j = 0; j < col; j++)
            {
                types[i-(row*2)-1,j] = type[j];
                print(type[j]);
            }
        }

        // Call the function to generate the dungeon
        GenerateDungeon(rooms, layouts, types, col, row);

    }

    // This is the main function that generates the 3D representation of the dungeon
    // It requires information from the three main arrays and the size of the dungeon grid
    void GenerateDungeon(string [ , ] rooms, string [ , ] layouts, string [ , ] types,  int col, int row)
    {
        int start_col = 0;
        int start_row = 0;

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if(rooms[i , j] == Convert.ToString(1))
                {
                    start_col = j;
                    start_row = i;
                }

                if(rooms[i, j] != "0")
                {
                    if(layouts[i, j] == "A1")
                    {
                        var newRoom = Instantiate(A, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.identity, transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "A2")
                    {
                        var newRoom = Instantiate(A, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,90,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "A3")
                    {
                        var newRoom = Instantiate(A, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,180,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "A4")
                    {
                        var newRoom = Instantiate(A, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,270,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "B1")
                    {
                        var newRoom = Instantiate(B, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,90,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "B2")
                    {
                        var newRoom = Instantiate(B, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.identity , transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "C1")
                    {
                        var newRoom = Instantiate(C, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.identity, transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "C2")
                    {
                        var newRoom = Instantiate(C, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,90,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "C3")
                    {
                        var newRoom = Instantiate(C, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,180,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "C4")
                    {
                        var newRoom = Instantiate(C, new Vector3(i * offset.x, 0, j *offset.y),Quaternion.Euler(0,270,0) , transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "D1")
                    {
                        var newRoom = Instantiate(D, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.identity, transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "D2")
                    {
                        var newRoom = Instantiate(D, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,90,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "D3")
                    {
                        var newRoom = Instantiate(D, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,180,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "D4")
                    {
                        var newRoom = Instantiate(D, new Vector3(i * offset.x, 0, j *offset.y), Quaternion.Euler(0,270,0), transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                    if(layouts[i, j] == "E")
                    {
                        var newRoom = Instantiate(E, new Vector3(i * offset.x, 1, j *offset.y), Quaternion.identity, transform);
                        SelectColor(types[i , j], newRoom);
                        newRoom.name += " " + i + "-" + j;
                    }
                }
            }
        }
        player.transform.position = new Vector3(start_row*offset.x, 0, start_col*offset.y);
    }

    // This function is used to make sure that the appropriate color
    // is assigned to the appropriate room type
    void SelectColor(string type, GameObject room)
    {
        if(type == "S")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.white;
            }           
        }
        else if(type == "T")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.green;
            }  
        }
        else if(type == "E")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.yellow;
            }  
        }
        else if(type == "N")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.blue;
            }  
        }
        else if(type == "C")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.red;
            }  
        }
        else if(type == "F")
        {
            for(int i = 0; i < room.transform.childCount; i++)
            {
                GameObject child = room.transform.GetChild(i).gameObject;
                child.GetComponent<Renderer>().material.color = Color.black;
            }  
        }
    }
}
