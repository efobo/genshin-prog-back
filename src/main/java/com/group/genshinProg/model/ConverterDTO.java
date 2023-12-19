package com.group.genshinProg.model;

import com.group.genshinProg.model.DTO.*;
import com.group.genshinProg.model.entity.PrayResult;
import com.group.genshinProg.model.entity.User;
import com.group.genshinProg.model.enums.RangCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDTO {


    public static PrayResult prayerDTOtoPrayResult (PrayerDTO prayerDTO) {
        String name = prayerDTO.getName();
        String rang;
        if (prayerDTO.getRang().equals(RangCode.THREE_STAR.toString())) rang = RangCode.THREE_STAR.toString();
        else if (prayerDTO.getRang().equals(RangCode.FOUR_STAR.toString())) rang = RangCode.FOUR_STAR.toString();
        else rang = RangCode.FIVE_STAR.toString();

        String username = prayerDTO.getUserName();
        return new PrayResult(name, rang, username);
    }

    public static List<PrayResult> threeStarDTOtoHero(ThreeStarDTO threeStarDTO) {
        int amount = threeStarDTO.getAmount();
        PrayResult prayResult = new PrayResult(null, RangCode.THREE_STAR.toString(), threeStarDTO.getUserName());
        List<PrayResult> list = new ArrayList<>();
        for (int i =0; i < amount; i++) {
            list.add(prayResult);
        }
        return list;
    }

    public static PrayResult prayResultDTOtoPrayResult (PrayResultDTO prayResultDTO) {
        String name = prayResultDTO.getName();
        String rang = prayResultDTO.getRang();
        String userName = prayResultDTO.getUserName();
        return new PrayResult(name, rang, userName);
    }

    public static User userDTOtoUser (UserDTO userDTO) {
        String name = userDTO.getName();
        String password = userDTO.getPassword();
        return new User(name, password);
    }

    public static UserDTO userToDTO (User user) {
        String name = user.getName();
        String password = user.getPassword();
        return new UserDTO(name, password);
    }


}
