package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodStock;

import java.util.List;

public interface BloodStockServiceIntf {
    List<BloodStock> getAllBloodStocks();
    BloodStock getBloodStockById(Long id);
    BloodStock createBloodStock(BloodStock bloodStock);
    BloodStock updateBloodStock(Long id, BloodStock bloodStock);
    boolean deleteBloodStock(Long id);
}
