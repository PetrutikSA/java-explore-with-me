package ru.practicum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "endpoints_hits")
@SecondaryTable(name = "apps", pkJoinColumns = @PrimaryKeyJoinColumn(name = "app_id"))
@SecondaryTable(name = "uris", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uri_id"))
@SecondaryTable(name = "ips", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ip_id"))
@Data
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", table = "apps")
    private String app;
    @Column(name = "name", table = "uris")
    private String uri;
    @Column(name = "name", table = "ips")
    private String ip;
    @Column(name = "created")
    private Instant timestamp;
}
