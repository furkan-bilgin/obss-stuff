package com.furkanbilgin.week3;

import com.furkanbilgin.week3.services.CalcService;
import com.furkanbilgin.week3.services.impl.AcademicCalcService;
import com.furkanbilgin.week3.services.impl.PrimarySchoolCalcService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week3Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Week3Application.class, args);
        CalcService primarySchoolCalcService = context.getBean(PrimarySchoolCalcService.class);
        CalcService academicCalcService = context.getBean(AcademicCalcService.class);

        var logger = LoggerFactory.getLogger(Week3Application.class);

        logger.info("Primary School Calculator Result: {}", primarySchoolCalcService.calculate(1, 2, 3, 4, 5));
        logger.info("Academic Calculator Result: {}", academicCalcService.calculate(1, 2, 3, 4, 5));
    }
}
