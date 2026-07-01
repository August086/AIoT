package com.aiot.infra.adapter;

import com.aiot.domain.model.PhysiologicalSnapshot;
import com.aiot.domain.model.TimeRange;
import com.aiot.domain.port.PhysiologicalDataBuffer;
import com.aiot.domain.port.BufferException;
import com.aiot.domain.shared.TripId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class PhysiologicalDataBufferAdapter implements PhysiologicalDataBuffer {

    private final ConcurrentLinkedDeque<PhysiologicalSnapshot> deque = new ConcurrentLinkedDeque<>();
    private static final int CAPACITY = 600;

    @Override
    public List<PhysiologicalSnapshot> getReadings(TripId tripId, TimeRange window)
            throws BufferException {
        List<PhysiologicalSnapshot> result = new ArrayList<>();
        for (PhysiologicalSnapshot s : deque) {
            if (!s.timestamp().isBefore(window.start())
                    && !s.timestamp().isAfter(window.end())) {
                result.add(s);
            }
        }
        if (result.isEmpty()) {
            throw new BufferException.WindowNotCoveredException(
                    "No readings in window: " + window);
        }
        return result;
    }

    public void append(PhysiologicalSnapshot snapshot) {
        if (deque.size() >= CAPACITY) {
            deque.removeFirst();
        }
        deque.addLast(snapshot);
    }
}
