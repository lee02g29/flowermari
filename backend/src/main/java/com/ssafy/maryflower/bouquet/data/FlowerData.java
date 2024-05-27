package com.ssafy.maryflower.bouquet.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
public class FlowerData {
    public static final List<FlowerInfo> FLOWERS = Arrays.asList(
            new FlowerInfo("빨강 장미", "Red Rose", "red", "사랑, 아름다움, 열정", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/1.png"),
            new FlowerInfo("하양 장미", "White Rose", "white", "존경,순결,순진,매력", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/2.png"),
            new FlowerInfo("분홍 장미", "Pink Rose", "pink", "맹세, 단순, 행복한사랑", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/3.png"),
            new FlowerInfo("빨강 튤립", "Red Tulip", "red", "사랑의 고백, 신중함", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/4.png"),
            new FlowerInfo("노랑 튤립", "Yellow Tulip", "yellow", "희망, 생명력", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/5.png"),
            new FlowerInfo("보라 튤립", "Purple Tulip", "purple", "영원한 사랑, 영원하지 않은 사랑", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/6.png"),
            new FlowerInfo("안개꽃", "White Gypsophila", "white", "맑은 마음, 순수한 사랑, 미지의 사랑", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/7.png"),
            new FlowerInfo("아이리스", "Iris", "purple", "지혜, 자신감, 아름다움", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/8.png"),
            new FlowerInfo("백합", "White Lily", "white", "순결, 우아함", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/9.png"),
            new FlowerInfo("빨강 카네이션", "Red Carnation", "red", "부모를 향한 사랑, 감사함, 건강 기원", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/10.png"),
            new FlowerInfo("분홍 카네이션", "Pink Carnation", "pink", "사랑의 고백, 감사하는 마음", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/11.png"),
            new FlowerInfo("수국", "White Hydrangea", "white", "변덕, 진실된 마음", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/12.png"),
            new FlowerInfo("거베라", "Pink Gerbera", "pink", "감사, 숭고한 아름다움", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/13.png"),
            new FlowerInfo("라벤더", "Purple Lavender", "purple", "사랑, 행운", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/14.png"),
            new FlowerInfo("히아신스", "Pink Hyacinth", "pink", "사랑의 기쁨", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/15.png"),
            new FlowerInfo("해바라기", "Yellow Sunflower", "yellow", "활력, 인내", "https://a305-project-bucket.s3.ap-northeast-2.amazonaws.com/flowerBaseImage/FlowerImage/16.png")
    );

    @AllArgsConstructor
    @Getter
    public  static class FlowerInfo{
        Long id;
        String koreanName;
        String englishName;
        String color;
        String meaning;
        String imageUrl;

        public FlowerInfo(String koreanName, String englishName, String color, String meaning, String imageUrl) {
            this(null, koreanName, englishName, color, meaning, imageUrl);
        }
    }

}
