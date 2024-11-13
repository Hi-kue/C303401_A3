package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodBank;

import java.util.List;

public interface BloodBankServiceIntf {
    List<BloodBank> getAllBloodBanks();
    BloodBank getBloodBankById(Long id);
    BloodBank createBloodBank(BloodBank bloodBank);
    BloodBank updateBloodBank(Long id, BloodBank bloodBank);
    boolean deleteBloodBank(Long id);
}
