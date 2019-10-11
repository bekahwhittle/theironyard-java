package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    static final int SIZE = 10;

    static Room[][] createRooms (){
        Room [][] rooms = new Room[SIZE][SIZE];
        for (int row = 0; row < SIZE; row ++){
            for (int col = 0; col < SIZE; col ++){
                rooms[row][col]= new Room(row, col);
            }
        }
        return rooms;
    }

    static ArrayList<Room> possNeigh(Room[][] rooms, int row, int col){
        ArrayList<Room> neigh = new ArrayList<>();
        if (row < SIZE - 1) {
            neigh.add(rooms[row+1][col]);
        }
        if (row > 0){
            neigh.add(rooms[row-1] [col]);
        }
        if (col < SIZE - 1){
            neigh.add(rooms[row][col+1]);
        }
        if (col > 0){
            neigh.add(rooms[row][col-1]);
        }

        neigh = neigh.stream()
                .filter(room -> !room.wasVisited)
                .collect(Collectors.toCollection(ArrayList:: new));
        return neigh;
    }

    static Room ranNeigh (Room [][] rooms, int row, int col){
        ArrayList<Room> neigh = possNeigh(rooms, row, col);
        if (neigh.size() == 0){
            return null;
        }

        Random r = new Random();
        int index = r.nextInt(neigh.size());
        return neigh.get(index);

    }

    static void tearDown(Room oldRoom, Room newRoom){
        if (oldRoom.row < newRoom.row){
            oldRoom.hasB = false;
        }
        else if (oldRoom.row > newRoom.row){
            newRoom.hasB = false;
        }
        else if (oldRoom.col < newRoom.col){
            oldRoom.hasR = false;
        }
        else  if (oldRoom.col > newRoom.col){
            newRoom.hasR = false;
        }
    }

    static boolean hitDE = false;

    static boolean createMaze(Room [][] rooms, Room room){
        room.wasVisited = true;
        Room nextRoom = ranNeigh(rooms, room.row, room.col);
        if (nextRoom == null){
            if (hitDE == false){
                hitDE = true;
                room.fin = true;
            }

            return false;
        }
        tearDown(room, nextRoom);

        while(createMaze(rooms, nextRoom));

        return true;
    }


    public static void main(String[] args) {
        Room [][] rooms = createRooms();
        createMaze(rooms, rooms[0][0]);
        for (Room [] row : rooms){
            System.out.print(" _");
        }
        System.out.println();
        for (Room [] row : rooms){
            System.out.print("|");
            for (Room room : row){
                if (room.fin == true){
                    System.out.print("x");
                }
                else if (room.row == 0 && room.col == 0){
                    System.out.print("o");
                }
                else if (room.hasB){
                    System.out.print("_");
                }
                else{
                    System.out.print(" ");
                }
                if (room.hasR){
                    System.out.print("|");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
}
