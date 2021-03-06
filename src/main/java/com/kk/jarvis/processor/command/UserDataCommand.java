package com.kk.jarvis.processor.command;

import com.kk.jarvis.dao.UserDataDao;
import com.kk.jarvis.dto.UserDataDto;
import com.kk.jarvis.dto.UserInfoDto;
import com.kk.jarvis.dto.UserUnitAssignmentDto;
import com.kk.jarvis.processor.Command;
import com.kk.jarvis.processor.CommandProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kkedari
 * Date: 5/18/14
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDataCommand implements Command {

    private List<String> params;

    private UserInfoDto userInfoDto;

    private JdbcTemplate jdbcTemplate;

    public UserDataCommand(UserInfoDto userInfoDto, List<String> params, JdbcTemplate jdbcTemplate) {
        this.params = params;
        this.userInfoDto = userInfoDto;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> execute() {

        if(params.size() < 3) {
            return null;
        }
        String name = params.get(0);
        int noOfCommands = Integer.parseInt(params.get(1));
        Date addTime = null;
        if(params.size() > (2*noOfCommands +1)) {
            // time offset is added

            try {
                addTime = new SimpleDateFormat().parse(params.get(params.size() - 1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for(int i =1; i <= noOfCommands; i++) {

            String value = params.get(i*2);

            String unit = params.get(i*2 + 1);


            String category = userInfoDto.getUserDataString("category");
            String subCategory = userInfoDto.getUserDataString("subcategory");


            Map<String, UserUnitAssignmentDto> assignmentDtoMap = null;
            if(userInfoDto.getUserData("assignments") != null) {
                assignmentDtoMap =  ( Map<String, UserUnitAssignmentDto>)userInfoDto.getUserData("assignments");
                if(assignmentDtoMap.containsKey(unit)) {
                    UserUnitAssignmentDto userUnitAssignmentDto = assignmentDtoMap.get(unit);
                    category = userUnitAssignmentDto.getCategory();
                    subCategory = userUnitAssignmentDto.getSubCategory() != null ? userUnitAssignmentDto.getSubCategory() : "";
                }
            }




            UserDataDto userDataDto = new UserDataDto();
            userDataDto.setName(name);
            userDataDto.setValue(value);
            userDataDto.setUnit(unit);
            userDataDto.setUserId(userInfoDto.getUserId());
            userDataDto.setCategory(category);
            userDataDto.setSubCategory(subCategory);
            addTime = addTime == null ? new Date() : addTime;
            userDataDto.setAddTime(addTime);
            UserDataDao userDataDao = new UserDataDao(jdbcTemplate);

            String response = userDataDao.addUserData(userDataDto);

            if(response == null )
            {
                return null;
            }
        }


        return params;

    }

    @Override
    public int getType() {
        return CommandProcessor.USER_DATA_COMMAND;
    }
}
