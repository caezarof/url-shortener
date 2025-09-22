package br.com.url_shortener.url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {

    boolean existsByShortCode(String shortCode);

    Optional<UrlEntity> findByShortCode(String shortCode);
}
