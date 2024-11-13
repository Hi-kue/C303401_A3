package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodStock;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces.BloodStockServiceIntf;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BloodStockService implements BloodStockServiceIntf {
    private final HashMap<Long, BloodStock> bloodStockHashMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<BloodStock> getAllBloodStocks() {
        if (bloodStockHashMap.isEmpty()) {
            return null;
        }

        return List.copyOf(bloodStockHashMap.values());
    }

    @Override
    public BloodStock getBloodStockById(@NotNull Long id) {
        if (bloodStockHashMap.containsKey(id)) {
            return bloodStockHashMap.get(id);
        }

        return null;
    }

    @Override
    public BloodStock createBloodStock(@NotNull BloodStock bloodStock) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (bloodStockHashMap.containsKey(bloodStock.getId())) {
            return null;
        }

        bloodStock.setId(idCounter.incrementAndGet());
        bloodStock.setCreatedAt(currentTime);
        bloodStock.setUpdatedAt(currentTime);
        bloodStockHashMap.put(bloodStock.getId(), bloodStock);
        return bloodStock;
    }

    @Override
    public BloodStock updateBloodStock(@NotNull Long id, BloodStock bloodStock) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        BloodStock bloodStockToUpdate = bloodStockHashMap.get(id);

        if (bloodStockToUpdate == null) {
            return null;
        }

        bloodStockToUpdate.setBloodGroup(bloodStock.getBloodGroup());
        bloodStockToUpdate.setQuantity(bloodStock.getQuantity());
        bloodStockToUpdate.setExpiryDate(bloodStock.getExpiryDate());
        bloodStockToUpdate.setStatus(bloodStock.getStatus());
        bloodStockToUpdate.setUpdatedAt(currentTime);

        return bloodStockToUpdate;
    }

    @Override
    public boolean deleteBloodStock(@NotNull Long id) {
        if (bloodStockHashMap.containsKey(id)) {
            bloodStockHashMap.remove(id);
            return true;
        }

        return false;
    }
}
