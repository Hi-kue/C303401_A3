package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.Seeker;

import java.util.List;

public interface SeekerServiceIntf {
    List<Seeker> getAllSeekers();
    Seeker getSeekerBySeekerId(Long id);
    Seeker createSeeker(Seeker seeker);
    Seeker updateSeeker(Long id, Seeker seeker);
    boolean deleteSeekerById(Long id);
}
