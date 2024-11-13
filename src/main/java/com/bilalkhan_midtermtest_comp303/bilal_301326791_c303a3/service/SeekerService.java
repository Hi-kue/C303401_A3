package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.Seeker;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.interfaces.SeekerServiceIntf;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SeekerService implements SeekerServiceIntf {
    private final HashMap<Long, Seeker> seekerHashMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<Seeker> getAllSeekers() {
        if (seekerHashMap.isEmpty()) {
            return null;
        }

        return List.copyOf(seekerHashMap.values());
    }

    @Override
    public Seeker getSeekerBySeekerId(@NotNull Long id) {
        if (seekerHashMap.containsKey(id)) {
            return seekerHashMap.get(id);
        }

        return null;
    }

    @Override
    public Seeker createSeeker(@NotNull Seeker seeker) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (seekerHashMap.containsValue(seeker)) {
            return null;
        }

        seeker.setId(idCounter.incrementAndGet());
        seeker.setCreatedAt(currentTime);
        seeker.setUpdatedAt(currentTime);
        seekerHashMap.put(seeker.getId(), seeker);
        return seeker;
    }

    @Override
    public Seeker updateSeeker(@NotNull Long id, @NotNull Seeker seeker) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Seeker seekerToUpdate = seekerHashMap.get(id);

        if (seekerToUpdate == null) {
            return null;
        }

        seekerToUpdate.setFirstName(seeker.getFirstName());
        seekerToUpdate.setLastName(seeker.getLastName());
        seekerToUpdate.setAge(seeker.getAge());
        seekerToUpdate.setBloodGroup(seeker.getBloodGroup());
        seekerToUpdate.setCity(seeker.getCity());
        seekerToUpdate.setPhoneNumber(seeker.getPhoneNumber());
        seekerToUpdate.setUpdatedAt(currentTime);

        return seekerToUpdate;
    }

    @Override
    public boolean deleteSeekerById(@NotNull Long id) {
        if (seekerHashMap.containsKey(id)) {
            seekerHashMap.remove(id);
            return true;
        }

        return false;
    }
}
