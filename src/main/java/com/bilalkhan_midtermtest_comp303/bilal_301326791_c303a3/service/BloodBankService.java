package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodBank;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces.BloodBankServiceIntf;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BloodBankService implements BloodBankServiceIntf {
    private final HashMap<Long, BloodBank> BloodBankHashMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<BloodBank> getAllBloodBanks() {
        if (BloodBankHashMap.isEmpty()) {
            return null;
        }

        return List.copyOf(BloodBankHashMap.values());
    }

    @Override
    public BloodBank getBloodBankById(@NotNull Long id) {
        if (BloodBankHashMap.containsKey(id)) {
            return BloodBankHashMap.get(id);
        }

        return null;
    }

    @Override
    public BloodBank createBloodBank(@NotNull BloodBank bloodBank) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (BloodBankHashMap.containsKey(bloodBank.getId())) {
            return null;
        }

        bloodBank.setId(idCounter.incrementAndGet());
        bloodBank.setCreatedAt(currentTime);
        bloodBank.setUpdatedAt(currentTime);
        BloodBankHashMap.put(bloodBank.getId(), bloodBank);
        return bloodBank;
    }

    @Override
    public BloodBank updateBloodBank(@NotNull Long id, BloodBank bloodBank) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        BloodBank bloodBankToUpdate = BloodBankHashMap.get(id);

        if (bloodBankToUpdate == null) {
            return null;
        }

        bloodBankToUpdate.setBloodBankName(bloodBank.getBloodBankName());
        bloodBankToUpdate.setAddress(bloodBank.getAddress());
        bloodBankToUpdate.setCity(bloodBank.getCity());
        bloodBankToUpdate.setPhoneNumber(bloodBank.getPhoneNumber());
        bloodBankToUpdate.setEmail(bloodBank.getEmail());
        bloodBankToUpdate.setUpdatedAt(currentTime);

        return bloodBankToUpdate;
    }

    @Override
    public boolean deleteBloodBank(@NotNull Long id) {
        if (BloodBankHashMap.containsKey(id)) {
            BloodBankHashMap.remove(id);
            return true;
        }

        return false;
    }
}
