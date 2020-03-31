package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.Board;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;

public class BoardsService {
	
	static HashMap<String, Board> board_Map = InMemoryDatabase.getBoardDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public BoardsService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//GET, get all boards
	public List<Board> getAllBoards() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("boardId-index")
				.withConsistentRead(false);

		List<Board> list =  mapper.scan(Board.class, scanExpression);
		return list ;
	}
	
	// Getting One course
	public Board getBoard(String boardId) {
		List<Board> boardList = getBoardFromDynamoDB(boardId);
		return boardList.size() != 0 ? boardList.get(0) : null;
	}
		
	//POST, add a new board
	public Board addBoard(Board board) {
		Board newBoard = new Board(board.getBoardId(), board.getCourseId());
		mapper.save(newBoard);
		System.out.println("Item added:");
		System.out.println(newBoard.toString());
		return newBoard;
	}
	
	
	// Deleting a course
	public  Board deleteBoard(String boardId) {
		List<Board> boardList = getBoardFromDynamoDB(boardId);
		Board board = null;
		if(boardList.size() != 0){
			board = boardList.get(0);
			String deleteBoardId = board.getBoardId();
			mapper.delete(board);

			// deleting corresponding announcements in Announcements table
//			AnnouncementsService annService = new AnnouncementsService();
//			annService.deleteAnnouncementsByBoardId(deleteBoardId);

			Board board2 = mapper.load(Board.class, board.getId());
			if (board2 == null) {
				System.out.println("The board is deleted.");
				System.out.println(board.toString());
			}
		}

		return board;
	}
	
	// Updating course Info
	public Board updateBoardInformation(String boardId, Board board) {
		List<Board> list = getBoardFromDynamoDB(boardId);
		Board preBoard = null;
		if(list.size() != 0) {
			preBoard = list.get(0);
			preBoard.setBoardId(board.getBoardId());
			preBoard.setCourseId(board.getCourseId());
			mapper.save(preBoard);
			System.out.println("Item updated:");
			System.out.println(preBoard.toString());
		}
		return preBoard;
	}
	public List<Board> getBoardFromDynamoDB(String boardId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);

		List<Board> boardList =  mapper.query(Board.class, queryExpression);
		return boardList;
	}
		
 }

