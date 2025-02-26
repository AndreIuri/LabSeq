package org.example.labseq.Services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;

@Service
public class LabSeqServiceImpl implements LabSeqService{

    private final HashMap<Integer, BigInteger> labSeqMap = new HashMap<>();

    public LabSeqServiceImpl(){
        labSeqMap.put(0, BigInteger.valueOf(0));
        labSeqMap.put(1, BigInteger.valueOf(1));
        labSeqMap.put(2, BigInteger.valueOf(0));
        labSeqMap.put(3, BigInteger.valueOf(1));
    }

    @Override
    public String getValue(Integer n){
        BigInteger value = BigInteger.valueOf(0);

        if(n>=0) {
            if (!labSeqMap.containsKey(n)) {
                for (var i = 4; i <= n; i++) {
                    if (!labSeqMap.containsKey(i)) {
                        BigInteger operand1 = labSeqMap.get(i - 4);
                        BigInteger operand2 = labSeqMap.get(i - 3);
                        BigInteger calculation = operand1.add(operand2);
                        labSeqMap.put(i, calculation);
                    }
                }
            }
            value = labSeqMap.get(n);
        }

        return value.toString();
    }

    @Override
    public String getAllLabSeqValues(){
        Collection<BigInteger> sequence = labSeqMap.values();
        return sequence.toString();
    }
}
