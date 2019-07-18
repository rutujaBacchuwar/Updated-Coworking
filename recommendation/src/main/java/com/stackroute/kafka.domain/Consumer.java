package com.stackroute.kafka.domain;

import com.stackroute.recommendation.domain.Location;
import com.stackroute.recommendation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import java.util.ArrayList;
import java.util.List;


public class Consumer {
    com.stackroute.recommendation.domain.Location loc = new Location();
    com.stackroute.recommendation.domain.Space sp = new com.stackroute.recommendation.domain.Space();
    com.stackroute.recommendation.domain.Category cat = new com.stackroute.recommendation.domain.Category();
   com.stackroute.recommendation.domain.Booked booked=new com.stackroute.recommendation.domain.Booked();
    @Autowired
    private LocationService locationService;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    LocatedService locatedService;

    @Autowired
    ContainsService containsService;

    @Autowired
    BookedService bookedService;

    @Autowired
    UserService userService;


    @KafkaListener(topics = "spaceTopicForRecommendation")
    public void receive(@Payload Space space) {
        //  System.out.println("Consumed space for use of recommendation:" + space.toString());

        //for location
        com.stackroute.kafka.domain.Location location = space.getLocation();
        loc.setLocationName(location.getLocationName());
        loc.setLatitude(location.getLatitude());
        loc.setLongitude(location.getLongitude());
        loc.setLocationId((location.getLocationId()));
        locationService.create(loc.getLocationId(), loc.getLocationName(), loc.getLatitude(), loc.getLatitude());
        System.out.println("Saved data of location" + loc.toString());

    }


    @KafkaListener(topics = "spaceTopicForRecommendation1")
    public void receive1(@Payload Space space) {

        //System.out.println("Consumed space for use of recommendation:" + space.toString());

        //for space
        com.stackroute.kafka.domain.Space prodspace = new Space();
        prodspace.setSpaceId(space.getSpaceId());
        prodspace.setSpaceName(space.getSpaceName());
        prodspace.setCategory(space.getCategory());
        prodspace.setLocation(space.getLocation());
        sp.setSpaceId(prodspace.getSpaceId());
        sp.setSpaceName(prodspace.getSpaceName());
        spaceService.create(sp.getSpaceId(), sp.getSpaceName());
        System.out.println("Set values of space Id and space Name" + sp.toString());
        locatedService.createRelationship(sp.getSpaceName(), space.getLocation().getLocationName());
        containsService.createRelationship(sp.getSpaceId());

    }


    @KafkaListener(topics = "spaceTopicForRecommendationCategory")
    public void receive2(@Payload Space space) {
        //System.out.println("Consumed space for use of recommendation:" + space.toString());

        //for category
        List<Category> list = new ArrayList<Category>();
        list = space.getCategory();
        for (int i = 0; i < list.size(); i++) {
            Category category = list.get(i);
            cat.setCategoryId(category.getCategoryId());
            cat.setCategoryName(category.getCategoryName());
           // cat.setPrice(category.getPrice());
            System.out.println("ouweuewgeuwfuo"+category.getMonthlyPrice());
            cat.setPrice(category.getMonthlyPrice());
            System.out.println("consumed price"+cat.getPrice());
            sp.setSpaceId(space.getSpaceId());
            sp.setSpaceName(space.getSpaceName());
            cat.setSpace(sp);

            categoryService.create(cat.getCategoryId(), cat.getCategoryName(), cat.getPrice(), cat.getSpace());
            System.out.println("Set values of category Id and category Name=" + cat.toString());

        }
    }


    @KafkaListener(topics = "BookingTopic")
    public void receive3(@Payload Booking booking) {
        System.out.println("consumed booking"+booking.toString());
        MyUser myuser=booking.getMyUser();
        MySpace mySpace=myuser.getMyCategory().getMySpace();
        System.out.println("=================="+myuser.getName());
        System.out.println("++++++++++++++++++"+myuser.getMyCategory().getCategoryName());
        System.out.println("_______________________________"+mySpace.spaceId);
        userService.create(myuser.getUid(),myuser.getName(),myuser.getEmailId(),myuser.getUid());
        bookedService.createRelationship(myuser.getName(),myuser.getMyCategory().getCategoryName(),mySpace.spaceId);


    }

}


