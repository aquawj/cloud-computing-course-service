package com.cloudcomputing.fall2018.courseservice.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cloudcomputing.fall2018.courseservice.datamodel.*;

import java.util.List;

public class BoardService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
    DynamoDBQueryExpression<Board> queryExpression;
    DynamoDBScanExpression scanExpression;
	
	public BoardService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		queryExpression = new DynamoDBQueryExpression<Board>();
	    scanExpression = new DynamoDBScanExpression();
	}

    //get all boards
    public List<Board> getAllBoards(){
    	return mapper.scan(Board.class, scanExpression);
    }

    public Board addBoard(Board board) {
        mapper.save(board);
        return board;
    }

    // get a board by ID
    public List<Board> getBoard(String boardId) {
    	List<Board> boards = queryBoards(boardId);
		return boards;
    }

    //delete a board
    public Board deleteBoard(String boardId) {
    	for(Board b : getBoard(boardId)) {
			mapper.delete(b);
		}
		return getBoard(boardId).get(0);
    }

    //update a board
    public Board updateBoardInformation(String boardId, Board board) {
    	List<Board> delete = getBoard(boardId);
    	for(Board d : delete) {
    		mapper.delete(d);
    	}
    	board.setBoardId(boardId);
		mapper.save(board);
		return board;
    }
    
    private List<Board> queryBoards(String boardId) {
    	Board board = new Board();
        board.setBoardId(boardId);
        queryExpression.setHashKeyValues(board);
        queryExpression.withIndexName("boardId-index");
        queryExpression.setConsistentRead(false);
        List<Board> boards = mapper.query(Board.class, queryExpression);
        return boards;
    }

}

