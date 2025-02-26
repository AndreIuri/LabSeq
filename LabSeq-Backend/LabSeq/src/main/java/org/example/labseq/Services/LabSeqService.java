package org.example.labseq.Services;

import java.math.BigInteger;
import java.util.Collection;

public interface LabSeqService {

    BigInteger getValue(Integer n);

    Collection<BigInteger> getAllLabSeqValues();
}
