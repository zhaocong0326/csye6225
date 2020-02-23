package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Board;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;

public class BoardsService {
	
	static HashMap<String, Board> board_Map = InMemoryDatabase.getBoardDB();
	
	public BoardsService(){
		
	}
	
	//GET, get all boards
	public List<Board> getAllBoards() {	
		//Getting the list
		ArrayList<Board> list = new ArrayList<>();
		for (Board board : board_Map.values()) {
			list.add(board);
		}
		if (list.size() != 0) {
			System.out.println("All Boards retrieved");
		} else {
			System.out.println("No Boards data.");
		}
		return list ;
	   
	}
	
	// Getting One course
	public Board getBoard(String boardId) {
		if (board_Map.containsKey(boardId)) {
			 //Simple HashKey Load
			Board board = board_Map.get(boardId);
			System.out.println("Item retrieved:");
			System.out.println(board.toString());
			return board;
		} else {
			System.out.println("Board " + boardId + " does not exist!");
			return null;
		}
	}
		
	//POST, add a new board
	public Board addBoard(Board board) {
		 Board newBoard = new Board(board.getBoardId(), board.getCourseId());
	     board_Map.put(board.getBoardId(), newBoard);	
		 System.out.println("Item added:");
	     System.out.println(newBoard.toString());
		 return newBoard;
	}
	
	
	// Deleting a course
	public  Board deleteBoard(String boardId) {
		if (board_Map.containsKey(boardId)) {
			Board deletedBoardDetails = board_Map.get(boardId);
			board_Map.remove(boardId);
			System.out.println("Item deleted:");
			System.out.println(deletedBoardDetails.toString());
			return deletedBoardDetails;
		} else {
			System.out.println("Board " + boardId + "does not exist!");
			return null;
		}
	}
	
	// Updating course Info
	public Board updateBoardInformation(String boardId, Board board) {
		if (board_Map.containsKey(boardId)) {
			board.setBoardId(boardId);
			board_Map.put(boardId, board);
			System.out.println("Item updated:");
			System.out.println(board.toString());
		} else {
			System.out.println("Cannot find the board data with " + boardId);
		}
		return board;
	}
		
 }

