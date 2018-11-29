package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Board;
import com.cloudcomputing.fall2018.courseservice.service.BoardService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/boards")
public class BoardResource {

    static BoardService BoardService = new BoardService();

    // .../Boards
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> getAllBoards(){
        return BoardService.getAllBoards();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board addBoard(Board board) {
        return BoardService.addBoard(board);
    }

    // .../boards/1
    @GET
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> getBoard(@PathParam("BoardId") String id) {
        return BoardService.getBoard(id);
    }

    // .../boards/1
    @DELETE
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board deleteBoard(@PathParam("boardId") String id) {
        return BoardService.deleteBoard(id);
    }

    // .../boards/1
    @PUT
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board updateBoard(@PathParam("boardId") String id, Board board) {
        return BoardService.updateBoardInformation(id, board);
    }

}

