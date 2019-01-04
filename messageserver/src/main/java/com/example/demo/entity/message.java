package com.example.demo.entity;

import java.util.Date;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-03 02:44
 **/
public class message {
    private String id;

    private String message;

    private String sendcount;

    private String queue;

    private String sendsystem;

    private String status;

    private Date customerdate;

    private String customersystem;

    private Date cdate;

    private String diecount;

    private Date diedate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendcount() {
        return sendcount;
    }

    public void setSendcount(String sendcount) {
        this.sendcount = sendcount;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getSendsystem() {
        return sendsystem;
    }

    public void setSendsystem(String sendsystem) {
        this.sendsystem = sendsystem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCustomerdate() {
        return customerdate;
    }

    public void setCustomerdate(Date customerdate) {
        this.customerdate = customerdate;
    }

    public String getCustomersystem() {
        return customersystem;
    }

    public void setCustomersystem(String customersystem) {
        this.customersystem = customersystem;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getDiecount() {
        return diecount;
    }

    public void setDiecount(String diecount) {
        this.diecount = diecount;
    }

    public Date getDiedate() {
        return diedate;
    }

    public void setDiedate(Date diedate) {
        this.diedate = diedate;
    }
}
