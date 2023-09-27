package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne
    private Customer customerId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderLine> orderLines = new ArrayList<>();

    private Date dateOfSubmission;




    public Orders(Customer customerId, List<OrderLine> orderLines, Date dateOfSubmission) {
        this.customerId = customerId;
        this.orderLines = orderLines;
        this.dateOfSubmission = dateOfSubmission;
    }

    public Orders(Customer customerId, Date dateOfSubmission) {
        this.customerId = customerId;
        this.dateOfSubmission = dateOfSubmission;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }






}