package com.group.genshinProg.services;

import com.group.genshinProg.model.ConverterDTO;
import com.group.genshinProg.model.DTO.*;
import com.group.genshinProg.model.entity.PrayResult;
import com.group.genshinProg.repositories.PrayResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tip: Когда собираю достаточно данных с пользователя, делать
 * предсказания на основе его собственной статистики
 */
@Service
public class PrayResultService {
    private final PrayResultRepository prayResultRepository;
    private final Double[] fourStarArray = {0.048, 0.086, 0.057, 0.028, 0.105, 0.028, 0.048, 0.028, 0.371, 0.286};
    private final Double[] fiveStarArray = {
            0.006, 0.00596, 0.00592, 0.00591, 0.00586, // 5
            0.00582, 0.00579, 0.00575, 0.00571, 0.00568, // 10
            0.00565, 0.00561, 0.00558, 0.00554, 0.00552, // 15
            0.00549, 0.00545, 0.00541, 0.00539, 0.00536, // 20
            0.00531, 0.00528, 0.00525, 0.00523, 0.00519, // 25
            0.00517, 0.00513, 0.00511, 0.00507, 0.00503, // 30
            0.00501, 0.00498, 0.00495, 0.00491, 0.00489, // 35
            0.00486, 0.00483, 0.00480, 0.00477, 0.00475, // 40
            0.00471, 0.00469, 0.00467, 0.00464, 0.00461, // 45
            0.00457, 0.00456, 0.00453, 0.00448, 0.00447, // 50
            0.00445, 0.00442, 0.00439, 0.00437, 0.00434, // 55
            0.0043, 0.00428, 0.00426, 0.00423, 0.0042, // 60
            0.00418, 0.00416, 0.00413, 0.0041, 0.00408, // 65
            0.00406, 0.00404, 0.00401, 0.00399, 0.00396, // 70
            0.00393, 0.00392, 0.00388, 0.00387, 0.00384, // 75
            0.20627, 0.13946, 0.09429, 0.06375, 0.04306, // 80
            0.02914, 0.01970, 0.01332, 0.00901, 0.00608, // 85
            0.00411, 0.00278, 0.00187, 0.00126, 0.00265}; // 90


    @Autowired
    public PrayResultService(PrayResultRepository prayResultRepository) {
        this.prayResultRepository = prayResultRepository;
    }

    // таблица со всеми существующими героями PlotHeroes
    // ищем на фронте героя, берем инфу из Plot Heroes, засовываем в объект
    // add-hero, добавляем hero в бд пользователя, считаем какой он по номеру
    // рассчитываем вероятность, возвращаем 2 числа (вероятность 4 и 5)
    // ! здесь возвращаем вероятности выпадения предметов на следующей крутке
    // ! по нажатию специальной кнопки будем выдавать вероятность через какое-то кол-во круток

    public List<PrayResultDTO> getAllPrayResult(String userName) {

        //List<PrayResultDTO> list = prayResultRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream().map(this::convertToPrayResultDTO).collect(Collectors.toList());
        List<PrayResultDTO> list = prayResultRepository.findByUserName(userName).stream().map(this::convertToPrayResultDTO).collect(Collectors.toList());
        return list;
    }

    public void deletePrayResult (Long id) {
        prayResultRepository.deletePrayResultById(id);
    }



    // изменить return на вероятности
    public List<ProbabilityDTO> addPrayer (PrayerDTO prayerDTO) {
        PrayResult prayResultToSave = ConverterDTO.prayerDTOtoPrayResult(prayerDTO);
        prayResultRepository.save(prayResultToSave);
        List<ProbabilityDTO> list = nextObjectsProbability(prayerDTO.getUserName());
        return list;
    }



    public List<ProbabilityDTO> getProbabilities (int num, String userName) {
        //int countFourStar = prayResultRepository.countThreeStarPrayResultsAfterLatestFourStarByUserName(userName);
        //int countFiveStar = prayResultRepository.countThreeStarPrayResultsAfterLatestFiveStarByUserName(userName);

        List<PrayResult> prayResults = prayResultRepository.findByUserName(userName);

        Long latestFourStarId = prayResults.stream().
                filter(result -> "FOUR_STAR".equals(result.getRang().toString()))
                .mapToLong(PrayResult::getId).max().orElse(0);
        int countFourStar = (int) prayResults.stream().
                filter(result -> "THREE_STAR".equals(result.getRang().toString())
                        && result.getId() > latestFourStarId).count();

        Long latestFiveStarId = prayResults.stream().
                filter(result -> "FIVE_STAR".equals(result.getRang().toString()))
                .mapToLong(PrayResult::getId).max().orElse(0);
        int countFiveStar = (int) prayResults.stream().
                filter(result -> !"FIVE_STAR".equals(result.getRang().toString())
                        && result.getId() > latestFiveStarId).count();


//        int countFourStar = prayResultRepository.countThreeStarPrayResultsAfterLatestFourStar();
//        int countFiveStar = prayResultRepository.countThreeStarPrayResultsAfterLatestFiveStar();



        Double fourStarPrediction = 0D;
        Double fiveStarPrediction = 0D;

        if (countFourStar + num >= 10) fourStarPrediction = 100D;
        else {
            for (int i = 0; i < countFourStar + num; i++) {
                fourStarPrediction += fourStarArray[i];
            }

            if (fourStarPrediction > 1) fourStarPrediction = 100D;
            else fourStarPrediction = Math.round(fourStarPrediction * 10000.0) / 100.0;
        }

        if (countFiveStar + num >= 90) fiveStarPrediction = 100D;
        else {
            for (int i = 0; i < countFiveStar + num; i++) {
                fiveStarPrediction += fiveStarArray[i];
            }

            if (fiveStarPrediction > 1) fiveStarPrediction = 100D;
            else fiveStarPrediction = Math.round(fiveStarPrediction*10000.0) / 100.0;
        }

        List<ProbabilityDTO> list = new ArrayList<>();
        list.add(new ProbabilityDTO(fourStarPrediction, fiveStarPrediction));
        return list;
    }


    private List<ProbabilityDTO> nextObjectsProbability (String username) {
        Double fourStarPrediction = nextFourStarProbability(username);
        Double fiveStarPrediction = nextFiveStarProbability(username);

        fourStarPrediction = Math.round(fourStarPrediction * 10000.0) / 100.0;
        fiveStarPrediction = Math.round(fiveStarPrediction * 10000.0) / 100.0;

        List<ProbabilityDTO> list = new ArrayList<>();
        ProbabilityDTO probabilityDTO = new ProbabilityDTO(fourStarPrediction, fiveStarPrediction);
        list.add(probabilityDTO);
        return list;
    }


    private Double getFourStarProbability(int qty) {

        int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFourStar();


        if (num + qty >= 10) {return 0.994;}
        double ans = 0D;
        for (int i = 0; i < qty; i++) {
            ans += fourStarArray[i];
        }

        return ans;
    }


    private Double getFiveStarProbability(int qty) {

        int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFiveStar();

        if (num + qty >= 90) {return 1D;}
        double ans = 0D;
        for (int i = 0; i < qty; i++) {
            ans += fiveStarArray[i];
        }

        return ans;
    }


    private Double nextFourStarProbability(String username) {
        //int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFourStar();
        //int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFourStarByUserName(username);
        List<PrayResult> prayResults = prayResultRepository.findByUserName(username);

        Long latestFourStarId = prayResults.stream().
                filter(result -> "FOUR_STAR".equals(result.getRang().toString()))
                .mapToLong(PrayResult::getId).max().orElse(0);
        int num = (int) prayResults.stream().
                filter(result -> "THREE_STAR".equals(result.getRang().toString())
                        && result.getId() > latestFourStarId).count();

        if (num < 0) throw new exceptions.IncorrectParameterException("Неверное количество круток после четырехзвездочного объекта");
        if (num > 9) return 1D;
        double ans = 0;
        if (num == 9) {
            ans = 0.994;
        } else if (num < 7){
            ans = 0.051*(num+1);
        } else {
            ans = 0.0994*(num+1);
        }
        return ans;

    }

    // метод посчитать количество всех предыдущих персов до 5*
    private Double nextFiveStarProbability(String username) {

        //int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFiveStar();
        //int num = prayResultRepository.countThreeStarPrayResultsAfterLatestFiveStarByUserName(username);

        List<PrayResult> prayResults = prayResultRepository.findByUserName(username);

        Long latestFiveStarId = prayResults.stream().
                filter(result -> "FIVE_STAR".equals(result.getRang().toString()))
                .mapToLong(PrayResult::getId).max().orElse(0);

        int num = (int) prayResults.stream().
                filter(result -> !"FIVE_STAR".equals(result.getRang().toString())
                        && result.getId() > latestFiveStarId).count();


        if (num < 0) throw new exceptions.IncorrectParameterException("Неверное количество круток после пятизвездочного объекта");
        if (num >= 89) return 1D;
        double ans = 0;
        if (num == 89) {
            return 1D;
        }
        if (num < 70) {
            ans = 0.006*(num+1);
        } else {
            ans = 0.011*(num+1);
        }

        return ans;

    }

    private PrayResultDTO convertToPrayResultDTO (PrayResult prayResult) {
        Long id = prayResult.getId();
        String name = prayResult.getName();
        String rang = prayResult.getRang().toString();
        String date = prayResult.getDate().toString();
        String userName = prayResult.getUserName();
        return new PrayResultDTO(id, name, rang, date, userName);
    }
}
