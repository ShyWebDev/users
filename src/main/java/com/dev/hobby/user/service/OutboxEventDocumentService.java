package com.dev.hobby.user.service;

import com.dev.hobby.user.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import com.dev.hobby.user.repository.document.OutboxEventQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventDocumentService {

    private final OutboxEventQueryRepository qutBoxEventQueryRepository;

    public Optional<OutboxEventDocument> getOutBoxEventEntityByUniqueId(String uniqueId){
        return Optional.empty();
    }
}
