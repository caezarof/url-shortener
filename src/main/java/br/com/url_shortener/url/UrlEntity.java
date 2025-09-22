package br.com.url_shortener.url;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;

@Table(name = "urls")
@Entity(name = "Url")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SQLRestriction("enabled = true")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, updatable = false, length = 2048)
    @NotNull
    @URL
    private String originalUrl;

    @Column(nullable = false, unique = true, length = 10)
    @NotNull
    private String shortCode;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private final Instant createdAt = Instant.now();

    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt = Instant.now();

    @Column(nullable = false)
    private Integer accessCount = 0;

    @Column(nullable = false)
    private Boolean enabled = true;

    public UrlEntity(String originalUrl, String shortCode){
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.accessCount = 0;
        this.enabled = true;
    }

    public void disable(){
        this.enabled = false;
    }

    public void enable(){
        this.enabled = true;
    }
}
