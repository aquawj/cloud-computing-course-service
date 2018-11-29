package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Announcement;
import com.cloudcomputing.fall2018.courseservice.service.AnnouncementService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/announcements")
public class AnnouncementResource {

    static AnnouncementService AnnouncementService = new AnnouncementService();

    // .../announcements
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAllAnnouncements(){
        return AnnouncementService.getAllAnnouncements();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement addAnnouncement(Announcement announcement) {
        return AnnouncementService.addAnnouncement(announcement);
    }

    // .../announcements/{boardId}/{announcementId}
    @GET
    @Path("/{boardId}/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAnnouncement(@PathParam("boardId") String bId, @PathParam("announcementId") String aId) {
        return AnnouncementService.getAnnouncement(bId + "_" + aId);
    }

    // .../announcements/{boardId}/{announcementId}
    @DELETE
    @Path("/{boardId}/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement deleteAnnouncement(@PathParam("boardId") String bId, @PathParam("announcementId") String aId) {
        return AnnouncementService.deleteAnnouncement(bId + "_" + aId);
    }

 // .../announcements/{boardId}/{announcementId}
    @PUT
    @Path("/{boardId}/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement updateAnnouncement(@PathParam("boardId") String bId, @PathParam("announcementId") String aId, Announcement Announcement) {
        return AnnouncementService.updateAnnouncementInformation(bId + "_" + aId, Announcement);
    }

}

