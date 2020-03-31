package com.csye6225.spring2020.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Announcement;
import com.csye6225.spring2020.courseservice.service.AnnouncementsService;

//.. /webapi/myresource
@Path("/announcements")
public class AnnouncementResource {
    AnnouncementsService annouceService = new AnnouncementsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAllAnnouncements() {
        return annouceService.getAllAnnouncements();
    }

    // ... webapi/announcements/..-..
    @GET
    @Path("/{boardId}-{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement getAnnouncement(@PathParam("boardId") String boardId,
                                        @PathParam("announcementId") String announcementId) {
        return annouceService.getAnnounceById(boardId, announcementId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement addAnnouncement(Announcement announcement) {
        return annouceService.addAnnouncement(announcement);
    }

    @DELETE
    @Path("/{boardId}-{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement deleteAnnouncement(@PathParam("boardId") String boardId,
                                           @PathParam("announcementId") String announcementId) {
        return annouceService.deleteAnnounce(boardId, announcementId);
    }

    @GET
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAnnouncementsByBoardId(@PathParam("boardId") String boardId) {
        return annouceService.getAnnounceByBoardId(boardId);
    }

    @PUT
    @Path("/{boardId}-{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement updateAnnouncement(@PathParam("boardId") String boardId,
                                           @PathParam("announcementId") String announcementId, Announcement announcement) {
        return annouceService.updateAnnounc(boardId, announcementId, announcement);
    }
}
