package br.com.url_shortener.url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {

    boolean existsByShortCode(String shortCode);

    Optional<UrlEntity> findByShortCode(String shortCode);

    @Modifying
    @Query("UPDATE Url u SET u.accessCount = u.accessCount + 1 WHERE u.id = :id")
    void incrementAccessCount(@Param("id") Long id);
}
