package com.csye6225.spring2020.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Board;
import com.csye6225.spring2020.courseservice.service.BoardsService;

//.. /webapi/myresource
@Path("boards")
public class BoardsResource {
	BoardsService boardsService = new BoardsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getBoards() {
		return boardsService.getAllBoards();
	}	
	
	// ... webapi/boards/1
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {
		System.out.println("board Resource: Looking for: " + boardId);
		return boardsService.getBoard(boardId);
	}
	
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId") String boardId) {
		return boardsService.deleteBoard(boardId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board) {
		return boardsService.addBoard(board);
	}
	
	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("boardId") String boardId,
			Board board) {
		return boardsService.updateBoardInformation(boardId, board);
	}
}
