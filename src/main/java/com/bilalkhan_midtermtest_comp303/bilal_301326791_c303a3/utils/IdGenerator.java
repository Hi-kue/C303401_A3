package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.enums.IdType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Component
public class IdGenerator {
    private final static ArrayList<Long> longCache = new ArrayList<>();
    private final static ArrayList<Short> shortCache = new ArrayList<>();
    private final static ArrayList<BigInteger> bigIntegerCache = new ArrayList<>();

    public Short generateRandomShort() {
        short shortId = (short) Math.round(Math.random() * Short.MAX_VALUE);
        if (shortCache.contains(shortId)) {
            shortId = generateRandomShort();
        }
        shortCache.add(shortId);
        return shortId;
    }

    public BigInteger generateRandomBigInteger() {
        BigInteger bigIntegerId = BigInteger.valueOf(Math.round(Math.random() * Integer.MAX_VALUE));
        if (bigIntegerCache.contains(bigIntegerId)) {
            bigIntegerId = generateRandomBigInteger();
        }
        bigIntegerCache.add(bigIntegerId);
        return bigIntegerId;
    }

    public Long generateRandomLong() {
        long longId = Math.round(Math.random() * Long.MAX_VALUE);
        if (longCache.contains(longId)) {
            longId = generateRandomLong();
        }
        longCache.add(longId);
        return longId;
    }

    public String generateRandomStringUuid() {
        return UUID.randomUUID().toString();
    }

    public <T> T generateRandomUniqueId(IdType idType) {
        return (T) switch (idType) {
            case UUID -> generateRandomStringUuid();
            case LONG -> generateRandomLong();
            case SMALLINT -> generateRandomShort();
            case BIGINT -> generateRandomBigInteger();
        };
    }
}
