package ru.practicum.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.model.ParticipationRequest;

@Repository
public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {
}
