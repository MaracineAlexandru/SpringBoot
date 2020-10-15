package ro.sda.spring.boot.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false,updatable = true)
    private LocalDateTime lastModifiedTime;

    @PrePersist
    private void prePersist(){
        this.createdTime = LocalDateTime.now();
        this.lastModifiedTime= LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.lastModifiedTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
