package com.sicmed.ehis.registration.entity;

import java.util.Date;

public class SicmedRegistered {
    private String id;

    private String patientId;

    private String branchId;

    private String doctorId;

    private String registrationType;

    private String registeredStatus;

    private String registeredPrice;

    private String priceStatus;

    private Date registeredBeginDate;

    private Date registeredEndDate;

    private String createUser;

    private Date createDate;

    private String updateUser;

    private Date updateDate;

    private String deleteUser;

    private Date deleteDate;

    private String remarks;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType == null ? null : registrationType.trim();
    }

    public String getRegisteredStatus() {
        return registeredStatus;
    }

    public void setRegisteredStatus(String registeredStatus) {
        this.registeredStatus = registeredStatus == null ? null : registeredStatus.trim();
    }

    public String getRegisteredPrice() {
        return registeredPrice;
    }

    public void setRegisteredPrice(String registeredPrice) {
        this.registeredPrice = registeredPrice == null ? null : registeredPrice.trim();
    }

    public String getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(String priceStatus) {
        this.priceStatus = priceStatus == null ? null : priceStatus.trim();
    }

    public Date getRegisteredBeginDate() {
        return registeredBeginDate;
    }

    public void setRegisteredBeginDate(Date registeredBeginDate) {
        this.registeredBeginDate = registeredBeginDate;
    }

    public Date getRegisteredEndDate() {
        return registeredEndDate;
    }

    public void setRegisteredEndDate(Date registeredEndDate) {
        this.registeredEndDate = registeredEndDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}