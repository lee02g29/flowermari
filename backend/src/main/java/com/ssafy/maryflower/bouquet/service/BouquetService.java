package com.ssafy.maryflower.bouquet.service;


import com.ssafy.maryflower.bouquet.data.entity.*;
import com.ssafy.maryflower.bouquet.data.repository.*;
import com.ssafy.maryflower.bouquet.exception.BouquetErrorCode;
import com.ssafy.maryflower.bouquet.exception.BouquetException;
import com.ssafy.maryflower.member.data.entity.Member;
import com.ssafy.maryflower.member.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BouquetService {
    private final MemberRepository memberRepository;
    private final ApiLogRepository apiLogRepository;
    private final BouquetRepository bouquetRepository;
    private final MemberBouquetRepository MemberBouquetRepository;
    private final FlowerRepository flowerRepository;
    private final FlowerBouquetRepository FlowerBouquetRepository;
    // random uuid 생성을 통해 reqyestId 설정
    public String generateRequestID(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString();
    }

    // api 사용기록 저장.
    @Transactional
    public void createApiLog(Long userId){
        // 멤버 조회.
        Member member=memberRepository.findById(userId).orElseThrow(()-> new BouquetException(BouquetErrorCode.INVALID_FLOWER_ID));

        // Api 로그 생성
        ApiLog apiLog=new ApiLog();

        apiLog.setMember(member);

        apiLogRepository.save(apiLog);
    }

    // 하루동안 api 호출 회수 확인.
    public Integer checkApiUses(Long userid){
        LocalDateTime fromDateTime=LocalDateTime.now().minusDays(1);
        return apiLogRepository.numberOfApiUses(userid,fromDateTime);
    }

    // 껓다발 확정 후, 관련 정보 DB에 저장.
    public void saveBucketData(String whom,String situation, String message,String imageUrl,Long memberId,List<Long> flowerIds) {
        // 사용자가 입력한 Text
        Bouquet bouquet = new Bouquet();

        // Member 엔티티 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BouquetException(BouquetErrorCode.INVALID_FLOWER_ID));
        bouquet.setWhom(whom);
        bouquet.setSituation(situation);
        bouquet.setMessage(message);
        bouquet.setImageUrl(imageUrl);
        bouquet.setMember(member);
        // 꽃다발 저장.
        bouquetRepository.save(bouquet);


        // MemberBouquet 생성 및 저장.
        MemberBouquet MemberBouquet = new MemberBouquet();
        MemberBouquet.setBouquet(bouquet);
        MemberBouquet.setMember(member);
        MemberBouquetRepository.save(MemberBouquet);

        // 각 Flower ID에 대해 FlowerBouquet 생성 및 저장.
        for (Long flowerId : flowerIds) {
            Flower flower = flowerRepository.findById(flowerId)
                    .orElseThrow(() -> new BouquetException(BouquetErrorCode.INVALID_FLOWER_ID));
            FlowerBouquet FlowerBouquet = new FlowerBouquet();
            FlowerBouquet.setBouquet(bouquet);
            FlowerBouquet.setFlower(flower);
            FlowerBouquetRepository.save(FlowerBouquet);
        }


    }
    public List<Long> getRandomFlowerIds() {

        List<Flower> allFlowers = flowerRepository.findAll();
        Collections.shuffle(allFlowers, new SecureRandom());

        return allFlowers.stream()
                .limit(7)
                .map(Flower::getFlowerId)
                .collect(Collectors.toList());
    }


}
