package com.group.genshinProg.repositories;


import com.group.genshinProg.model.entity.PrayResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrayResultRepository extends JpaRepository<PrayResult, Long> {

    @Query(value = "SELECT count(*) FROM pray_result WHERE rang='THREE_STAR' AND date > (SELECT MAX(date) FROM pray_result WHERE rang = 'FOUR_STAR')",
            nativeQuery = true)
    int countThreeStarPrayResultsAfterLatestFourStar();

    @Query(value = "SELECT count(*) FROM pray_result WHERE rang='THREE_STAR' AND user_name=?1 AND id > (SELECT MAX(id) FROM pray_result WHERE rang = 'FOUR_STAR' AND user_name=?1)",
            nativeQuery = true)
    int countThreeStarPrayResultsAfterLatestFourStarByUserName(@Param("userName") String userName);

    @Query(value = "SELECT count(*) FROM pray_result WHERE rang<>'FIVE_STAR' AND date > (SELECT MAX(date) FROM pray_result WHERE rang = 'FIVE_STAR')",
            nativeQuery = true)
    int countThreeStarPrayResultsAfterLatestFiveStar();

    @Query(value = "SELECT count(*) FROM pray_result WHERE rang<>'FIVE_STAR' AND user_name=?1 AND id > (SELECT MAX(id) FROM pray_result WHERE rang = 'FIVE_STAR' AND user_name=?1)",
            nativeQuery = true)
    int countThreeStarPrayResultsAfterLatestFiveStarByUserName(String userName);

    void deletePrayResultById(Long id);

    List<PrayResult> findByUserName(String userName);

}
