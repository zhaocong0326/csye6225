package com.csye6225.spring2020.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.Announcement;

public class AnnouncementsService {
    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;

    public AnnouncementsService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }

    // Get all announcements
    // GET "..webapi/announcements"
    public List<Announcement> getAllAnnouncements() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("boardId-announcementId-index").withConsistentRead(false);

        List<Announcement> announcementList = mapper.scan(Announcement.class, scanExpression);
        return announcementList;
    }

    // get an announcement
    public Announcement getAnnounceById(String announcementId, String boardId) {
        List<Announcement> announcementList = getAnnouncementFromDynamoDB(announcementId, boardId);
        return announcementList.size() != 0 ? announcementList.get(0) : null;
    }

    // add an announcement
    public Announcement addAnnouncement(Announcement announcement) {
        Announcement newAnnouce = new Announcement();
        newAnnouce.setBoardId(announcement.getBoardId());
        newAnnouce.setAnnouncementId(announcement.getAnnouncementId());
        newAnnouce.setAnnouncementContent(announcement.getAnnouncementContent());
        newAnnouce.setCourseId(announcement.getCourseId());
        mapper.save(newAnnouce);

        return newAnnouce;
    }

    public Announcement deleteAnnounce(String boardId, String announcementId) {
        List<Announcement> list = getAnnouncementFromDynamoDB(boardId, announcementId);
        Announcement announce = null;
        if (list.size() != 0) {
            announce = list.get(0);
            mapper.delete(announce);
            Announcement announceDeleting = mapper.load(Announcement.class, announce.getId());

            if (announceDeleting == null) {
                System.out.println("announcement is deleted.");
                System.out.println(announce.toString());
            }
        }

        return announce;
    }

    public Announcement updateAnnounc(String boardId, String announcementId, Announcement announcement) {

        List<Announcement> announcementList = getAnnouncementFromDynamoDB(boardId, announcementId);
        Announcement announcement2 = null;

        if (announcementList.size() != 0) {
            announcement2 = announcementList.get(0);
            announcement2.setAnnouncementId(announcement.getAnnouncementId());
            announcement2.setAnnouncementContent(announcement.getAnnouncementContent());
            announcement2.setAnnouncementContent(announcement.getAnnouncementContent());

            mapper.save(announcement2);

            System.out.println("Annouce updated.");
            System.out.println(announcement2.toString());

        }

        return announcement2;
    }

    public List<Announcement> getAnnounceByBoardId(String boardId) {
        HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        map.put(":v1", new AttributeValue().withS(boardId));

        DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
                .withIndexName("boardId-announcementId-index").withConsistentRead(false)
                .withKeyConditionExpression("boardId = :v1").withExpressionAttributeValues(map);

        List<Announcement> announcementList = mapper.query(Announcement.class, queryExpression);
        return announcementList;
    }

    public void deleteAnnouncementsByBoardId(String boardId) {
        List<Announcement> announcementList = getAnnounceByBoardId(boardId);

        if (announcementList.size() != 0) {
            mapper.batchDelete(announcementList);
        }
    }

    private List<Announcement> getAnnouncementFromDynamoDB(String boardId, String announcementId) {
        HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        map.put(":v1", new AttributeValue().withS(boardId));
        map.put(":v2", new AttributeValue().withS(announcementId));

        DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
                .withIndexName("boardId-announcementId-index").withConsistentRead(false)
                .withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
                .withExpressionAttributeValues(map);

        List<Announcement> list = mapper.query(Announcement.class, queryExpression);
        return list;
    }
}
