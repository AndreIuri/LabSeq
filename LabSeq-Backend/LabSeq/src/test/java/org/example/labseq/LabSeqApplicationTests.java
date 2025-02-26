package org.example.labseq;

import org.example.labseq.Services.LabSeqService;
import org.example.labseq.Services.LabSeqServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class LabSeqApplicationTests {

    @InjectMocks
    private LabSeqServiceImpl labSeqServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNullValue() {
        BigInteger value = labSeqServiceImpl.getValue(0);

        assertEquals(BigInteger.valueOf(0), value);
    }

    @Test
    void testGetNegativeValue() {
        BigInteger value = labSeqServiceImpl.getValue(-5);

        assertEquals(BigInteger.valueOf(0), value);
    }

    @Test
    void testGetValue1() {
        BigInteger value = labSeqServiceImpl.getValue(10);

        assertEquals(BigInteger.valueOf(3), value);
    }

    @Test
    void testGetValue2() {
        BigInteger value = labSeqServiceImpl.getValue(100);

        assertEquals(BigInteger.valueOf(182376579), value);
    }

    @Test
    void testGetValue3() {
        BigInteger value = labSeqServiceImpl.getValue(10000);

        BigInteger expectedValue = new BigInteger("69950566878097184013157744477635556727868849589082998911839343197880823215346221009722233023943602770307729191665655398407165768121564186987192397693071609846919453430811144389823875683774480880281479951416523467736343974525549960389427464841013320746241755697990287429747307066048835194835534301361435435171244963037487135503198565459610125773779110841477593382691667903942271834984619627946845583317271714790127086723614783681640902031022970893247841818337935296805019561967546398282596597404334400595273408222818081762762981879844447410743730739725556081175617700994424267694361314464204552899258977619983936670456553201627025301979470684612183482967552781789171894406131379502874476544298881442363169258726593616997962614541232149734611181684936265928412294383549494959124156102645749161099774806409315657803977415799277767229630141831326718534674913706653355139");

        assertEquals(expectedValue, value);
    }

    @Test
    void testGetValuePerformance() {
        LabSeqServiceImpl labSeqServiceImpl = new LabSeqServiceImpl();

        long startTime = System.nanoTime();
        BigInteger value = labSeqServiceImpl.getValue(10000);
        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;

        System.out.println("Execution time: " + durationInMillis + " ms");

        assertTrue(durationInMillis < 10_000);
    }

}
