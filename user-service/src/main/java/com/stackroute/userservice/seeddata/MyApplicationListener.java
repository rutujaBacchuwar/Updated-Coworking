package com.stackroute.userservice.seeddata;

import com.stackroute.kafka.domain.*;
import com.stackroute.userservice.controller.SpaceController;
import com.stackroute.userservice.repository.SpaceRepository;
import com.stackroute.userservice.service.SpaceService;
import com.stackroute.userservice.service.SpaceServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;


@Component
@Configuration


public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

//    SpaceRepository spaceRepository;
//
//    @Autowired
//    public MyApplicationListener(SpaceRepository spaceRepository) {
//        this.spaceRepository = spaceRepository;
//    }

    SpaceServiceImpl spaceService;

    @Autowired
    public MyApplicationListener(SpaceServiceImpl spaceService) {
        this.spaceService = spaceService;
    }

    //    private static final Logger logs = Logger.getLogger("MyApplicationListener.class");
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        User user = new User();
        Space space=new Space();
        List<Category> categories = new ArrayList<Category>();
        // Category category=new Category();
        Location location = new Location();

        Amenities amenities = new Amenities();
        Dimension dimension = new Dimension();


        System.out.println("***********************");

//        User user1 = new User();
//        Space space1 = new Space();
//        //List<Category> categories = new ArrayList<Category>();
//        Category category1=new Category();
//        Location location1 = new Location();
//        Address address1 = new Address();
//        Amenities amenities1 = new Amenities();
//        Dimension dimension1 = new Dimension();


        // Space space1 = new Space();

        String userName=null;

        //logs.info("data Successfully inserted@@@@@@@@@@@@");

        try {

            FileInputStream file = new FileInputStream(new File("/home/rutuja/user-service/SpaceData.xlsx"));
            Scanner scan=new Scanner(file);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                for (int i = 1; cellIterator.hasNext(); i++) {

                    System.out.println("value of i"+i);
                    Address address = new Address();
                    user.setName(sheet.getRow(i).getCell(0).toString());
                    System.out.println((sheet.getRow(i).getCell(4)));
                    space.setSpaceId((int)sheet.getRow(i).getCell(1).getNumericCellValue());
                    space.setSpaceName(sheet.getRow(i).getCell(2).toString());
                    space.setSpaceImageUrl(sheet.getRow(i).getCell(3).toString());
                    address.setBuildingNumber(sheet.getRow(i).getCell(4).toString());
                    address.setArea(sheet.getRow(i).getCell(5).toString());
                    address.setCity(sheet.getRow(i).getCell(6).toString());
                    address.setState(sheet.getRow(i).getCell(7).toString());
                    address.setCountry(sheet.getRow(i).getCell(8).toString());
                    address.setPincode((int)sheet.getRow(i).getCell(9).getNumericCellValue());
                    amenities.setAmenities(sheet.getRow(i).getCell(10).toString().split(","));
                    location.setLocationName(sheet.getRow(i).getCell(11).toString());
                    location.setLatitude(sheet.getRow(i).getCell(12).getCellType());
                    location.setLongitude(sheet.getRow(i).getCell(13).getCellType());
                    // for (int j=0;j<)
//                        category.setCategoryName(sheet.getRow(i).getCell(14).toString());
//                        category.setHourlyPrice(sheet.getRow(i).getCell(15).toString());
//                        category.setDailyPrice(sheet.getRow(i).getCell(16).toString());
//                        category.setMonthlyPrice(sheet.getRow(i).getCell(17).toString());
                    // category.setCapacity(workbook.getSheetAt(0).getRow(i).getCell(j + 18).getCellType());
                    //category.setAdditionalAmenities(sheet.getRow(i).getCell(18).toString().split(","));


//                        space.setUser(space.getUser());
//
                    for (int k=0; k<=categories.size()-1;k++)
                    {
//                            Category category=new Category();
//                            category.setCategoryName(sheet.getRow(i).getCell(14).toString());
//                            category.setHourlyPrice(sheet.getRow(i).getCell(15).toString());
//                            category.setDailyPrice(sheet.getRow(i).getCell(16).toString());
//                            category.setMonthlyPrice(sheet.getRow(i).getCell(17).toString());
//                            //Category category1= categories.get(k);
                        //category1.setDimension(category1.getDimension());
                        space.setCategory(categories);
                    }
//                        space.setCategory(space.getCategory());
//                        space.setLocation( space1.getLocation());
//                        Address add=space.getAddress();
//                        space1.setAddress(add);
//                        space1.setAmenities(space1.getAmenities());

                    //              user=space.getUser();
                    space.setUser(user);

                    //  category1 = space.getCategory();
//                    for (int i=0; i<=category.size()-1;i++)
//                    {
//                        Category category1= category.get(i);
//                        dimension= category1.getDimension();
//                        category1.setDimension(dimension);
                    // }
                    //  space.setCategory(category);

                    space.setLocation(location);

                    space.setAddress(address);

                    space.setAmenities(amenities);

                    System.out.println("**************");

                    System.out.println(space);

                    //  spaceRepository.save(space);

                    spaceService.saveSpace(space);


                    //  spaceController.saveSpace(space);
                }
            }
            file.close();
        }   catch (Exception e) {
            e.printStackTrace();
            System.out.println("message");
            // logs.info("Exception");
        }
        // System.out.println(userName);
    }
}
