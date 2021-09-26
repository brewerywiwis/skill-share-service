package com.example.accountservice.entities;

import com.example.accountservice.types.UserPrincipal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Embeddable
@Getter
@Setter
public class DateTimeAudit {

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @PrePersist
    public void beforeAdd() {
        Instant dateTime = Instant.now();
        setCreatedAt(dateTime);
        setUpdatedAt(dateTime);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            username = request.getRemoteAddr();
        } else {
            username = ((UserPrincipal) authentication.getPrincipal()).getUsername();

        }
        setCreatedBy(username);
        setUpdatedBy(username);
    }

    @PreUpdate
    public void afterAdd() {
        setUpdatedAt(Instant.now());
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setUpdatedBy(userPrincipal.getUsername());
    }


}