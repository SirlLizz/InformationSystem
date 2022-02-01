package com.example.client.transport;

import com.example.client.reference.ReferenceSystem;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "response")
@XmlRootElement
public class Response {

    ReferenceSystem department;

    public Response() {
    }

    public void setDepartment(ReferenceSystem department) {
        this.department = department;
    }

    public ReferenceSystem getDepartment() {
        return department;
    }

    public Response(ReferenceSystem department) {
        this.department = department;
    }
}
