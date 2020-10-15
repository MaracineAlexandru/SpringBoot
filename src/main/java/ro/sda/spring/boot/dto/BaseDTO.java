package ro.sda.spring.boot.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class BaseDTO {

    private Long id;

    private LocalDateTime createdTime;

    private LocalDateTime lastModifiedTime;

    public BaseDTO(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public BaseDTO() {
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
