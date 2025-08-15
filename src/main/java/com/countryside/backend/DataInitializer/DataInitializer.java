package com.countryside.backend;
/*
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

//@Component
public class DataInitializer implements ApplicationRunner {

    private final LocationRepository locationRepository;

    public DataInitializer(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (locationRepository.count() == 0) {

            Location daejeon = new Location("Daejeon_Gwangyeoksi", "대전광역시");
            daejeon.getLandmarks().add(new Landmark("국립중앙과학관", daejeon));
            daejeon.getLandmarks().add(new Landmark("한밭수목원", daejeon));
            daejeon.getLandmarks().add(new Landmark("수통골", daejeon));
            daejeon.getLandmarks().add(new Landmark("국립대전현충원", daejeon));
            daejeon.getLandmarks().add(new Landmark("유성온천지구", daejeon));
            daejeon.getLandmarks().add(new Landmark("성심당", daejeon));
            daejeon.getLandmarks().add(new Landmark("대전 엑스포 아쿠아리움", daejeon));
            daejeon.getLandmarks().add(new Landmark("옛터민속박물관", daejeon));
            daejeon.getLandmarks().add(new Landmark("한국조폐공사 화폐박물관", daejeon));
            daejeon.getLandmarks().add(new Landmark("대전 오!월드", daejeon));

            Location seoul = new Location("Seoul_Teukbyeolsi", "서울특별시");
            seoul.getLandmarks().add(new Landmark("경복궁", seoul));
            seoul.getLandmarks().add(new Landmark("남산타워", seoul));

            Location busan = new Location("Busan_Gwangyeoksi", "부산광역시");
            busan.getLandmarks().add(new Landmark("해운대해수욕장", busan));
            busan.getLandmarks().add(new Landmark("광안리해수욕장", busan));

            Location jeju = new Location("Jeju_Teukbyeoljachido_Jeju", "제주특별자치도 제주시");
            jeju.getLandmarks().add(new Landmark("한라산", jeju));
            jeju.getLandmarks().add(new Landmark("성산일출봉", jeju));

            // 여기부터 전국 주요 지역들 추가 (예시 30개)
            Location gimcheon = new Location("Gimcheon_Si", "김천시");
            gimcheon.getLandmarks().add(new Landmark("김천 문화원", gimcheon));

            Location gumi = new Location("Gumi_Si", "구미시");
            gumi.getLandmarks().add(new Landmark("금오산", gumi));

            Location chungbuk = new Location("Chungbuk_Do", "충청북도");
            chungbuk.getLandmarks().add(new Landmark("청주 무심천", chungbuk));

            Location chungnam = new Location("Chungnam_Do", "충청남도");
            chungnam.getLandmarks().add(new Landmark("공주 공산성", chungnam));

            Location gwangju = new Location("Gwangju_Gwangyeoksi", "광주광역시");
            gwangju.getLandmarks().add(new Landmark("무등산", gwangju));

            Location gangwon = new Location("Gangwon_Do", "강원도");
            gangwon.getLandmarks().add(new Landmark("설악산", gangwon));

            Location incheon = new Location("Incheon_Gwangyeoksi", "인천광역시");
            incheon.getLandmarks().add(new Landmark("송도 센트럴파크", incheon));

            Location sejong = new Location("Sejong_Teukbyeoljachisi", "세종특별자치시");
            sejong.getLandmarks().add(new Landmark("세종호수공원", sejong));

            Location daegu = new Location("Daegu_Gwangyeoksi", "대구광역시");
            daegu.getLandmarks().add(new Landmark("팔공산", daegu));

            Location ulsan = new Location("Ulsan_Gwangyeoksi", "울산광역시");
            ulsan.getLandmarks().add(new Landmark("태화강 국가정원", ulsan));

            Location gyeonggiSuwon = new Location("Gyeonggi_Do_Suwon", "경기도 수원시");
            gyeonggiSuwon.getLandmarks().add(new Landmark("수원 화성", gyeonggiSuwon));

            Location gyeonggiSeongnam = new Location("Gyeonggi_Do_Seongnam", "경기도 성남시");
            gyeonggiSeongnam.getLandmarks().add(new Landmark("판교 테크노밸리", gyeonggiSeongnam));

            Location gyeonggiGoyang = new Location("Gyeonggi_Do_Goyang", "경기도 고양시");
            gyeonggiGoyang.getLandmarks().add(new Landmark("일산 호수공원", gyeonggiGoyang));

            Location gyeonggiHwaseong= new Location("Gyeonggi_Do_Hwaseong", "경기도 화성시");
            gyeonggiHwaseong.getLandmarks().add(new Landmark("동탄 센트럴파크", gyeonggiHwaseong));
            gyeonggiHwaseong.getLandmarks().add(new Landmark("동탄 롯데아울렛", gyeonggiHwaseong));
            gyeonggiHwaseong.getLandmarks().add(new Landmark("동탄 호수공원", gyeonggiHwaseong));

            // 추가 지역 더 넣을 수 있습니다...

            locationRepository.saveAll(List.of(
                    daejeon, seoul, busan, jeju,
                    gimcheon, gumi, chungbuk, chungnam, gwangju,
                    gangwon, incheon, sejong, daegu, ulsan,
                    gyeonggiSuwon, gyeonggiSeongnam, gyeonggiGoyang,
                    gyeonggiHwaseong
            ));

            System.out.println("✅ 전국 30개 이상 지역 및 명소 데이터 자동 등록 완료!");
        }
    }
}
 */
